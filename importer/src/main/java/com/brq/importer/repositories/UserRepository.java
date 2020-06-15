package com.brq.importer.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brq.importer.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	boolean existsByEmailAndCompanyId(String email, Integer companyId);
	
	List<User> findByEmail(String email);
	
	List<User> findByCompanyId(Integer companyId);
	
}
