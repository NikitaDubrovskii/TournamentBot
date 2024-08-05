package dev.dubrovsky.action;

import dev.dubrovsky.service.UserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class ActionRegistration implements IAction {

    private final UserService userService;

    public ActionRegistration(UserService userService) {
        this.userService = userService;
    }

    @Override
    public BotApiMethod<Message> handle(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();

            if (!userService.isUserRegistered(chatId)) {
                userService.registerUser(update.getMessage().getChat().getUserName(), chatId);
                return createMessage(chatId, "Вы успешно зарегистрировались! Теперь вы можете использовать команду /tournament для открытия таблицы.");
            } else {
                return createMessage(chatId, "Вы уже зарегистрированы.");
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
