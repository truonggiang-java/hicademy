package com.example.lessonEnglish.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="dlfileentry")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DlFileEntry extends BaseEntity{
	@Column(name="file_name")
	private String fileName;
	
	@Column(name="file_type")
	private String fileType;
	
	@Column(name="data_byte")
	@Lob
	private byte[] data;
}
