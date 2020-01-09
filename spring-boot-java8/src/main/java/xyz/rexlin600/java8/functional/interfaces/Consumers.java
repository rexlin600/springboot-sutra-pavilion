package xyz.rexlin600.java8.functional.interfaces;

import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Consumers 类
 *
 * @author: hekunlin
 * @date: 2020/1/9
 */
public class Consumers {

    /**
     * Consumer 表示要对单个输入参数执行的操作。
     *
     * @return
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