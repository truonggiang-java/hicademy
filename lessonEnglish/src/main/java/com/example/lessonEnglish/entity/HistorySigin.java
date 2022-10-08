package com.example.lessonEnglish.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="historysigin")
public class HistorySigin {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name="UUID",strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name="id")
	private String id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="ip")
	private String ip;
	
	
	@Column(name="status")
	private String status;
	
	@Column(name="access_date")
	private Date date;
	
	public enum StatusConnect{
		JOIN,LEAVE
	}
}
