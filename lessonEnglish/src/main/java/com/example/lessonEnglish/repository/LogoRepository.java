package com.example.lessonEnglish.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lessonEnglish.entity.Logo;

@Repository
public interface LogoRepository extends JpaRepository<Logo, String>{

}
