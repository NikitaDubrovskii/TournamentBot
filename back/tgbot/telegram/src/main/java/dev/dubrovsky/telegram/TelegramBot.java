package dev.dubrovsky.telegram;

import dev.dubrovsky.action.IAction;
import dev.dubrovsky.configuration.BotConfiguration;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfiguration botConfiguration;

    // https://www.youtube.com/watch?v=_d72wm_XGrM
    private final Map<Long, String> bindingBy = new ConcurrentHashMap<>();
    private final Map<String, IAction> actions;

    public TelegramBot(BotConfiguration botConfiguration, BotView botView) {
        super(botConfiguration.getToken());

        this.botConfiguration = botConfiguration;
        this.actions = botView.getActions();
    }

    @Override
    public String getBotUsername() {
        return botConfiguration.getBotName();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            var messageKey = update.getMessage().getText();
            var chatId = update.getMessage().getChatId();

            if (actions.containsKey(messageKey)) {
                var message = actions.get(messageKey).handle(update);
                bindingBy.put(chatId, messageKey);

                executeMessage(message);
            } else if (bindingBy.containsKey(chatId)) {
                var message = actions.get(bindingBy.get(chatId)).callback(update);
                bindingBy.remove(chatId);

                executeMessage(message);
            }
        } else if (update.hasCallbackQuery()) {
            var callbackQuery = update.getCallbackQuery().getData();
            var chatId = update.getCallbackQuery().getMessage().getChatId();

            if (callbackQuery.equals("settings")) {
                var message = actions.get("/settings").handle(update);

                executeMessage(message);
            }else if (callbackQuery.startsWith("settings_")) {
                var message = actions.get(bindingBy.get(chatId)).callback(update);

                executeMessage(message);
            }
        }
    }

    @PostConstruct
    public void registerBotCommands() {
        List<BotCommand> commands = new ArrayList<>();
        commands.add(new BotCommand("/start", "Start bot"));
        //commands.add(new BotCommand("/register", "Register to use the bot"));
        //commands.add(new BotCommand("/settings", "Change your name"));

        try {
            this.execute(new SetMyCommands(commands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void executeMessage(BotApiMethod<Message> message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
