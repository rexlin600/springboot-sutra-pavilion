package xyz.rexlin600.java8.stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * @description
 * @auther hekunlin
 * @create 2020-01-09 13:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SortedTest {

    private Sorted sorted;

    @Before
    public void setUp() throws Exception {
        sorted = new Sorted();
    }

    @Test
    public void sorted() {
        List<String> sorted = this.sorted.sorted();
        sorted.stream().forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
    }

    @Test
    public void sorted1() {
        Comparator<Double> doubleComparator = new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                //int min2Max = o1.compareTo(o2); // 从小到大排列
                int max2Min = o2.compareTo(o1); // 从大到小排列
                return max2Min;
            }
        };

        List<Object> sorted = this.sorted.sorted(doubleComparator);
        sorted.stream().forEach(new Consumer<Object>() {
            @Override
            public void accept(Object value) {
                System.out.println((Double) value);
            }
        });
    }
}