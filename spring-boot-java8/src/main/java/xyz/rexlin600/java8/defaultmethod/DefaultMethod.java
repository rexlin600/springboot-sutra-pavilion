package xyz.rexlin600.java8.defaultmethod;

/**
 * DefaultMethod 类
 *
 * @author: hekunlin
 * @date: 2020/1/9
 */
public class DefaultMethod {

    /**
     * 测试接口
     */
    public interface InterfaceA {
        /**
         * hello 方法
         */
        default void hello() {
            System.out.println("Hello, I'm default method");
        }
    }

    /**
     * 内部类
     */
    public static class InnerDefaultMethod implements InterfaceA {

    }

}