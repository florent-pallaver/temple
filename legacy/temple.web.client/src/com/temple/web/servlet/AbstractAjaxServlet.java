package com.temple.web.servlet;

import com.temple.service.ServiceException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Base implementation for Servlet used to handle Ajax request.
 * <br>
 * By default it does not handle any request.
 * @see #handleRequest(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse) 
 * @see #createResponse(javax.servlet.http.HttpServletRequest) 
 */
public abstract class AbstractAjaxServlet extends AbstractTempleServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Util method to handle an ajax request and send a JSON response
	 * 
	 * @param request the ajax request
	 * @param response the servlet response
	 * @throws ServletException 
	 * @throws IOException 
	 * @see #createResponse(javax.servlet.http.HttpServletRequest) 
	 */
	protected final void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonResponse jr ;
		try {
			jr = this.createResponse(request) ;
		} catch (final ServiceException e) {
			jr = new JsonResponse(false, lb.getString(e)) ;
		}
		jr.write(response);
	}

	protected String getErrorMessage(ServiceException e) {
		return e.getLocaleKey() ;
	}
	
	/**
	 * Creates a specific {@JsonResponse} object for this service if it succeeds.
	 * 
	 * @param request the request
	 * @return the {@JsonResponse}
	 * @throws ServiceException if this service fails
	 * @throws ServletException TODOC
	 * @throws IOException TODOC
	 */
	protected abstract JsonResponse createResponse(HttpServletRequest request) throws ServiceException, ServletException, IOException ;
	
}
