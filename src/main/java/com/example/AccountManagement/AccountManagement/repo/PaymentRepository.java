package com.example.AccountManagement.AccountManagement.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.AccountManagement.AccountManagement.model.Payment;
import com.example.AccountManagement.AccountManagement.model.User;



public interface PaymentRepository extends JpaRepository<Payment, Long> {
	
	List<Payment> findByFromAccountIdOrToAccountId(String accountId,String accountId1);
	
//	List<Payment> findByToAccountId(String accountId);

}
