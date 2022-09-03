package com.example.lessonEnglish.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.lessonEnglish.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String>{
	@Query(value="select c from Customer c where c.email=:email")
	Optional<Customer> findByEmail(@Param("email") String email);
}
