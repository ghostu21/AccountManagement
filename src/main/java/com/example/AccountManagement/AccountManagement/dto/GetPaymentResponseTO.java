package com.example.AccountManagement.AccountManagement.dto;

import lombok.Data;

@Data
public class GetPaymentResponseTO {
	private String account;
	
	private String toAccount;
	
	private Long amount;
	
	private String direction;

}
