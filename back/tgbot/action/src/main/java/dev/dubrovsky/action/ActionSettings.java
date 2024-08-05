package dev.dubrovsky.action;

import dev.dubrovsky.service.UserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class ActionSettings implements IAction {

    private final UserService userService;

    public ActionSettings(UserService userService) {
        this.userService = userService;
    }

    @Override
    public BotApiMethod<Message> handle(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();

            if (userService.isUserRegistered(chatId)) {
                SendMessage message = new SendMessage();
                message.setChatId(chatId);

                message.setText("Введите имя");

                /*message.setText("Выберите пункт меню:");

                InlineKeyboardButton setNameButton = new InlineKeyboardButton();
                setNameButton.setText("Добавить/изменить имя");
                setNameButton.setCallbackData("set_name_Введите ваше имя");

                List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
                keyboardButtonsRow.add(setNameButton);

                List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
                keyboard.add(keyboardButtonsRow);

                InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
                keyboardMarkup.setKeyboard(keyboard);
                message.setReplyMarkup(keyboardMarkup);*/

                return message;
            } else {
                return createMessage(chatId, "Пожалуйста, зарегистрируйтесь, используя команду /register.");
            }
        }

        return null;
    }

    @Override
    public BotApiMethod<Message> callback(Update update) {
        var message = update.getMessage();
        var chatId = message.getChatId();
        userService.updateName(chatId, message.getText());
        return createMessage(chatId, "Имя установлено на: " + message.getText());
    }

    private SendMessage createMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        return message;
    }

}
