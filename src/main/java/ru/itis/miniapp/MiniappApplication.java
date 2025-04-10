package ru.itis.miniapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class MiniappApplication implements CommandLineRunner {

	@Autowired
	private MyTelegramBot myTelegramBot; // Инжектируем наш бот из контекста Spring

	public static void main(String[] args) {
		SpringApplication.run(MiniappApplication.class, args);
	}

	@Override
	public void run(String... args) {
		try {
			TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
			botsApi.registerBot(myTelegramBot);
			System.out.println("Бот запущен!");
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}
