package com.example.lessonEnglish.controller.admin;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.example.lessonEnglish.dto.ChatMessageDto;

@Component
public class WebSocketEventListener { // tạo class này để lắng nghe trình kết nối và ngắt kết nối
	private static final Logger logger=LoggerFactory.getLogger(WebSocketEventListener.class);
	
	static Map<String,String> users=new HashMap<>();
	
	@Autowired
	private SimpMessageSendingOperations messageingTemplate;
		
	
	@EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
		
        logger.info("Received a new web socket connection: " +event.getMessage());
    }
	
	@EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) headerAccessor.getSessionAttributes().get("username");
        String id = (String) headerAccessor.getSessionAttributes().get("id");
        if(username != null) {
            
            users.remove(id);
            ChatMessageDto chatMessage = new ChatMessageDto();
            chatMessage.setType(ChatMessageDto.MessageType.LEAVE);
            chatMessage.setSender(username);
            chatMessage.setIdSender(id);
            chatMessage.setUserOnline(users);
            messageingTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }
	
	
}
