package xyz.rexlin600.java8.stream;

import org.springframework.util.ObjectUtils;
import xyz.rexlin600.java8.model.Goods;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;

/**
 * Reduce 类
 *
 * @author: hekunlin
 * @since: 2020/1/9
 */
@SuppressWarnings("ALL")
public class Reduce {

    /**
     * init data
     */
    private static List<Goods> goodsList = new ArrayList<>();
    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

    static {
        goodsList.add(new Goods(1L, "Padraig", 0, "接待员", 192.78, "透明", LocalDateTime.parse("2011-03-25 16:37:44", DateTimeFormatter.ofPattern(PATTERN)), LocalDateTime.parse("2019-10-05 15:16:39", DateTimeFormatter.ofPattern(PATTERN))));
        goodsList.add(new Goods(2L, "Geoffrey", 0, "接待员", 143.15, "天蓝色", LocalDateTime.parse("2015-05-14 22:23:27", DateTimeFormatter.ofPattern(PATTERN)), LocalDateTime.parse("2019-10-05 15:16:39", DateTimeFormatter.ofPattern(PATTERN))));
        goodsList.add(new Goods(3L, "Juan", 1, "研发工程师", 286.16, "天蓝色", LocalDateTime.parse("2007-11-06 13:06:44", DateTimeFormatter.ofPattern(PATTERN)), LocalDateTime.parse("2019-10-05 15:16:39", DateTimeFormatter.ofPattern(PATTERN))));
        goodsList.add(new Goods(4L, "Jean", 0, "行政经理", 89.25, "桔色", LocalDateTime.parse("2002-03-11 07:12:07", DateTimeFormatter.ofPattern(PATTERN)), LocalDateTime.parse("2019-10-05 15:16:39", DateTimeFormatter.ofPattern(PATTERN))));
        goodsList.add(new Goods(5L, "Anallese", 0, "网络运维工程师", 166.48, "酒红色", LocalDateTime.parse("2004-09-05 23:44:22", DateTimeFormatter.ofPattern(PATTERN)), LocalDateTime.parse("2019-10-05 15:16:39", DateTimeFormatter.ofPattern(PATTERN))));
        goodsList.add(new Goods(6L, "Roscoe", 0, "测试工程师", 30.39, "天蓝色", LocalDateTime.parse("2010-04-27 07:48:08", DateTimeFormatter.ofPattern(PATTERN)), LocalDateTime.parse("2019-10-05 15:16:39", DateTimeFormatter.ofPattern(PATTERN))));
        goodsList.add(new Goods(7L, "Alexandros", 0, "话务员", 107.38, "深灰色", LocalDateTime.parse("2012-07-15 05:46:04", DateTimeFormatter.ofPattern(PATTERN)), LocalDateTime.parse("2019-10-05 15:16:39", DateTimeFormatter.ofPattern(PATTERN))));
        goodsList.add(new Goods(8L, "Adair", 1, "研发工程师", 104.33, "黑色", LocalDateTime.parse("2009-12-18 17:53:51", DateTimeFormatter.ofPattern(PATTERN)), LocalDateTime.parse("2019-10-05 15:16:39", DateTimeFormatter.ofPattern(PATTERN))));
        goodsList.add(new Goods(9L, "Annnora", 1, "话务员", 196.45, "紫色", LocalDateTime.parse("2003-11-07 00:35:14", DateTimeFormatter.ofPattern(PATTERN)), LocalDateTime.parse("2019-10-05 15:16:39", DateTimeFormatter.ofPattern(PATTERN))));
    }


    /**
     * 通过 reduce 计算出和 identity sort 一致并在 goodsList 中 weight 最大的元素
     *
     * @return
     */
    public static Goods reduceByIdentity() {
        Goods identity = new Goods(10L, "Ynez", 1, "产品经理", 74.24, "深卡其布色", LocalDateTime.parse("2015-06-20 03:31:47", DateTimeFormatter.ofPattern(PATTERN)), LocalDateTime.parse("2019-10-05 15:16:39", DateTimeFormatter.ofPattern(PATTERN)));
        Goods goods = goodsList.stream()
                .reduce(identity, new BinaryOperator<Goods>() {
                    @Override
                    public Goods apply(Goods goods1, Goods goods2) {
                        if (ObjectUtils.isEmpty(goods1)) {
                            return goods2;
                        }
                        if (ObjectUtils.isEmpty(goods2)) {
                            return goods1;
                        }

                        // 找出重量较大的一个
                        boolean maxWeight = goods1.getWeight() > goods2.getWeight();
                        if (maxWeight) {
                            // 找出 sort 一致的那一个
                            if (goods1.getSort().equals(identity.getSort())) {
                                return goods1;
                            }
                            return null;
                        }
                        return goods2;
                    }
                });

        return goods;
    }

    /**
     * reduce 找出 weight 最大的
     *
     * @return
     */
    public static Goods reduce() {
        Optional<Goods> optional = goodsList.stream()
                .reduce(new BinaryOperator<Goods>() {
                    @Override
                    public Goods apply(Goods goods, Goods goods2) {
                        if (goods.getWeight() > goods2.getWeight()) {
                            return goods;
                        }
                        return goods2;
                    }
                });
        if (!optional.isPresent()) {
            return null;
        }

        return optional.get();
    }

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
        reduceByIdentity();
        System.out.println("---------------------------");
        reduce();
    }

}