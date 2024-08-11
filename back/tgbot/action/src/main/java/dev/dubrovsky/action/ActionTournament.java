package dev.dubrovsky.action;

import dev.dubrovsky.service.UserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Component
public class ActionTournament implements IAction {

    private final UserService userService;

    public ActionTournament(UserService userService) {
        this.userService = userService;
    }

    @Override
    public BotApiMethod<Message> handle(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();

            if (userService.isUserRegistered(chatId)) {
                SendMessage message = new SendMessage();
                message.setChatId(chatId);
                message.setText("Нажмите на кнопку ниже, чтобы открыть таблицу:");

                InlineKeyboardButton webAppButton = new InlineKeyboardButton();
                webAppButton.setText("Open Tournament");
                String username = update.getMessage().getFrom().getUserName();
                try {
                    webAppButton.setWebApp(new WebAppInfo("https://tournamentbot-b7tb.onrender.com?username=" + URLEncoder.encode(username, "UTF-8")));
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }

                List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
                keyboardButtonsRow.add(webAppButton);

                List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
                keyboard.add(keyboardButtonsRow);

                InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
                keyboardMarkup.setKeyboard(keyboard);
                message.setReplyMarkup(keyboardMarkup);

                return message;
            } else {
                return createMessage(chatId, "Пожалуйста, зарегистрируйтесь, используя команду /register.");
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
