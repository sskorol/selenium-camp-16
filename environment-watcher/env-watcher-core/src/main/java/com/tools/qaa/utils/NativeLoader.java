package com.tools.qaa.utils;

import com.tools.qaa.providers.ServiceException;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.apache.commons.io.FilenameUtils.separatorsToSystem;

public final class NativeLoader {

	public static final String WEBP_LIB_NAME = "webp-imageio.dll";
	public static final String CMD_CAPTURE_NAME = "CmdCapture.exe";

	private NativeLoader() {
		throw new UnsupportedOperationException("Illegal access to private constructor");
	}

	public static String loadLibrary(final String library, final boolean setJavaPath) throws ServiceException {
		Path filePath;

		try {
			filePath = unpackLibrary(library);
			if (setJavaPath)
				setLibPath(filePath.getParent().toString());
		} catch (Exception ex) {
			throw new ServiceException("Could not find library " + library + " as resource", ex);
		}

		return filePath.toString();
	}

	public static String loadLibrary(final String library) throws ServiceException {
		return loadLibrary(library, true);
	}

	public static Path unpackLibrary(final String library) throws IOException, NoSuchFieldException, IllegalAccessException {
		final File tmpDir = new File(System.getProperty("java.io.tmpdir"));
		if (!tmpDir.exists()) {
			tmpDir.mkdir();
		}

		try (final InputStream iStream = ClassLoader.getSystemResourceAsStream("lib/" + library);
		     final FileOutputStream foStream = new FileOutputStream(separatorsToSystem(tmpDir + File.separator + library))) {
			IOUtils.copy(iStream, foStream);
			foStream.flush();
		}

		return Paths.get(separatorsToSystem(tmpDir.getAbsolutePath() + "\\" + library));
	}

	public static void setLibPath(final String path) throws NoSuchFieldException, IllegalAccessException {
		System.setProperty("java.library.path", path);
		final Field sysPathField = ClassLoader.class.getDeclaredField("sys_paths");
		sysPathField.setAccessible(true);
		sysPathField.set(null, null);
	}
}
