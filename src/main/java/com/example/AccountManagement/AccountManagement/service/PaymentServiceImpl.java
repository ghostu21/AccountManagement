package com.example.AccountManagement.AccountManagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AccountManagement.AccountManagement.dto.GetPaymentResponseTO;
import com.example.AccountManagement.AccountManagement.dto.PaymentRequestTO;
import com.example.AccountManagement.AccountManagement.exception.InvalidExecption;
import com.example.AccountManagement.AccountManagement.exception.NotFoundExecption;
import com.example.AccountManagement.AccountManagement.model.Account;
import com.example.AccountManagement.AccountManagement.model.Payment;
import com.example.AccountManagement.AccountManagement.model.User;
import com.example.AccountManagement.AccountManagement.repo.AccountRepository;
import com.example.AccountManagement.AccountManagement.repo.PaymentRepository;
import com.example.AccountManagement.AccountManagement.security.JwtProvider;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private UserService userService;
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	

	@Autowired
	private JwtProvider jwtProvider;
	
	@Override
	public void createPayment(PaymentRequestTO req,String jwt) {
		
		String email = jwtProvider.getEmailFromJwtToken(jwt);
		User user = userService.getUserByEmail(email);
		
		Account sender=accountRepository.findByUser(user);
		
		if(sender.getBalance()<req.getAmount()) {
			throw new InvalidExecption("Amount is not sufficient");
		}
		
		Account reciever=accountRepository.getReferenceById(req.getAccountID());
		if(reciever==null) {
			throw new NotFoundExecption("Reciever Account not found");
		}
		
		Payment payment=new Payment();
		
		payment.setFromAccountId(sender.getId());
		payment.setToAccountId(reciever.getId());
		payment.setAmount(req.getAmount());
		
		payment=paymentRepository.save(payment);
		
		reciever.setBalance(reciever.getBalance()+req.getAmount());
		
		sender.setBalance(sender.getBalance()-req.getAmount());
		
		accountRepository.save(reciever);
		
		accountRepository.save(sender);
		
		
	}
	
	@Override
	public List<GetPaymentResponseTO> getPaymentsUser(String jwt) {
		
		String email = jwtProvider.getEmailFromJwtToken(jwt);
		User user = userService.getUserByEmail(email);
		
		Account userAccount=accountRepository.findByUser(user);
		
		List<GetPaymentResponseTO> paymentResponses=new ArrayList<GetPaymentResponseTO>();
		
		
		List<Payment>payments=paymentRepository.findByFromAccountIdOrToAccountId(userAccount.getId(),userAccount.getId());
		GetPaymentResponseTO paymentResponse=null;
	
		for(Payment payment:payments) {
			paymentResponse=new GetPaymentResponseTO();
			paymentResponse.setAccount(payment.getFromAccountId());
			paymentResponse.setToAccount(payment.getToAccountId());
			paymentResponse.setAmount(payment.getAmount());
			if(payment.getFromAccountId()==userAccount.getId()) {
				paymentResponse.setDirection("outgoing");				
			}else {
				paymentResponse.setDirection("incoming");
			}
			
			
			paymentResponses.add(paymentResponse);
			
		}
		
		return paymentResponses;
		
	}

}
