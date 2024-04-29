package com.example.AccountManagement.AccountManagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.AccountManagement.AccountManagement.model.Account;
import com.example.AccountManagement.AccountManagement.model.User;


public interface AccountRepository extends JpaRepository<Account, String> {
	
	Account findByUser(User user);

}
