package com.example.lessonEnglish.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.lessonEnglish.entity.Learn;
import com.example.lessonEnglish.entity.Lesson;

public interface LearnRepository extends JpaRepository<Learn, String>{
    @Query(value ="select * from Learn le where le.id_user=:idUser and le.id_lesson=:idLesson",nativeQuery=true)
    Learn findAllLessonById(@Param("idUser") String idUser,@Param("idLesson") String idLesson);

    @Query(value ="select * from Learn le where le.id_user=:idUser",nativeQuery=true)
    List<Learn> findAllLessonByIdUser(@Param("idUser") String idUser);
}
