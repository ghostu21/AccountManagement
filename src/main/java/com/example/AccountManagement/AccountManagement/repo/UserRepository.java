package com.example.AccountManagement.AccountManagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AccountManagement.AccountManagement.model.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {



	User findByEmail(String email);

	User findByMobile(String mobile);

	boolean existsByEmail(String email);

	boolean existsByMobile(String mobile);

	// Other custom query methods if needed
}
