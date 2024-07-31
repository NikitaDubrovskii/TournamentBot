package dev.dubrovsky.bot;

import dev.dubrovsky.configuration.BotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig botConfig;

    public TelegramBot(BotConfig botConfig) {
        super(botConfig.getToken());

        this.botConfig = botConfig;
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
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

    }

}

