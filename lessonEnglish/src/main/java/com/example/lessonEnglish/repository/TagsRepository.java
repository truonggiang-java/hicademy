package com.example.lessonEnglish.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lessonEnglish.entity.Tags;

@Repository
public interface TagsRepository extends JpaRepository<Tags, String>{
	
}
