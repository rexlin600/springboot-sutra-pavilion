package xyz.rexlin600.java8.functional.interfaces;

/**
 * My functional interface
 *
 * @param <T> parameter
 * @param <R> parameter
 */
@FunctionalInterface
interface MyFunctionalInterface<T, R> {

	/**
	 * Apply r
	 *
	 * @param t t
	 * @return the r
	 */
	R apply(T t);

}