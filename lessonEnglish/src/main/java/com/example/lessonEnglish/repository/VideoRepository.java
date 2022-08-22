package com.example.lessonEnglish.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.lessonEnglish.entity.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, String> {
	@Query("select c from Video c where c.id in :id")
	List<Video> deleteListVideo(@Param("id") List<String> id);

	@Query(value = "select * from Video c where upper(c.name) like concat(concat('%',upper(:input)),'%') or upper(c.description) like concat(concat('%',upper(:input)),'%') or upper(c.param) like concat(concat('%',upper(:input)),'%') order by c.update_date desc", nativeQuery = true)
	List<Video> findAllVideo(@Param("input") String video);

	@Query(value = "select * from Video c where c.param = :input", nativeQuery = true)
	List<Video> findVideoByParam(@Param("input") String inout);
}
