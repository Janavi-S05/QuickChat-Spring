package com.SpringbootChat.chatapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/chat")
				.setAllowedOrigins("http://localhost:8080")
				.withSockJS();
		
		// client will connect to websocket using /chat endpoint (like SockJS("/chat"))
		// prevents CORS issues -> only 8080 frontend can connect
		// if WebSocket is blocked SockJS provides fallback
		
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
	 	// like a chat room, anyone subscribed to /topic/messages will get updates, used for broadcasting messages to users
		registry.enableSimpleBroker("/topic"); // Who subscribe to /topic then they broadcast of whatever msgs being sent over here 
		
		// msgs sent from browser to server must start with /app, expect message with /app/sendmessage processed by controller
		registry.setApplicationDestinationPrefixes("/app");
	}
    // WebSocket is for handling two way communication - Real-time connection
	// STOMP provides structure to the message that is passed between  - organize, routing
	
    	
	
}
