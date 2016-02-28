package com.tools.qaa.entities;

public enum CommonTask {
	FIREFOX("firefox"),
	CHROME("chrome"),
	IE_BROWSER("iexplore"),
	IE_DRIVER("IEDriver"),
	HAR_STORAGE("python");

	private String name;

	CommonTask(final String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}
