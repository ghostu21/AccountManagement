package com.example.AccountManagement.AccountManagement.service;

import java.util.List;

import com.example.AccountManagement.AccountManagement.dto.GetPaymentResponseTO;
import com.example.AccountManagement.AccountManagement.dto.PaymentRequestTO;

public interface PaymentService {

	void createPayment(PaymentRequestTO req, String jwt);

	List<GetPaymentResponseTO> getPaymentsUser(String jwt);

}
