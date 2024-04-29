package com.example.AccountManagement.AccountManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AccountManagement.AccountManagement.dto.AuthResponseTO;
import com.example.AccountManagement.AccountManagement.dto.CreateUserRequestTO;
import com.example.AccountManagement.AccountManagement.dto.GetPaymentResponseTO;
import com.example.AccountManagement.AccountManagement.dto.LoginRequestTO;
import com.example.AccountManagement.AccountManagement.dto.PaymentRequestTO;
import com.example.AccountManagement.AccountManagement.model.Account;
import com.example.AccountManagement.AccountManagement.service.AccountService;
import com.example.AccountManagement.AccountManagement.service.PaymentService;

import com.example.AccountManagement.AccountManagement.service.UserService;

@RestController
@RequestMapping("/api")
public class ManagementController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/register")
	public ResponseEntity<AuthResponseTO> registerUser(@RequestBody CreateUserRequestTO userDTO) {




		AuthResponseTO authResponse = userService.registerUser(userDTO);
		


		return new ResponseEntity<>(authResponse, HttpStatus.CREATED);

	}
	
	
	@PostMapping("/signin")
	public ResponseEntity<AuthResponseTO> singnin(@RequestBody LoginRequestTO req) {

		
		AuthResponseTO authResponse = userService.userSignIn(req);
		
		return new ResponseEntity<>(authResponse, HttpStatus.OK);

	}
	
	
	@GetMapping("/v1/accounts")
	public ResponseEntity<List<Account>>  getAccounts(@RequestHeader("Authorization") String jwt) {
		List<Account>accounts=accountService.getAccounts(jwt);
		
		return new ResponseEntity<>(accounts, HttpStatus.OK);
	}
	
	
	@GetMapping("/v1/payments")
	public ResponseEntity<List<GetPaymentResponseTO>> getPayments(@RequestHeader("Authorization") String jwt) {
		List<GetPaymentResponseTO>paymentResponse=paymentService.getPaymentsUser(jwt);
		
		return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
		
	}
	
	
	@PostMapping("/v1/payments")
	public void createPayment(@RequestHeader("Authorization") String jwt,@RequestBody PaymentRequestTO req) {
		
		paymentService.createPayment(req, jwt);
	}
	
	
	
	
	
	
	
	

}
