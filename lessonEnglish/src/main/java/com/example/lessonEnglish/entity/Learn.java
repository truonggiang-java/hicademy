package com.example.lessonEnglish.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="learn")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Learn {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name="UUID",strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name="id")
	private String id;
	
	@Column(name="id_user")
	private String idUser;
	
	@Column(name="id_lesson")
	private String id_lesson;
	
	@Column(name="point")
	private Integer point;
}
