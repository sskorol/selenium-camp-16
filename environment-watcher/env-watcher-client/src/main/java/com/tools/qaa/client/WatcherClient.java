package com.tools.qaa.client;

import com.tools.qaa.providers.ServiceException;
import com.tools.qaa.entities.CommonTask;
import com.tools.qaa.entities.JavaTask;
import javaslang.control.Try;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class WatcherClient {

	private Client client;
	private WebTarget service;

	private final Logger LOGGER = Logger.getLogger(getClass().getName());

	public WatcherClient(final String ipOrDomain, final int port) {
		this.client = ClientBuilder.newBuilder()
				.register(JacksonFeature.class)
				.build();
		this.service = client.target("http://" + ipOrDomain + ":" + port);
	}

	public void killJavaTasks(final long timeout, final List<String> tasks) throws ServiceException {
		Try.of(() -> service.path("shutdown")
				.path("javaTasks")
				.queryParam("timeout", timeout)
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(tasks))
				.readEntity(String.class))
				.onSuccess(LOGGER::info)
				.orElseThrow(ex -> new ServiceException("Unable to kill java tasks", ex));
	}

	public void killCommonTasks(final long timeout, final List<String> tasks) throws ServiceException {
		Try.of(() -> service.path("shutdown")
				.path("commonTasks")
				.queryParam("timeout", timeout)
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(tasks))
				.readEntity(String.class))
				.onSuccess(LOGGER::info)
				.orElseThrow(ex -> new ServiceException("Unable to kill java tasks", ex));
	}

	public void killJavaTasks(final long timeout, final JavaTask... tasks) throws ServiceException {
		killJavaTasks(timeout, Arrays.asList(tasks)
				.stream()
				.map(JavaTask::toString)
				.collect(Collectors.toList()));
	}

	public void killCommonTasks(final long timeout, final CommonTask... tasks) throws ServiceException {
		killCommonTasks(timeout, Arrays.asList(tasks)
				.stream()
				.map(CommonTask::toString)
				.collect(Collectors.toList()));
	}

	public void startScript(final String path, final long timeout) throws ServiceException {
		Try.of(() -> service.path("cmd")
				.path("startScript")
				.queryParam("path", path)
				.queryParam("timeout", timeout)
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(null))
				.readEntity(String.class))
				.onSuccess(LOGGER::info)
				.orElseThrow(ex -> new ServiceException("Unable to start a script", ex));
	}

	public void takeScreenshot(final String path, final String name, final long timeout) throws ServiceException {
		Try.of(() -> service.path("cmd")
				.path("takeScreenshot")
				.queryParam("path", path)
				.queryParam("name", name)
				.queryParam("timeout", timeout)
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(null)))
				.onSuccess(Response::close)
				.orElseThrow(ex -> new ServiceException("Unable to take a screenshot", ex));
	}

	public void compressScreenshot(final String path, final long timeout) throws ServiceException {
		Try.of(() -> service.path("io")
				.path("compressScreenshot")
				.queryParam("path", path)
				.queryParam("timeout", timeout)
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(null)))
				.onSuccess(Response::close)
				.orElseThrow(ex -> new ServiceException("Unable to convert a screenshot", ex));
	}

	public void disconnect() {
		Optional.ofNullable(client).ifPresent(Client::close);
	}

	public static void main(String[] args) throws ServiceException {
		WatcherClient client = new WatcherClient("yourHostIpOrDomain", 4041);
		client.killCommonTasks(10, CommonTask.FIREFOX, CommonTask.IE_BROWSER);
		client.killJavaTasks(10, JavaTask.SELENIUM);
		client.startScript("C:\\Grid\\start_hub.bat", 10);
		client.takeScreenshot("C:\\", "screen.png", 10);
		client.compressScreenshot("C:\\screen.png", 10);
		client.disconnect();
	}
}
