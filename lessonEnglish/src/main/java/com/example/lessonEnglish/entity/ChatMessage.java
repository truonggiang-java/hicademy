package com.example.lessonEnglish.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="chatmessage")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage extends BaseEntity {
	@Column(name="content")
	private String content;
	
	@Column(name="sender_id")
	private String linkImage;
	
	@Column(name="sender")
	private String name;
	
}
