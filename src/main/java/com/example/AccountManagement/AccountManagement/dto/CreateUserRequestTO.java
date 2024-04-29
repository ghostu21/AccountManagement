package com.example.AccountManagement.AccountManagement.dto;

import com.example.AccountManagement.AccountManagement.constant.UserRole;

import lombok.Data;

@Data
public class CreateUserRequestTO {
	
	private String userName;
	private String email;
	private String password;
	private String mobile;

	private String name;
	private String dob;
	
	private UserRole role;

}
