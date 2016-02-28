package com.blogspot.notes.automation.qa.pages;

import com.esotericsoftware.reflectasm.ConstructorAccess;
import javaslang.control.Try;

import static com.blogspot.notes.automation.qa.core.StaticWebDriverRunner.getDriver;

public final class PageObjectSupplier {

	public static <T> T $(Class<T> pageObject) {
		return ConstructorAccess.get(pageObject).newInstance();
	}

	public static SearchPage loadSiteUrl(final String url) {
		Try.run(() -> getDriver().navigate().to(url))
				.orElseThrow(ex -> new IllegalArgumentException("Unable to navigate to specified URL", ex));
		return $(SearchPage.class);
	}

	private PageObjectSupplier() {
		throw new UnsupportedOperationException("Illegal access to private constructor");
	}
}
