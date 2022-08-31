package com.example.lessonEnglish.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.lessonEnglish.entity.DlFileEntry;

@Repository
public interface DlFileEntryRepository  extends JpaRepository<DlFileEntry, String>{
	@Query("select d from DlFileEntry d  where upper(d.fileName) like concat(concat('%',upper(:input)),'%') order by d.updatedDate desc")
	Page<DlFileEntry> findAllDlFileEntry(@Param("input") String input,Pageable page);
	
	@Query("select d from DlFileEntry d where d.id in :id")
	List<DlFileEntry> findListDlFileEntry(@Param("id") List<String> id);
	
	@Query("select d from DlFileEntry d where d.fileName=:name")
	DlFileEntry findListDlFileEntryByName(@Param("name") String name);
}
