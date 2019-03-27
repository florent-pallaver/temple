package com.temple.credentials.service;


public final class CreateUserIdentityException extends CredentialsException {

	private static final long serialVersionUID = 1L;

	public CreateUserIdentityException(String login, Throwable cause) {
		super(cause, login);
	}
}
