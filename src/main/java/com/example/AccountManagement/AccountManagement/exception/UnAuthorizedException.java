package com.example.AccountManagement.AccountManagement.exception;

public class UnAuthorizedException extends RuntimeException {

	private static final long serialVersionUID = -4740177348008017038L;
	
	public UnAuthorizedException(String message) {
		super(message);
	}

}
