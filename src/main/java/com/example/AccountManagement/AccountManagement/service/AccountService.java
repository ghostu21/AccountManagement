package com.example.AccountManagement.AccountManagement.service;

import java.util.List;

import com.example.AccountManagement.AccountManagement.model.Account;

public interface AccountService {

	List<Account> getAccounts(String jwt);

}
