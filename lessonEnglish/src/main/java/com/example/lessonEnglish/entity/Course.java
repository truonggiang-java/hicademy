package com.example.lessonEnglish.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="course")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course extends BaseEntity{
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="id_dlfileentry")
	private String idDlFileEntry;
	
	@Column(name="param")
	private String param;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="course_lesson",joinColumns = @JoinColumn(name="course_id"),inverseJoinColumns = @JoinColumn(name="lesson_id"))
	@JsonIgnore
	private List<Lesson> lessons=new ArrayList<>();
}
