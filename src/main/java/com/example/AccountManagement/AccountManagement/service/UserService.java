package com.example.AccountManagement.AccountManagement.service;

import com.example.AccountManagement.AccountManagement.dto.AuthResponseTO;
import com.example.AccountManagement.AccountManagement.dto.CreateUserRequestTO;
import com.example.AccountManagement.AccountManagement.dto.LoginRequestTO;
import com.example.AccountManagement.AccountManagement.model.User;

public interface UserService {
	
	AuthResponseTO registerUser(CreateUserRequestTO userDTO);

	User getUserByEmail(String email);

	AuthResponseTO userSignIn(LoginRequestTO req);

}
