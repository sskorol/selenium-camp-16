package com.tools.qaa.services;

import com.tools.qaa.providers.ServiceException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static com.tools.qaa.utils.ProcessUtils.*;

@Path("/shutdown")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShutdownService {

	@POST
	@Path("/javaTasks")
	public Response killJavaTasks(final List<String> javaTasks,
	                              @QueryParam("timeout") final int timeout) throws ServiceException {
		return Response.status(Response.Status.OK)
				.entity(executeProcess(ProcessName.CMD, JAVA_TASKS_KILL, javaTasks, timeout))
				.build();
	}

	@POST
	@Path("/commonTasks")
	public Response killCommonTasks(final List<String> commonTasks,
	                                @QueryParam("timeout") final int timeout) throws ServiceException {
		return Response.status(Response.Status.OK)
				.entity(executeProcess(ProcessName.CMD, COMMON_TASKS_KILL, commonTasks, timeout))
				.build();
	}
}
