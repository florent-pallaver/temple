package com.temple.credentials.service;

import com.temple.LocaleViewableTempleException;
import com.temple.Module;
import java.io.Serializable;

public abstract class CredentialsException extends LocaleViewableTempleException {

	private static final long serialVersionUID = 1L;

	public CredentialsException(Serializable... parameters) {
		super(parameters, Module.SERVICE);
	}

	public CredentialsException(Throwable cause, Serializable... parameters) {
		super(parameters, Module.SERVICE, cause);
	}
}
