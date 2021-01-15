package com.registrationsystem.workshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.registrationsystem.workshop.entities.Produto;
import com.registrationsystem.workshop.entities.UserSystem;

public interface UserRepository extends JpaRepository<UserSystem,Integer>{
	
	@Query(value="SELECT * FROM User_System u WHERE u.name_user = ?1 and u.password = ?2", nativeQuery = true)
	UserSystem findByUserAndPassoword(String user,String password);
	
	@Query(value="SELECT * FROM User_System u WHERE u.name_user = ?1", nativeQuery = true)
	UserSystem findByUser(String user);
}
