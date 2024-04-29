package com.example.AccountManagement.AccountManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.AccountManagement.AccountManagement.constant.UserRole;
import com.example.AccountManagement.AccountManagement.exception.UnAuthorizedException;
import com.example.AccountManagement.AccountManagement.model.Account;
import com.example.AccountManagement.AccountManagement.model.User;
import com.example.AccountManagement.AccountManagement.repo.AccountRepository;
import com.example.AccountManagement.AccountManagement.security.JwtProvider;

@Service
public class AccountServiceImpl implements AccountService {
	
	
	@Autowired
	private UserService userService;
	@Autowired
	private AccountRepository accountRepository;
	


	@Autowired
	private JwtProvider jwtProvider;
	
	@Override
	public List<Account> getAccounts(String jwt) {
		
		String email = jwtProvider.getEmailFromJwtToken(jwt);
		User user = userService.getUserByEmail(email);
		
		
		List<Account>accounts=accountRepository.findAll();
		
		return accounts;
		
	}
	
	
	
	

}
