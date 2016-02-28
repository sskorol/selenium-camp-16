package com.blogspot.notes.automation.qa.utils;

import javaslang.control.Try;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.blogspot.notes.automation.qa.utils.NetworkUtils.getIp;
import static java.lang.String.format;

public class WebDriverFactory {

	private static final String HUB_URL = "http://%s:%d/wd/hub";
	private static final long IMPLICIT_WAIT_TIMEOUT = 3;
	private static final int DEFAULT_HUB_PORT = 4444;

	public static WebDriver getDriver(final Capabilities capabilities, final int hubPort) {
		return Try.of(() -> new RemoteWebDriver(new URL(format(HUB_URL, getIp(), hubPort)), capabilities))
				.onSuccess(driver -> driver.manage().timeouts()
						.implicitlyWait(IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS))
				.orElseThrow((ex) -> new IllegalArgumentException("Can't create WebDriver", ex));
	}

	public static WebDriver getDriver(final Capabilities capabilities) {
		return getDriver(capabilities, Optional.ofNullable(System.getProperty("hubPort"))
				.map(Integer::valueOf)
				.orElse(DEFAULT_HUB_PORT));
	}
}
