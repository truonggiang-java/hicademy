package com.example.lessonEnglish.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.lessonEnglish.entity.DlFileEntry;
import com.example.lessonEnglish.entity.Tags;

@Repository
public interface TagsRepository extends JpaRepository<Tags, String>{
	@Query(value ="SELECT * FROM tags t where t.param=:param ORDER BY RAND() LIMIT :number",nativeQuery = true)
	List<Tags> findListTagsRandom(@Param("param") String param,@Param("number") Integer number);
	
	@Query(value="SELECT t.param as param FROM tags t where t.param is not null group by t.param ORDER BY RAND()",nativeQuery = true)
	List<String> randomParamTags();
	
	
	@Query("select d from Tags d  where upper(d.fileName) like concat(concat('%',upper(:input)),'%') order by d.updatedDate desc")
	Page<Tags> findAllTags(@Param("input") String input,Pageable page);
	
	@Query("select d from Tags d where d.fileName=:name")
	Tags findTagsByName(@Param("name") String name);
	
	@Query("select d from Tags d where d.id in :id")
	List<Tags> findListTags(@Param("id") List<String> id);
}
