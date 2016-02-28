package com.tools.qaa.providers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ServiceExceptionMapper implements ExceptionMapper<ServiceException> {

	@Override
	public Response toResponse(final ServiceException ex) {
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity(ex.getMessage())
				.type(MediaType.TEXT_PLAIN_TYPE)
				.build();
	}
}
