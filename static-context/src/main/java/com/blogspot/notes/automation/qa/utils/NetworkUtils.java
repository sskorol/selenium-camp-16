package com.blogspot.notes.automation.qa.utils;

import javaslang.control.Try;

import java.net.InetAddress;

public final class NetworkUtils {

	public static String getIp() {
		return Try.of(() -> InetAddress.getLocalHost().getHostAddress())
				.orElseThrow((ex) -> new IllegalArgumentException("Can't get ip", ex));
	}

	private NetworkUtils() {
		throw new UnsupportedOperationException("Illegal access to private constructor");
	}
}
