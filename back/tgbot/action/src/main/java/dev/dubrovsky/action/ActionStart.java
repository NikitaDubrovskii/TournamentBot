package dev.dubrovsky.action;

import dev.dubrovsky.service.UserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class ActionStart implements IAction{

    private static final String TEXT_BEFORE_REG = """
            Добро пожаловать!
            
            Для использования данного бота необходимо провести регистрацию, используя команду /register.
            Иные команды доступны только после регистрации!
            """;

    private static final String TEXT_AFTER_REG = """
            Добро пожаловать!
            
            /settings - позволяет настроить имя пользователя
            /tournament - открывает приложение с таблицей
            
            Приятного пользования и великих побед!
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
                return createMessage(chatId, TEXT_AFTER_REG);
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
