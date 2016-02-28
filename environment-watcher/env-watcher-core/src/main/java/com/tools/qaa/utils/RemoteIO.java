package com.tools.qaa.utils;

import com.tools.qaa.providers.ServiceException;

import java.io.File;

import static com.tools.qaa.utils.NativeLoader.loadLibrary;

public final class RemoteIO {

	public static void compressScreenshot(final String file, final long timeout) {
		new ScreenshotWatcher(new File(file), timeout).run();
	}

	public static String loadResource(final String fileName, final boolean setJavaPath) throws ServiceException {
		return loadLibrary(fileName, setJavaPath);
	}

	private RemoteIO() {
		throw new UnsupportedOperationException("Illegal access to private constructor");
	}
}
