package com.globalchat.chatapp;

import com.globalchat.chatapp.services.RoomService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GlobalChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(GlobalChatApplication.class, args);
	}

	@Bean
	public CommandLineRunner initializeRooms(RoomService roomService) {
		return args -> {
			// Check if rooms already exist
			if (roomService.getAllRooms().isEmpty()) {
				// Create default rooms
				roomService.createRoom("General", "General discussion");
				roomService.createRoom("Tech", "Technology discussions");
				roomService.createRoom("Random", "Random topics");
				roomService.createRoom("Help", "Get help with various topics");

				System.out.println("Default chat rooms have been created.");
			} else {
				System.out.println("Chat rooms already exist, skipping initialization.");
			}
		};
	}
}
