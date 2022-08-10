package com.example.lessonEnglish.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.lessonEnglish.entity.Course;
import com.example.lessonEnglish.projections.CourseProjection;
import com.example.lessonEnglish.projections.LessonCourseProjection;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
	@Query("select c from Course c where c.id in :id")
	List<Course> findListCourse(@Param("id") List<String> id);

	@Query(value = "select c.id as id, c.name as name,c.description as description ,c.id_dlfileentry as idDlFileEntry,count(cl.lesson_id) as countLesson from course c LEFT JOIN   course_lesson cl ON c.id=cl.course_id where upper(c.name) like concat(concat('%',upper(:input)),'%') or upper(c.description) like concat(concat('%',upper(:input)),'%') group by c.name order by c.update_date desc limit :page, :size",nativeQuery = true)
	List<CourseProjection> findAllCourse(@Param("input") String input,@Param("page") Integer page,@Param("size") Integer size);
	
	@Query(value="select l.name as name from course_lesson cl inner join lesson l on cl.lesson_id=l.id where cl.course_id=:id order by l.update_date desc",nativeQuery = true)
	List<LessonCourseProjection> findLessonByIdCourse(@Param("id") String id);
	
	@Query(value="select count(*) from Course c where upper(c.name) like concat(concat('%',upper(:input)),'%') or upper(c.description) like concat(concat('%',upper(:input)),'%')",nativeQuery = true)
	Long countCourse(@Param("input") String input);
}
