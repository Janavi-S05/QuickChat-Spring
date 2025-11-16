package com.SpringbootChat.chatapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication {
//	WebSocket → Real-time 2-way communication.
//	STOMP Protocol → A messaging format on top of WebSocket.
//	SockJS → Helps connect even if WebSockets are blocked (fallback).
//	Spring Boot → Backend handling.
//	HTML + JavaScript → Frontend.
	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}
}
