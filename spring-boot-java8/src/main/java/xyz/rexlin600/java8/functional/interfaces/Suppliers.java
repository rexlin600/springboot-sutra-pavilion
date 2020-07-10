package xyz.rexlin600.java8.functional.interfaces;

import java.util.function.Supplier;

/**
 * Suppliers
 *
 * @author hekunlin
 */
public class Suppliers {

	/**
	 * NUM
	 */
	private static final Long NUM = 100000L;

	/**
	 * Suppliers supplier
	 *
	 * @param msg msg
	 * @return the supplier
	 */
	public Supplier<String> suppliers(String msg) {
		Supplier<String> stringSupplier = new Supplier<String>() {
			@Override
			public String get() {
				return "Hi," + msg;
			}
		};
		return stringSupplier;
	}

	/**
	 * Suppliers supplier
	 *
	 * @param number number
	 * @return the supplier
	 */
	public Supplier<Double> suppliers(Double number) {
		Supplier<Double> supplier = () -> {
			Double tmp = number;
			// 模拟耗时操作
			for (int i = 0; i < NUM; i++) {
				tmp += Math.random();
			}
			return tmp;
		};
		return supplier;
	}

}