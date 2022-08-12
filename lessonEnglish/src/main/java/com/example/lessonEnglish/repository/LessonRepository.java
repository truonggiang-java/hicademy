package com.example.lessonEnglish.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.lessonEnglish.entity.Lesson;
import com.example.lessonEnglish.projections.CourseLessonProjection;
import com.example.lessonEnglish.projections.LessonProjection;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, String>{
	@Query("select l from Lesson l where l.name in :name")
	List<Lesson> findListLesson(@Param("name") List<String> name);

	@Query(value = "select c.id as id, c.name as name,c.description as description ,c.id_dlfileentry as idDlFileEntry,count(cl.course_id) as countCourse from lesson c LEFT JOIN   course_lesson cl ON c.id=cl.lesson_id where upper(c.name) like concat(concat('%',upper(:input)),'%') or upper(c.description) like concat(concat('%',upper(:input)),'%') group by c.name order by c.update_date desc limit :page, :size",nativeQuery = true)
	List<LessonProjection> findAllLesson(@Param("input") String input,@Param("page") Integer page,@Param("size") Integer size);

	@Query(value="select l.name as name from course_lesson cl inner join course l on cl.course_id=l.id where cl.lesson_id=:id order by l.update_date desc",nativeQuery = true)
	List<CourseLessonProjection> findCourseByIdLesson(@Param("id") String id);
	
	@Query(value="select count(*) from Lesson c where upper(c.name) like concat(concat('%',upper(:input)),'%') or upper(c.description) like concat(concat('%',upper(:input)),'%')",nativeQuery = true)
	Long countLesson(@Param("input") String input);
}
