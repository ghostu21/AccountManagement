package com.example.AccountManagement.AccountManagement.dto;

import lombok.Data;

@Data
public class AuthResponseTO {
	
	private String jwt;

	private String message;
	private String role;
	
	private String accountId;

}
