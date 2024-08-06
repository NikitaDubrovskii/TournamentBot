package dev.dubrovsky.action;

import dev.dubrovsky.service.UserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class ActionRegistration implements IAction {

    private static final String TEXT = """
            Вы успешно зарегистрировались!
            Настоятельно рекомендуем использовать /settings, для того чтобы установить себе имя.
            
            Теперь вы можете использовать команды:
            
            /start - приветственное сообщение с подсказками
            /settings - позволяет настроить имя пользователя
            /tournament - открывает приложение с таблицей
            """;

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
                return createMessage(chatId, TEXT);
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
