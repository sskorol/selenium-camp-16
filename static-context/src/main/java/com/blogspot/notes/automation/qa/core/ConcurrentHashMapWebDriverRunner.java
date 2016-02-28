package com.blogspot.notes.automation.qa.core;

import com.blogspot.notes.automation.qa.utils.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.Thread.currentThread;

public class ConcurrentHashMapWebDriverRunner {

	private static final Map<Long, WebDriver> DRIVER_MAP =
			new ConcurrentHashMap<>();

	@BeforeMethod
	public void setUp() {
		DRIVER_MAP.putIfAbsent(currentThread().getId(),
				WebDriverFactory.getDriver(DesiredCapabilities.firefox()));
	}

	@AfterMethod
	public void tearDown() {
		if (getDriver() != null) {
			getDriver().quit();
			DRIVER_MAP.remove(currentThread().getId());
		}
	}

	public static WebDriver getDriver() {
		return DRIVER_MAP.get(currentThread().getId());
	}
}
