package com.example.lessonEnglish.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="logo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Logo  extends BaseEntity{
	@Column(name="file_name")
	private String fileName;
	
	@Column(name="file_type")
	private String fileType;
}
