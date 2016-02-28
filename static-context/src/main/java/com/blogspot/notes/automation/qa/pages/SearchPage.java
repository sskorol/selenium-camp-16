package com.blogspot.notes.automation.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class SearchPage extends BasePage {

	private By inputSearch = By.id("lst-ib");
	private By linkResults = By.cssSelector(".r>a");

	public SearchPage searchFor(final String text) {
		setText(inputSearch, text + Keys.ENTER);
		waitForItemsAppearance(linkResults);
		return this;
	}

	public String getSearchInputValue() {
		return getValue(inputSearch);
	}
}
