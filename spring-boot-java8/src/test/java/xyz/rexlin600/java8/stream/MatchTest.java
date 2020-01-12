package xyz.rexlin600.java8.stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.rexlin600.java8.model.Goods;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.function.Predicate;

import static org.junit.Assert.*;

/**
 * @description
 * @auther hekunlin
 * @create 2020-01-09 13:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MatchTest {

    private Match match;

    @Before
    public void setUp() throws Exception {
        match = new Match();
    }

    @Test
    public void anyMatch() {
        Boolean anyMatch1 = match.anyMatch(new Predicate<Goods>() {
            @Override
            public boolean test(Goods o) {
                return o.getColor().equals("天蓝色");
            }
        });

        // lambda 写法
        Boolean anyMatch2 = match.anyMatch(
                (Predicate<Goods>) o -> o.getCreateDate().isAfter(LocalDateTime.now(ZoneId.systemDefault())));

        assertTrue(anyMatch1);
        assertFalse(anyMatch2);
    }

    @Test
    public void noneMatch() {
        Boolean anyMatch1 = match.noneMatch(new Predicate<Goods>() {
            @Override
            public boolean test(Goods o) {
                return o.getColor().equals("天蓝色");
            }
        });

        // lambda 写法
        Boolean anyMatch2 = match.noneMatch(
                (Predicate<Goods>) o -> o.getCreateDate().isAfter(LocalDateTime.now(ZoneId.systemDefault())));

        assertFalse(anyMatch1);
        assertTrue(anyMatch2);
    }

    @Test
    public void allMatch() {
        Boolean anyMatch1 = match.allMatch(new Predicate<Goods>() {
            @Override
            public boolean test(Goods o) {
                return o.getColor().equals("天蓝色");
            }
        });

        // lambda 写法
        Boolean anyMatch2 = match.allMatch(
                (Predicate<Goods>) o -> o.getCreateDate().isAfter(LocalDateTime.now(ZoneId.systemDefault())));

        // lambda 写法
        Boolean anyMatch3 = match.allMatch(
                (Predicate<Goods>) o -> o.getCreateDate().isBefore(LocalDateTime.now(ZoneId.systemDefault())));


        assertFalse(anyMatch1);
        assertFalse(anyMatch2);
        assertTrue(anyMatch3);
    }

}