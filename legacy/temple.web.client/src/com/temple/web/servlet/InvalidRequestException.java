package com.temple.web.servlet;

import com.temple.service.ServiceException;

/**
 * Exception to be thrown whenever an invalid request is sent to a Servlet.
 * 
 * @author flominou
 * @version 1.0
 */
public class InvalidRequestException extends ServiceException {
	
	private static final long serialVersionUID = 1L ;

	/**
	 * Constructor.
	 */
	public InvalidRequestException() {
		this(null);
	}
	
	/**
	 * Constructor.
	 * 
	 * @param cause what caused the invalid request
	 */
	public InvalidRequestException(Throwable cause) {
		super(cause);
	}
	
}
