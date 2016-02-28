package com.tools.qaa.server;

import com.tools.qaa.services.CommandLineService;
import com.tools.qaa.services.IOService;
import com.tools.qaa.services.ShutdownService;
import com.tools.qaa.providers.ServiceExceptionMapper;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class WatcherConfiguration extends ResourceConfig {
	public WatcherConfiguration() {
		super(CommandLineService.class, ShutdownService.class, JacksonFeature.class,
				ServiceExceptionMapper.class, IOService.class);
	}
}
