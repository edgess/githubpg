package com.example.and.exception;

public class PermissionException extends Exception {
	private static final long serialVersionUID = 1L;

	public PermissionException() {
		super("Permission error");
	}

	public PermissionException(String message) {
		super(message);
	}

}