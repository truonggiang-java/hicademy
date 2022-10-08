package com.example.lessonEnglish.controller.admin;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.lessonEnglish.dto.ChatMessageDto;

@Controller
@CrossOrigin("*")
public class ChatController {
	
	
//	cả các thông báo được gửi từ các máy khách có đích bắt đầu bằng /app sẽ được chuyển đến các phương thức xử lý thông báo được chú thích bằng @MessageMapping.
//	Ví dụ: một thông báo có đích /app/chat.sendMessagesẽ được chuyển đến sendMessage()phương thức và một thông báo có đích /app/chat.addUsersẽ được chuyển đến addUser()phương thức
	@MessageMapping("/chat.sendMessage")
	@SendTo("/topic/public")
	public ChatMessageDto chatMessage(@Payload ChatMessageDto chatMessage) {
		
		return chatMessage;
	}

	@MessageMapping("/chat.addUser")
	@SendTo("/topic/public")
	public ChatMessageDto addUser(@Payload ChatMessageDto chatMessage, SimpMessageHeaderAccessor headerAccessor) {
		
		WebSocketEventListener.users.put(chatMessage.getIdSender(),chatMessage.getSender());
		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
		headerAccessor.getSessionAttributes().put("id", chatMessage.getIdSender());
		chatMessage.setUserOnline(WebSocketEventListener.users);
		return chatMessage;
	}
	
	
}
