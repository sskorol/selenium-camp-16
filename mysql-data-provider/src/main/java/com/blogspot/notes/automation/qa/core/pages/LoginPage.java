package com.blogspot.notes.automation.qa.core.pages;

import com.blogspot.notes.automation.qa.core.model.UsersEntity;
import ru.yandex.qatools.allure.annotations.Step;

import static com.blogspot.notes.automation.qa.core.pages.PageObjectSupplier.$;

public class LoginPage {

	public HomePage loginWith(final UsersEntity user) {
		return typeEmail(user.getEmail())
				.typePassword(user.getPassword())
				.clickLoginButton();
	}

	@Step("Type email address = {0}.")
	public LoginPage typeEmail(final String email) {
		return this;
	}

	@Step("Type password = {0}.")
	public LoginPage typePassword(final String password) {
		return this;
	}

	@Step("Click \"Login\" button.")
	public HomePage clickLoginButton() {
		return $(HomePage.class);
	}
}
