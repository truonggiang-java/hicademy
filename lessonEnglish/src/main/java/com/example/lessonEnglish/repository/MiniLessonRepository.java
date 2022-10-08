package com.example.lessonEnglish.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.lessonEnglish.entity.MiniLesson;
import com.example.lessonEnglish.projections.MiniLessonProjection;

public interface MiniLessonRepository extends JpaRepository<MiniLesson, String>{
	@Query(value = "select ml.id as id, le.name as name,le.description as description ,ml.id_dlfileentry as idDlFileEntry from lesson le INNER JOIN   mini_lesson ml ON le.id=ml.id_lesson where upper(le.name) like concat(concat('%',upper(:input)),'%') or upper(le.description) like concat(concat('%',upper(:input)),'%') order by ml.update_date desc limit :page, :size",nativeQuery = true)
	List<MiniLessonProjection> findAllMiniLesson(@Param("input") String input,@Param("page") Integer page,@Param("size") Integer size);
	
	@Query(value="select count(ml.id) as count from lesson le INNER JOIN   mini_lesson ml ON le.id=ml.id_lesson where upper(le.name) like concat(concat('%',upper(:input)),'%') or upper(le.description) like concat(concat('%',upper(:input)),'%')",nativeQuery = true)
	Long countMiniLesson(@Param("input") String input);
	
	@Query("select l from MiniLesson l where l.id in :id")
	List<MiniLesson> findListIdMiniLesson(@Param("id") List<String> id);
	
	@Query(value="select * from mini_lesson l where l.id_lesson = :id",nativeQuery = true)
	MiniLesson findListIdMiniLessonById(@Param("id") String id);
}
