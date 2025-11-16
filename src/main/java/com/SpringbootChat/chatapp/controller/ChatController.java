package com.SpringbootChat.chatapp.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.SpringbootChat.chatapp.model.ChatMessage;

@Controller
public class ChatController {
	
	// Browser sends msg to -> /app/sendMessage
	@MessageMapping("/sendMessage")
	@SendTo("/topic/messages") // Whatever the method returns will be sent to /topic/messages
	public ChatMessage sendMessage(ChatMessage message)
	{
		return message;
	}
	
	@GetMapping("chat")
	public String chat()
	{
		return "chat"; // When user visits /chat it returns chat.html view
	}
}
