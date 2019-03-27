package diet.service;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ServiceExceptionMapper implements ExceptionMapper<ServiceException> {

	@Override
	public Response toResponse(ServiceException e) {
		e.printStackTrace();
		return Response.serverError()
				.type(MediaType.APPLICATION_JSON)
				.entity(e)
				.build();
	}

}
