package com.tools.qaa.utils;

import com.luciad.imageio.webp.WebPWriteParam;
import org.apache.commons.io.FileUtils;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.io.File;

import java.nio.file.FileSystems;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

public class ScreenshotWatcher extends Thread {

	private final File file;
	private AtomicBoolean stop = new AtomicBoolean(false);
	private long timeout;

	private static final String DEFAULT_IMG_FORMAT = "png";
	private static final String DEFAULT_CONVERSION_FORMAT = "webp";
	private static final String DEFAULT_COMPRESSION_TYPE = "Lossless";

	private static final long DEFAULT_TIMEOUT_IN_SEC = 5;
	private static final float DEFAULT_QUALITY = 0.8f;

	private static final Logger LOGGER = Logger.getLogger(ScreenshotWatcher.class.getName());

	public ScreenshotWatcher(final File file) {
		this(file, DEFAULT_TIMEOUT_IN_SEC);
	}

	public ScreenshotWatcher(final File file, final long timeout) {
		this.file = file;
		this.timeout = timeout;
	}

	public boolean isStopped() {
		return stop.get();
	}

	public void stopThread() {
		stop.set(true);
	}

	public void onFileCreated() {
		final File outputFile = new File(file.getAbsolutePath().replace(DEFAULT_IMG_FORMAT, DEFAULT_CONVERSION_FORMAT));

		try (final ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(outputFile)) {
			final ImageWriter imgWriter = ImageIO.getImageWritersByFormatName(DEFAULT_CONVERSION_FORMAT).next();
			imgWriter.setOutput(imageOutputStream);
			imgWriter.write(null, new IIOImage(ImageIO.read(file), null, null), getDefaultImageWriterParameters());
			FileUtils.forceDelete(file);
		} catch (Exception ex) {
			LOGGER.severe("Can't create " + DEFAULT_CONVERSION_FORMAT + " file: " + ex.getMessage());
		}

		stopThread();
	}

	private ImageWriteParam getDefaultImageWriterParameters() {
		final ImageWriteParam imgWriteParams = new WebPWriteParam(null);
		imgWriteParams.setCompressionType(DEFAULT_COMPRESSION_TYPE);
		imgWriteParams.setCompressionQuality(DEFAULT_QUALITY);
		return imgWriteParams;
	}

	@Override
	public void run() {
		if (file.exists()) {
			onFileCreated();
		}

		try (final WatchService watcher = FileSystems.getDefault().newWatchService()) {
			file.toPath().getParent().register(watcher, StandardWatchEventKinds.ENTRY_CREATE);
			while (!isStopped()) {
				WatchKey key;
				if ((key = watcher.poll(timeout, TimeUnit.SECONDS)) != null) {
					key.pollEvents().stream()
							.filter(event -> key.isValid() && event.kind() == StandardWatchEventKinds.ENTRY_CREATE)
							.map(event -> event.context().toString())
							.filter(filePath -> filePath.equals(file.getName()))
							.forEach(filePath -> {
								onFileCreated();
								key.reset();
							});
				} else {
					LOGGER.info(timeout + " sec timeout occurred");
					stopThread();
				}
			}
		} catch (Exception e) {
			LOGGER.severe(e.toString());
		}
	}
}
