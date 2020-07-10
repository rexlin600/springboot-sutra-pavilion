package xyz.rexlin600.java8.stream;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Parallel stream
 *
 * @author hekunlin
 */
public class ParallelStream {

	/**
	 * Parallel stream list
	 *
	 * @param list list
	 * @return the list
	 */
	public List<Integer> parallelStream(List<Integer> list) {
		// CopyOnWriteArrayList、 Collections.synchronizedList(Arrays.asList()) 不会产生线程安全问题
		List<Integer> result = new CopyOnWriteArrayList<>();

		list.parallelStream().forEach(m -> {
			result.add(m + 3);
		});
		return result;
	}

}