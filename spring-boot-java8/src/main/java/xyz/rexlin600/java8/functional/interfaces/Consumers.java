package xyz.rexlin600.java8.functional.interfaces;

import java.util.function.Consumer;

/**
 * Consumers
 *
 * @author hekunlin
 */
public class Consumers {

	/**
	 * Gets consume *
	 *
	 * @return the consume
	 */
	public Consumer<String> getConsume() {
		Consumer<String> consumer = new Consumer<String>() {
			@Override
			public void accept(String s) {
				System.out.println(s);
			}
		};
		return consumer;
	}

}