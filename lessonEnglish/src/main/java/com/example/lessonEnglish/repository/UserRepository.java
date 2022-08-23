package com.example.lessonEnglish.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.lessonEnglish.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, String>{
	
	@Query(value="select * from Users u where u.email=:email",nativeQuery = true)
	Optional<Users> findByEmail(@Param("email") String email);
}
