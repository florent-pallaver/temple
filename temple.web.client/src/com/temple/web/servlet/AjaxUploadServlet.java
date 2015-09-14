package com.temple.web.servlet;

import com.temple.service.ServiceException;
import com.temple.service.cdi.ApplicationBean;
import com.temple.util.TempleUtil;
import com.temple.util.file.LocalUploadedFile;
import com.temple.util.file.UploadedFile;
import com.temple.web.cdi.WebConfiguration;
import java.io.File;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Base implementation for Servlet used to handle Ajax upload requests.
 * <br>
 * @see #handle(UploadedFile) 
 */
public abstract class AjaxUploadServlet extends AbstractAjaxServlet {

	private static final long serialVersionUID = 1L;

	@Inject
	@ApplicationBean
	private WebConfiguration wc ;
	
	@Override
	protected final void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.handleRequest(request, response);
	}

	@Override
	protected final JsonResponse createResponse(HttpServletRequest request) throws ServiceException, ServletException, IOException {
		final Part part = request.getPart("file");
		final String fn = Long.toString(TempleUtil.random(0, Long.MAX_VALUE), 36) + "_" + part.getSubmittedFileName() ;
		final File pf = new File(this.wc.getTempFolder(), fn);
		part.write(fn);
		try {
			this.handle(new LocalUploadedFile(part.getSubmittedFileName(), pf));
		} finally {
			pf.delete() ;
		}
		return new JsonResponse(true, "") ;
	}
	
	protected abstract void handle(UploadedFile luf) throws ServiceException;
	
}
