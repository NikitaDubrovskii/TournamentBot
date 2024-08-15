package dev.dubrovsky.action;

import dev.dubrovsky.service.user.UserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
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
        if (update.hasCallbackQuery()) {
            long chatId = update.getCallbackQuery().getMessage().getChatId();

            if (userService.isUserRegistered(chatId)) {
                SendMessage message = new SendMessage();
                message.setChatId(chatId);

                message.setText("""
                        Настройки ⚙️
                        
                        Выберите пункт меню:
                        """);

                InlineKeyboardButton checkNameButton  = new InlineKeyboardButton();
                checkNameButton.setText("Проверить имя");
                checkNameButton.setCallbackData("settings_check_name");

                InlineKeyboardButton changeNameButton = new InlineKeyboardButton();
                changeNameButton.setText("Добавить/изменить имя");
                changeNameButton.setCallbackData("settings_change_name");

                InlineKeyboardButton deleteNameButton = new InlineKeyboardButton();
                deleteNameButton.setText("Удалить имя");
                deleteNameButton.setCallbackData("settings_delete_name");


                List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
                keyboardButtonsRow1.add(checkNameButton);

                List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
                keyboardButtonsRow2.add(changeNameButton);

                List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
                keyboardButtonsRow3.add(deleteNameButton);

                List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
                keyboard.add(keyboardButtonsRow1);
                keyboard.add(keyboardButtonsRow2);
                keyboard.add(keyboardButtonsRow3);

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
        if (update.hasMessage() && update.getMessage().hasText()) {
            var message = update.getMessage();
            var chatId = message.getChatId();

            userService.updateName(chatId, message.getText());
            return createMessage(chatId, """
                    Настройки ⚙️ -> Поменять имя
                    
                    Имя установлено!
                    """);
        } else if (update.hasCallbackQuery()) {
            var chatId = update.getCallbackQuery().getMessage().getChatId();
            String data = update.getCallbackQuery().getData();

            if (data.endsWith("check_name")) {
                String name = userService.getName(chatId);

                SendMessage message = new SendMessage();
                message.setChatId(chatId);

                if (name == null) {
                    message.setText("""
                            Настройки ⚙️ -> Проверить имя
                            
                            Имя не установлено, перейдите к настройке "Добавить/изменить имя".
                            """);
                } else {
                    message.setText("""
                            Настройки ⚙️ -> Проверить имя
                            
                            Установлено имя: %s
                            """.formatted(name));
                }

                return message;
            } else if (data.endsWith("change_name")) {
                SendMessage message = new SendMessage();
                message.setChatId(chatId);
                message.setText("""
                        Настройки ⚙️ -> Поменять имя
                        
                        Введите имя:
                        """);

                return message;
            } else if (data.endsWith("delete_name")) {
                userService.deleteName(chatId);

                SendMessage message = new SendMessage();
                message.setChatId(chatId);
                message.setText("""
                        Настройки ⚙️ -> Удалить имя
                        
                        Имя удалено!
                        """);

                return message;
            }
            else {
                return null;
            }
        } else {
            return null;
        }
    }

    private SendMessage createMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        return message;
    }

}
