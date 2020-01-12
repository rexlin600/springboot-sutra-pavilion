package xyz.rexlin600.java8.functional.interfaces;

import java.util.function.Supplier;

/**
 * Suppliers 类
 *
 * @author: hekunlin
 * @date: 2020/1/9
 */
public class Suppliers {

    private static final Long NUM = 100000L;

    /**
     * Supplier 和 Functions 类似，只不过不接受参数
     *
     * @param msg
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
     * 【重要】惰性求值
     * <p>
     * 作用：我们可以把耗资源运算放到get方法里，在程序里，我们传递的是Supplier对象，直到调用get方法时，运算才会执行。这就是所谓的
     *
     * @return
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