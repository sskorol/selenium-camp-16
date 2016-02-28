package com.blogspot.notes.automation.qa.utils;

import javaslang.control.Try;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

import java.io.File;

public final class ScreenshotUtils {

	public static void takeScreenshot(final WebDriver driver) {
		final WebDriver augmentedDriver = new Augmenter().augment(driver);
		final File screenshot = ((TakesScreenshot) augmentedDriver).
				getScreenshotAs(OutputType.FILE);
		Try.run(() -> FileUtils.copyFile(screenshot,
				new File("screen" + RandomStringUtils.randomNumeric(3) + ".png")));
	}

	private ScreenshotUtils() {
		throw new UnsupportedOperationException("Illegal access to private constructor");
	}
}
