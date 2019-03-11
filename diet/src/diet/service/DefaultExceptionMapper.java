package diet.service;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DefaultExceptionMapper implements ExceptionMapper<RuntimeException> {

	@Override
	public Response toResponse(RuntimeException t) {
		t.printStackTrace();
		return Response.serverError()
				.type(MediaType.TEXT_PLAIN_TYPE)
				.entity(t.getMessage())
				.build();
	}

}
