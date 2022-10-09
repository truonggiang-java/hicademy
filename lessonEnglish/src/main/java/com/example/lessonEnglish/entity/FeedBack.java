package com.example.lessonEnglish.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="feedback")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedBack extends BaseEntity {
	@Column(name="name")
	private String name;
	
	@Column(name="id_user")
	private String idUser;
	
	@Column(name="description")
	private String description;
}
