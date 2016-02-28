package com.tools.qaa.utils;

import com.tools.qaa.providers.ServiceException;
import javaslang.control.Try;
import one.util.streamex.StreamEx;
import org.zeroturnaround.exec.ProcessExecutor;

import java.util.*;
import java.util.concurrent.TimeUnit;

public final class ProcessUtils {

	public static final String JAVA_TASKS_KILL = "for /f \"tokens=1\" %i in ('jps -l ^| findstr \"?\"') do (taskkill /F /PID %i)";
	public static final String COMMON_TASKS_KILL = "for /f \"tokens=1\" %i in ('tasklist ^| findstr \"?\"') do (taskkill /F /PID %i)";
	public static final String SCREENSHOT_ARGS = " /f \"%s\" /d \"%s\"";

	public enum ProcessName {
		CMD,
		PSEXEC
	}

	private static final Map<ProcessName, List<String>> PROCESSES = Collections.unmodifiableMap(
			new HashMap<ProcessName, List<String>>() {
				{
					put(ProcessName.CMD, Arrays.asList("cmd.exe", "/c"));
					put(ProcessName.PSEXEC, Arrays.asList("psexec.exe", "-c", "-d", "-i"));
				}
			});

	public static String executeProcess(final ProcessName process, final String rootCommand,
	                                    final List<String> tasks, final int timeout) throws ServiceException {
		return Try.of(() -> new ProcessExecutor()
				.command(compose(process, rootCommand, tasks))
				.readOutput(true)
				.timeout(timeout, TimeUnit.SECONDS)
				.execute()
				.outputUTF8())
				.orElseThrow(ex -> new ServiceException("Unable to execute process", ex));
	}

	private static String getFormattedTasks(final String rootCommand, final List<String> tasks) {
		return rootCommand.replace("?", tasks.toString()
				.replaceAll("\\[|\\]", "")
				.replaceAll(",", ""));
	}

	private static List<String> compose(final ProcessName process, final String rootCommand, final List<String> tasks) {
		return StreamEx.of(PROCESSES.get(process))
				.append(getFormattedTasks(rootCommand, tasks))
				.toList();
	}

	private ProcessUtils() {
		throw new UnsupportedOperationException("Illegal access to private constructor");
	}
}
