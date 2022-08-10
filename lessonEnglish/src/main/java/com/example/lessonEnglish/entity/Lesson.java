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
@Table(name="lesson")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lesson extends BaseEntity{
	@Column(name="name")
	private String name;
	
	@Column(name="id_dlfileentry")
	private String idDlfileEntry;
	
	@Column(name="description")
	private String description;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="course_lesson",joinColumns = @JoinColumn(name="lesson_id"),inverseJoinColumns = @JoinColumn(name="course_id"))
	@JsonIgnore
	private List<Course> courses=new ArrayList<>();
}
