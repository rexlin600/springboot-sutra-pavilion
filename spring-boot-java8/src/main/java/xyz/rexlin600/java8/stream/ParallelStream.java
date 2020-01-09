package xyz.rexlin600.java8.stream;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ParallelStream 类
 *
 * @author: hekunlin
 * @date: 2020/1/9
 */
public class ParallelStream {

    /**
     * 线程不安全的 并行流
     * <p>
     * 可以考虑使用
     *
     * @param list
     * @return
     */
    public List<Integer> parallelStream(List<Integer> list) {
        // 下面两个 List 不会产生线程安全问题
        List<Integer> result = new CopyOnWriteArrayList<>();
        // 下面这个方法必须制定要 add 元素的位置，属于 java api 历史遗留问题，否者在本例中会报错 UnsupportedOperationException
        //List<Integer> result = Collections.synchronizedList(Arrays.asList());

        // 下面这个 List 会产生线程安全问题
        //List<Integer> result = Lists.newArrayList();
        list.parallelStream().forEach(m -> {
            result.add(m + 3);
        });
        return result;
    }

}