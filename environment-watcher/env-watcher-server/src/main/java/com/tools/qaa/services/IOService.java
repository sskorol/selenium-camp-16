package com.tools.qaa.services;

import com.tools.qaa.utils.RemoteIO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/io")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class IOService {

	@POST
	@Path("/compressScreenshot")
	public Response compressScreenshot(@QueryParam("path") final String file,
	                                   @QueryParam("timeout") final long timeout) {
		RemoteIO.compressScreenshot(file, timeout);
		return Response.status(Response.Status.OK).build();
	}
}
