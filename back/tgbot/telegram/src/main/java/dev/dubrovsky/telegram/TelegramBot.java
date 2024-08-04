package dev.dubrovsky.telegram;

import dev.dubrovsky.configuration.BotConfiguration;
import dev.dubrovsky.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfiguration botConfiguration;
    private final UserService userService;

    public TelegramBot(BotConfiguration botConfiguration, UserService userService) {
        super(botConfiguration.getToken());

        this.botConfiguration = botConfiguration;
        this.userService = userService;
    }

    @Override
    public String getBotUsername() {
        return botConfiguration.getBotName();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText) {
                case "/start":
                    handleStartCommand(chatId);
                    break;
                case "/register":
                    handleRegisterCommand(chatId, update.getMessage().getChat().getUserName());
                    break;
                case "/tournament":
                    handleOpenTournament(chatId);
                    break;
                default:
                    sendMessage(chatId, "Unknown command.");
                    break;
            }
        }
    }

    private void handleStartCommand(long chatId) {
        sendMessage(chatId, "Добро пожаловать! Пожалуйста, зарегистрируйтесь, используя команду /register.");
    }

    private void handleRegisterCommand(long chatId, String username) {

        userService.registerUser(username, chatId);
        sendMessage(chatId, "Вы успешно зарегистрировались! Теперь вы можете использовать команду /tournament для открытия таблицы.");

    }

    private void handleOpenTournament(long chatId) {

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Нажмите на кнопку ниже, чтобы открыть Google:");

        InlineKeyboardButton webAppButton = new InlineKeyboardButton();
        webAppButton.setText("Open Tournament");
        webAppButton.setWebApp(new WebAppInfo("https://tournamentbot-b7tb.onrender.com"));

        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        keyboardButtonsRow.add(webAppButton);

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(keyboardButtonsRow);

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void registerBotCommands() {
        List<BotCommand> commands = new ArrayList<>();
        commands.add(new BotCommand("/start", "Start bot"));
        commands.add(new BotCommand("/register", "Register to use the bot"));
        commands.add(new BotCommand("/tournament", "Open Tournament table"));

        try {
            this.execute(new SetMyCommands(commands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /*@Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            if (messageText.equalsIgnoreCase("/start")) {
                SendMessage message = new SendMessage();
                message.setChatId(chatId);
                message.setText("Нажмите на кнопку, чтобы открыть турнир:");

                // Создаем кнопку для Web App
                InlineKeyboardButton webAppButton = new InlineKeyboardButton();
                webAppButton.setText("Open Tournament");
                //webAppButton.setWebApp(new WebAppInfo("https://www.google.com/")); // URL вашего веб-приложения
                webAppButton.setWebApp(new WebAppInfo("https://tournamentbot-nzi7.onrender.com/")); // URL вашего веб-приложения

                // Создаем строку клавиатуры и добавляем в нее кнопку
                List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
                keyboardButtonsRow.add(webAppButton);

                // Создаем клавиатуру и добавляем в нее строку
                List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
                keyboard.add(keyboardButtonsRow);

                InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
                keyboardMarkup.setKeyboard(keyboard);
                message.setReplyMarkup(keyboardMarkup);

                try {
                    execute(message); // Отправляем сообщение
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/

}

