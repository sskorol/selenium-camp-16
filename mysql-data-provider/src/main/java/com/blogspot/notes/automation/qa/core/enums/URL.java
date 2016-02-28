package com.blogspot.notes.automation.qa.core.enums;

import static com.blogspot.notes.automation.qa.core.utils.PropertyUtils.Constants.*;

public enum URL {
	DEV(DEV_URL);

	private String address;

	URL(final String address) {
		this.address = address;
	}

	public String toString() {
		return address;
	}
}
