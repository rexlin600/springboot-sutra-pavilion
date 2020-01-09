package xyz.rexlin600.java8.stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.rexlin600.model.Goods;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * @description
 * @auther hekunlin
 * @create 2020-01-09 11:59
 */
@SuppressWarnings("Duplicates")
@RunWith(SpringRunner.class)
@SpringBootTest
public class FlatMapTest {


    FlatMap flatMap;

    @Before
    public void setUp() throws Exception {
        flatMap = new FlatMap();
    }

    @Test
    public void flatMapFunction() {
        Function<Goods, Stream<Long>> function = new Function<Goods, Stream<Long>>() {
            @Override
            public Stream<Long> apply(Goods goods) {
                if (goods.getColor().equals("天蓝色")) {
                    return Stream.of(goods.getId());
                }
                return null;
            }
        };
        List<Long> list = flatMap.flatMapFunction(function);

        assertEquals(3l, list.size());
    }
}