package xyz.rexlin600.java8.optinals;

import xyz.rexlin600.model.Goods;

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
 * @date: 2020/1/9
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
    public Optional<Goods> optionalEmpty() {
        // return value == null ? empty() : of(value);
        Optional<Goods> optional = Optional.empty();
        return optional;
    }

    /**
     * Optional of List
     *
     * @return
     */
    public Optional<List<Goods>> optionalOfList() {
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
    public Optional<List<Goods>> optionalOfNullList() {
        // 【重点】Optional里包裹的内容如果是集合需要特殊判断，不能直接 Optional.isPresent()
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
    public Optional<Goods> optionalOfGoods() {
        // return new Optional<>(value);
        Optional<Goods> optional = Optional.of(goodsList.get(2));
        return optional;
    }

    /**
     * Optional ofNullable goods
     *
     * @return
     */
    public Optional<Goods> optionalOfNullGoods() {
        // 【重点】下面这种写法会报错，因为 Optional 只会包裹处理后的值，在处理过程中报错 Optional 是不会管的！
        // return value == null ? empty() : of(value);
        //Optional<Goods> optional = Optional.ofNullable(goodsList.get(100));

        // 【重点】必须保证Optional.of() 或者 Optional.ofNullable() 在 () 的内容不会报错
        Optional<Goods> optional = Optional.ofNullable(goodsList.get(2));
        return optional;
    }


}