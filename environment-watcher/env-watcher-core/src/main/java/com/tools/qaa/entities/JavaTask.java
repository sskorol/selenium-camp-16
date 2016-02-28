package com.tools.qaa.entities;

public enum JavaTask {
	SELENIUM("selenium"),
	SIKULI("sikuli"),
	BROWSER_MOB_PROXY("proxy");

	private String name;

	JavaTask(final String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}
