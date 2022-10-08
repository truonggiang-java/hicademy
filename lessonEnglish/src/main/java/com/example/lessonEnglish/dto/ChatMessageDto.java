package com.example.lessonEnglish.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDto {
	
	private MessageType type;
	private String content;
	private String sender;
	private String idSender;
	private Map<String,String> userOnline = new HashMap<>();
	public enum MessageType{
		CHAT,JOIN,LEAVE
	}
}
