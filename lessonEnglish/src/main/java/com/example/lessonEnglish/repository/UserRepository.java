package com.example.lessonEnglish.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lessonEnglish.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, String>{

}
