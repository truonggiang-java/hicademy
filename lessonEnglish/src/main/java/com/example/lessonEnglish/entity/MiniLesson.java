package com.example.lessonEnglish.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="mini_lesson")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MiniLesson extends BaseEntity{
	
	
	@Column(name="id_lesson")
	private String idLesson;
	
	@Column(name="id_dlfileentry")
	private String idDlFileEntry;
}
