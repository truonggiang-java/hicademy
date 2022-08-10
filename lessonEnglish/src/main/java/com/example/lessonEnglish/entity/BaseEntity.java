package com.example.lessonEnglish.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name="UUID",strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name="id")
	private String id;
	
	@Column(name="create_date")
	@CreatedDate
	private Timestamp createdDate;
	
	@Column(name="update_date")
	@LastModifiedDate
	private Timestamp updatedDate;
	
	public Timestamp getCreatedDate() {
		if(createdDate == null) {
			return null;
		}
		return new Timestamp(createdDate.getTime());
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = (createdDate == null ? null :new Timestamp(createdDate.getTime()));
	}

	public Timestamp getUpdatedDate() {
		if(updatedDate ==null) {
			return null;
		}
		return new Timestamp(updatedDate.getTime());
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = (updatedDate == null ? null : new Timestamp(updatedDate.getTime()));
	}
}
