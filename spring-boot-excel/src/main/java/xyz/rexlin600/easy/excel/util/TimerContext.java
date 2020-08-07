package xyz.rexlin600.easy.excel.util;

import org.slf4j.Logger;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Simple task time calculation，Currently only the task time statistics task that supports synchronizing code blocks is
 * supported.
 *
 * @author <a href="mailto:liaochuntao@live.com">liaochuntao</a>
 */
public class TimerContext {

	private static final ThreadLocal<Pair<String, Long>> TIME_RECORD = new ThreadLocal<>();

	public static void start(final String name) {
		long startTime = System.currentTimeMillis();
		TIME_RECORD.set(Pair.with(name, startTime));
	}

	public static void end(final Logger logger) {
		// 默认 DEBUG
		end(logger, "DEBUG");
	}

	/**
	 * End the task and print based on the log level.
	 *
	 * @param logger logger
	 * @param level  logger level
	 */
	public static void end(final Logger logger, final String level) {
		long endTime = System.currentTimeMillis();
		Pair<String, Long> record = TIME_RECORD.get();
		switch (level) {
			case "DEBUG":
				logger.debug("{} cost time : {} ms", record.getFirst(), (endTime - record.getSecond()));
				break;
			case "INFO":
				logger.info("{} cost time : {} ms", record.getFirst(), (endTime - record.getSecond()));
				break;
			case "TRACE":
				logger.trace("{} cost time : {} ms", record.getFirst(), (endTime - record.getSecond()));
				break;
			case "ERROR":
				logger.error("{} cost time : {} ms", record.getFirst(), (endTime - record.getSecond()));
				break;
			case "WARN":
				logger.warn("{} cost time : {} ms", record.getFirst(), (endTime - record.getSecond()));
				break;
			default:
				break;
		}
		TIME_RECORD.remove();
	}

	/**
	 * Execution with time-consuming calculations for {@link Runnable}.
	 *
	 * @param job    runnable
	 * @param name   job name
	 * @param logger logger
	 */
	public static void run(final Runnable job, final String name, final Logger logger) {
		start(name);
		try {
			job.run();
		} finally {
			end(logger);
		}
	}

	/**
	 * Execution with time-consuming calculations for {@link Supplier}.
	 *
	 * @param job    Supplier
	 * @param name   job name
	 * @param logger logger
	 */
	public static <V> V run(final Supplier<V> job, final String name, final Logger logger) {
		start(name);
		try {
			return job.get();
		} finally {
			end(logger);
		}
	}

	/**
	 * Execution with time-consuming calculations for {@link Function}.
	 *
	 * @param job    Function
	 * @param args   args
	 * @param name   job name
	 * @param logger logger
	 */
	public static <T, R> R run(final Function<T, R> job, T args, final String name, final Logger logger) {
		start(name);
		try {
			return job.apply(args);
		} finally {
			end(logger);
		}
	}

	/**
	 * Execution with time-consuming calculations for {@link Consumer}.
	 *
	 * @param job    Consumer
	 * @param args   args
	 * @param name   job name
	 * @param logger logger
	 */
	public static <T> void run(final Consumer<T> job, T args, final String name, final Logger logger) {
		start(name);
		try {
			job.accept(args);
		} finally {
			end(logger);
		}
	}

	// -----------------------------------------------------------------------------------------------
	// 二元组 Pair
	// -----------------------------------------------------------------------------------------------

	public static class Pair<A, B> {

		private final A first;

		private final B second;

		Pair(A first, B second) {
			this.first = first;
			this.second = second;
		}

		public static <A, B> Pair<A, B> with(A first, B second) {
			return new Pair<A, B>(first, second);
		}

		public A getFirst() {
			return first;
		}

		public B getSecond() {
			return second;
		}
	}

}
