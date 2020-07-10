package xyz.rexlin600.java8.defaultmethod;

/**
 * Default method
 *
 * @author hekunlin
 */
public class DefaultMethod {

	/**
	 * Interface a
	 */
	public interface InterfaceA {
		/**
		 * Hello
		 */
		default void hello() {
			System.out.println("Hello, I'm default method");
		}
	}

	/**
	 * Inner default method
	 */
	public static class InnerDefaultMethod implements InterfaceA {

	}

}