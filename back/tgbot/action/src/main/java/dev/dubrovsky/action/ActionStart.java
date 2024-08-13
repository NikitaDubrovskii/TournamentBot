package dev.dubrovsky.action;

import dev.dubrovsky.service.UserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppData;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class ActionStart implements IAction{

    private static final String TEXT_BEFORE_REG = """
            Добро пожаловать!
            
            Для использования данного бота необходимо провести регистрацию, используя команду /register.
            Иные команды доступны только после регистрации!
            """;

    private static final String TEXT_AFTER_REG = """
            Привет! 🎉
            
            Добро пожаловать в наш бот для создания турнирных таблиц!
            
            Здесь вы можете легко и быстро отслеживать результаты ваших матчей и организовывать турниры с друзьями.
            
            Нажмите кнопку ниже, чтобы открыть мини-приложение и начать управлять вашим турниром.
            
            Пусть ваши соревнования будут еще увлекательнее!
            """;

    private final UserService userService;

    public ActionStart(UserService userService) {
        this.userService = userService;
    }

    @Override
    public BotApiMethod<Message> handle(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();

            if (!userService.isUserRegistered(chatId)) {
                return createMessage(chatId, TEXT_BEFORE_REG);
            } else {
                SendMessage message = new SendMessage();
                message.setChatId(chatId);
                message.setText(TEXT_AFTER_REG);

                InlineKeyboardButton webAppButton = new InlineKeyboardButton();
                webAppButton.setText("Open Tournament 🏆");
                String username = update.getMessage().getFrom().getUserName();
                webAppButton.setWebApp(new WebAppInfo("https://tournamentbot-b7tb.onrender.com?username=" + URLEncoder.encode(username, StandardCharsets.UTF_8)));

                InlineKeyboardButton settingsButton = new InlineKeyboardButton();
                settingsButton.setText("Settings ⚙️");
                settingsButton.setCallbackData("/settings");

                List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
                keyboardButtonsRow1.add(webAppButton);

                List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
                keyboardButtonsRow2.add(settingsButton);

                List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
                keyboard.add(keyboardButtonsRow1);
                keyboard.add(keyboardButtonsRow2);

                InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
                keyboardMarkup.setKeyboard(keyboard);
                message.setReplyMarkup(keyboardMarkup);

                return message;
            }
        }

        return null;
    }

    @Override
    public BotApiMethod<Message> callback(Update update) {
        return handle(update);
    }

    private SendMessage createMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        return message;
    }

}
