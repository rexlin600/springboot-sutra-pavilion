package xyz.rexlin600.java8.optinals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import xyz.rexlin600.model.Goods;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static org.junit.Assert.*;

/**
 * @description
 * @auther hekunlin
 * @create 2020-01-09 17:02
 */
public class OptinalsTest {

    Optinals optinals;

    @Before
    public void setUp() throws Exception {
        optinals = new Optinals();
    }

    @Test
    public void optionalEmpty() {
        Optional<Goods> optional = optinals.optionalEmpty();

        assertTrue(!optional.isPresent());
    }

    @Test
    public void optionalOfList() {
        Optional<List<Goods>> optional = optinals.optionalOfList();

        assertTrue(optional.isPresent());
    }

    @Test
    public void optionalOfNullList() {
        Optional<List<Goods>> optional = optinals.optionalOfNullList();

        // 【重点】没有 index=100 的goodsList，所以 List 返回是一个 []，Optional则是一个 [[]]
        // 因此要注意 Optinal 里面的内容，如果是嵌套的一定要注意逐级判断
        optional.ifPresent(m -> {
            boolean empty = CollectionUtils.isEmpty(m);
            assertTrue(empty);
        });
    }

    @Test
    public void optionalOfGoods() {
        Optional<Goods> optional = optinals.optionalOfGoods();

        assertTrue(optional.isPresent());
    }

    @Test
    public void optionalOfNullGoods() {
        Optional<Goods> optional = optinals.optionalOfNullGoods();

        assertTrue(optional.isPresent());
    }
}