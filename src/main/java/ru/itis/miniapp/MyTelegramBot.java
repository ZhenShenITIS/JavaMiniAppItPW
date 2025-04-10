package ru.itis.miniapp;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyTelegramBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "counter_43089249_bot"; // замените на имя вашего бота
    }

    @Override
    public String getBotToken() {
        return "7987887292:AAH_jUvT-2y_zL9x5c8lWLXnzgJaLOH1_2s"; // замените на токен вашего бота
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            if ("/start".equals(text)) {
                sendMiniAppLink(update.getMessage().getChatId().toString());
            }
        }
    }

    private void sendMiniAppLink(String chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Нажмите кнопку для открытия миниприложения");

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Открыть MiniApp");
        // Замените URL на адрес вашего миниприложения
        button.setUrl("t.me/counter_43089249_bot/counter_43");
        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(button);
        rows.add(row);
        markup.setKeyboard(rows);
        message.setReplyMarkup(markup);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
