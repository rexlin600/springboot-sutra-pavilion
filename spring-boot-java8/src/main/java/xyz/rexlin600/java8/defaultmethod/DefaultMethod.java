package xyz.rexlin600.java8.defaultmethod;

/**
 * DefaultMethod 类
 *
 * @author: hekunlin
 * @date: 2020/1/9
 */
public class DefaultMethod {

    interface interfaceA {
        default void hello() {
            System.out.println("Hello, I'm default method");
        }
    }

    static class InnerDefaultMethod implements interfaceA {

    }

}