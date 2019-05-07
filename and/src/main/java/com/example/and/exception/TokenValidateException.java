package com.example.and.exception;

public class TokenValidateException extends Exception {
	private static final long serialVersionUID = 1L;

	public TokenValidateException() {
		super("TokenValidate error");
	}

	public TokenValidateException(String message) {
		super(message);
	}

}