package com.example.and.exception;

public class AnnoRoleException extends Exception {
	private static final long serialVersionUID = 1L;

	public AnnoRoleException() {
		super("Role error");
	}

	public AnnoRoleException(String message) {
		super(message);
	}

}