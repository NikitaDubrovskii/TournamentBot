package dev.dubrovsky.action;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface IAction {

    BotApiMethod<Message> handle(Update update);

    BotApiMethod<Message> callback(Update update);

}
