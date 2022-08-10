package com.example.lessonEnglish.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.lessonEnglish.entity.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, String>{
	@Query("select l from Lesson l where l.id in :id")
	List<Lesson> findListLesson(@Param("id") List<String> id);
}
