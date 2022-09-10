package com.example.lessonEnglish.repository;

import java.util.List;
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
	
	@Query(value="select * from Customer c where upper(c.name) like concat(concat('%',upper(:input)),'%') or upper(c.address) like concat(concat('%',upper(:input)),'%') or upper(c.email) like concat(concat('%',upper(:input)),'%') order by c.update_date desc",nativeQuery = true)
	List<Customer> findAllCustomer(@Param("input") String input);
}
