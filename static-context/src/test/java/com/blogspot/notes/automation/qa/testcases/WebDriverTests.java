package com.blogspot.notes.automation.qa.testcases;

import com.blogspot.notes.automation.qa.core.StaticWebDriverRunner;
import com.blogspot.notes.automation.qa.pages.SearchPage;
import org.testng.annotations.Test;

import static com.blogspot.notes.automation.qa.pages.PageObjectSupplier.$;
import static com.blogspot.notes.automation.qa.pages.PageObjectSupplier.loadSiteUrl;
import static org.testng.Assert.assertEquals;

public class WebDriverTests extends StaticWebDriverRunner {

	@Test
	public void searchForAutomationInGoogle() {
		loadSiteUrl("https://google.com.ua")
				.searchFor("automation");

		assertEquals($(SearchPage.class).getSearchInputValue(), "automation");
	}

	@Test
	public void searchForQualityInGoogle() {
		loadSiteUrl("https://google.com.ua")
				.searchFor("quality");

		assertEquals($(SearchPage.class).getSearchInputValue(), "quality");
	}
}
