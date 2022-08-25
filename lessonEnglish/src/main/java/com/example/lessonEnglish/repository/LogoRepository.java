package com.example.lessonEnglish.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.lessonEnglish.entity.Logo;

@Repository
public interface LogoRepository extends JpaRepository<Logo, String>{
	@Query(value="select * from Logo u where u.file_name=:name",nativeQuery = true)
	Logo findByNameLogo(@Param("name") String name);
}
