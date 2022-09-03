package com.example.lessonEnglish.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.lessonEnglish.entity.Verification;

@Repository
public interface VerificationRepository extends JpaRepository<Verification, String>{
	@Query(value="select * from Verification v where v.otp=:otp",nativeQuery=true)
	Verification findOtp(@Param("otp") String otp);
	
	
	@Query(value="select * from Verification v where v.email=:email",nativeQuery=true)
	Verification findEmail(@Param("email") String email);
	
	@Query(value="select * from Verification v where v.otp=:otp and v.email=:email",nativeQuery=true)
	Verification findVerificationByOtpAndEmail(@Param("otp") String otp,@Param("email") String email);
}
