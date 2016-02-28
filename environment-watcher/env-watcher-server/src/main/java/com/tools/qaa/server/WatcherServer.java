package com.tools.qaa.server;

import com.tools.qaa.providers.ServiceException;
import javaslang.control.Try;
import org.apache.commons.cli.*;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

import java.net.InetAddress;
import java.net.URI;

import static com.tools.qaa.utils.NativeLoader.WEBP_LIB_NAME;
import static com.tools.qaa.utils.NativeLoader.loadLibrary;
import static org.apache.commons.lang.math.NumberUtils.toInt;

public class WatcherServer {
	private static final Options COMMON_OPTIONS = new Options()
			.addOption("port", true, "remote watcher port");
	private static final int DEFAULT_PORT = 4041;

	public static void startServer(final int port) throws Exception {
		final URI baseUri = URI.create("http://" + InetAddress.getLocalHost().getHostAddress() + ":" +
				(port > 0 ? port : DEFAULT_PORT));
		final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri,
				new WatcherConfiguration());

		System.in.read();
		server.shutdown();
	}

	public static void main(final String[] args) throws ServiceException {
		loadLibrary(WEBP_LIB_NAME);
		Try.of(() -> new DefaultParser().parse(COMMON_OPTIONS, args))
				.onSuccess(cmd -> Try.run(() -> startServer(toInt(cmd.getOptionValue("port"))))
						.orElseThrow(ex -> new IllegalArgumentException("Can't start server", ex)));
	}
}
