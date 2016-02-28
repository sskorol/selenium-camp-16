package com.tools.qaa.services;

import com.tools.qaa.providers.ServiceException;
import com.tools.qaa.utils.NativeLoader;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Collections;

import static com.tools.qaa.utils.ProcessUtils.*;
import static com.tools.qaa.utils.RemoteIO.*;
import static java.lang.String.format;

@Path("/cmd")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommandLineService {

	@POST
	@Path("/startScript")
	public Response startScript(@QueryParam("path") final String path,
	                            @QueryParam("timeout") final int timeout) throws ServiceException {
		return Response.status(Response.Status.OK)
				.entity(executeProcess(ProcessName.PSEXEC, path, Collections.EMPTY_LIST, timeout))
				.build();
	}

	@POST
	@Path("/takeScreenshot")
	public Response takeScreenshot(@QueryParam("path") final String path,
	                               @QueryParam("name") final String name,
	                               @QueryParam("timeout") final int timeout) throws ServiceException {
		return Response.status(Response.Status.OK)
				.entity(executeProcess(ProcessName.CMD,
						loadResource(NativeLoader.CMD_CAPTURE_NAME, false) + format(SCREENSHOT_ARGS, name, path),
						Collections.EMPTY_LIST, timeout))
				.build();
	}
}
