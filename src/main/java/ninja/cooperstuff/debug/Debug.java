package ninja.cooperstuff.debug;

import java.time.LocalDateTime;

public class Debug {
	enum Level {LOG, DEBUG, INFO, WARN, ERROR}
	public static int level = 0;

	private static void print(Object text, Level level) {
		LocalDateTime ldt = LocalDateTime.now();
		System.out.printf("[%tT.%<tL] [%s/%s] %s%n", ldt, level, getCallerClassName(), text);
	}

	public static void log(Object text) {
		if (level < 2) print(text, Level.LOG);
	}

	public static void debug(Object text) {
		if (level < 1) print(text, Level.DEBUG);
	}

	public static void info(Object text) {
		if (level < 3) print(text, Level.INFO);
	}

	public static void warn(Object text) {
		if (level < 4) print(text, Level.WARN);
	}

	public static void error(Object text) {
		if (level < 5) print(text, Level.ERROR);
	}

	private static String getCallerClassName() {
		StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
		for (int i=1; i<stElements.length; i++) {
			StackTraceElement ste = stElements[i];
			if (!ste.getClassName().equals(Debug.class.getName()) && ste.getClassName().indexOf("java.lang.Thread")!=0) {
				return String.format("%s.%s:%s", ste.getClassName(), ste.getMethodName(), ste.getLineNumber());
			}
		}
		return null;
	}
}
