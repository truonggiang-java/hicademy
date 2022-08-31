package com.example.lessonEnglish.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.lessonEnglish.entity.Tags;

@Repository
public interface TagsRepository extends JpaRepository<Tags, String>{
	@Query(value ="SELECT * FROM tags t where t.param=:param ORDER BY RAND() LIMIT :number",nativeQuery = true)
	List<Tags> findListTagsRandom(@Param("param") String param,@Param("number") Integer number);
	
	@Query(value="SELECT t.param as param FROM tags t where t.param is not null group by t.param ORDER BY RAND()",nativeQuery = true)
	List<String> randomParamTags();
}
