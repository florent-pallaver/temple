package com.temple.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Base implementation for Servlet used to handle Ajax request.
 * <br>
 * By default it handles GET and POST requests the same way.
 * @see AbstractAjaxServlet
 */
public abstract class AjaxRequestServlet extends AbstractAjaxServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.handleRequest(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.handleRequest(request, response);
	}

}
