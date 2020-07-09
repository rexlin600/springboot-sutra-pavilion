package xyz.rexlin600.java8.stream;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ParallelStream 类
 *
 * @author: hekunlin
 * @since: 2020/1/9
 */
public class ParallelStream {

    /**
     * 线程不安全的 并行流
     * <p>
     * <p>
     * <p>
     * 下面这个方法必须制定要 add 元素的位置，属于 java api 历史遗留问题，否者在本例中会报错 UnsupportedOperationException
     * List<Integer> result = Collections.synchronizedList(Arrays.asList());
     * <p>
     * <p>
     * 会产生线程安全问题：Lists.newArrayList()
     *
     * @param list
     * @return
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