package com.example.lessonEnglish.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tags")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tags extends BaseEntity{
	@Column(name="file_name")
	private String fileName;
	
	@Column(name="param")
	private String param;
	
	@Column(name="description")
	private String description;
}
