package xyz.rexlin600.java8.optinals;

import xyz.rexlin600.java8.model.Goods;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Optinals 类
 *
 * @author: hekunlin
 * @since: 2020/1/9
 */
public class Optinals {

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
     * empty Optional
     *
     * @return
     */
    public static Optional<Goods> optionalEmpty() {
        // return value == null ? empty() : of(value);
        Optional<Goods> optional = Optional.empty();
        return optional;
    }

    /**
     * Optional of List
     *
     * @return
     */
    public static Optional<List<Goods>> optionalOfList() {
        Optional<List<Goods>> optional = Optional.of(goodsList.stream()
                .filter(m -> m.getId().equals(2))
                .collect(Collectors.toList()));
        return optional;
    }

    /**
     * Optional ofNullable List
     *
     * @return
     */
    public static Optional<List<Goods>> optionalOfNullList() {
        // [重点]Optional里包裹的内容如果是集合需要特殊判断，不能直接 Optional.isPresent()
        Optional<List<Goods>> optional = Optional.ofNullable(goodsList.stream()
                .filter(m -> m.getId().equals(100))
                .collect(Collectors.toList()));
        return optional;
    }

    /**
     * Optional of Goods
     *
     * @return
     */
    public static Optional<Goods> optionalOfGoods() {
        // return new Optional<>(value);
        Optional<Goods> optional = Optional.of(goodsList.get(2));
        return optional;
    }

    /**
     * Optional ofNullable goods
     * 必须保证在使用 Optional 的包裹方法时，内部的表达式不会报错，所以需要注意！
     *
     * @return
     */
    public static Optional<Goods> optionalOfNullGoods() {
        Optional<Goods> optional = Optional.ofNullable(goodsList.get(2));
        return optional;
    }

    public static void main(String[] args) {
        optionalEmpty();
        System.out.println("---------------------------");
        optionalOfGoods();
        System.out.println("---------------------------");
        optionalOfList();
        System.out.println("---------------------------");
        optionalOfNullGoods();
        System.out.println("---------------------------");
        optionalOfNullList();
    }

}