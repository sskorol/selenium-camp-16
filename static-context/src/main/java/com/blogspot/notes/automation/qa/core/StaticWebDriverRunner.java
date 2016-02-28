package com.blogspot.notes.automation.qa.core;

import com.blogspot.notes.automation.qa.utils.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.blogspot.notes.automation.qa.utils.ScreenshotUtils.takeScreenshot;

public class StaticWebDriverRunner {

	private static WebDriver driver;

	@BeforeMethod
	public void setUp() {
		driver = WebDriverFactory.getDriver(DesiredCapabilities.chrome());
	}

	@AfterMethod
	public void tearDown() {
		takeScreenshot(driver);
		driver.quit();
	}

	public static WebDriver getDriver() {
		return driver;
	}
}
