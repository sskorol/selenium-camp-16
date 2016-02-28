package com.blogspot.notes.automation.qa.core;

import com.blogspot.notes.automation.qa.utils.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

import static com.blogspot.notes.automation.qa.utils.ScreenshotUtils.takeScreenshot;

public class ThreadLocalWebDriverRunner {

	private static final ThreadLocal<WebDriver> WEB_DRIVER_THREAD_LOCAL =
			new ThreadLocal<>();

	@BeforeMethod
	public void setUp(ITestContext context, Method method) {
		WEB_DRIVER_THREAD_LOCAL.set(
				WebDriverFactory.getDriver(DesiredCapabilities.chrome()));
	}

	@AfterMethod
	public void tearDown() {
		if (getDriver() != null) {
			takeScreenshot(getDriver());
			getDriver().quit();
			WEB_DRIVER_THREAD_LOCAL.remove();
		}
	}

	public static WebDriver getDriver() {
		return WEB_DRIVER_THREAD_LOCAL.get();
	}
}
