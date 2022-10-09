package com.example.lessonEnglish.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.lessonEnglish.entity.Customer;
import com.example.lessonEnglish.entity.Users;
import com.example.lessonEnglish.projections.CustomerProjection;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String>{
	@Query(value="select c from Customer c where c.email=:email")
	Optional<Customer> findByEmail(@Param("email") String email);
	
	@Query(value="select c.id as id, c.name as name, c.gender as gender, c.date_of_birth as birthday, c.address as address, c.email as emal, c.id_logo as logo,c.telephone as telephone, c.role as role,sum(le.point) as sum from Customer c left join Learn le on c.id=le.id_user where upper(c.name) like concat(concat('%',upper(:input)),'%') or upper(c.address) like concat(concat('%',upper(:input)),'%') or upper(c.email) like concat(concat('%',upper(:input)),'%') group by c.id, c.name,c.email,c.telephone,c.id_logo,c.role,c.gender,c.date_of_birth,c.address order by c.update_date desc",nativeQuery = true)
	List<CustomerProjection> findAllCustomer(@Param("input") String input);
	
	@Query("select c from Customer c where c.id in :id")
	List<Customer> findListIdUser(@Param("id") List<String> id);
	
	@Query(value="select * from Customer u where u.id=:id",nativeQuery = true)
	Customer findByIdUser(@Param("id") String id);
}
