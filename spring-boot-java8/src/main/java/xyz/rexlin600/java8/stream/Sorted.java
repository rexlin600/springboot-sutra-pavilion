package xyz.rexlin600.java8.stream;

import lombok.extern.slf4j.Slf4j;
import xyz.rexlin600.swagger.model.Goods;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Sorted 类
 *
 * @author: hekunlin
 * @date: 2020/1/9
 */
@Slf4j
public class Sorted {

    /**
     * init data
     */
    private static List<Goods> goodsList = new ArrayList<>();
    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

    static {
        goodsList.add(new Goods(1L, "Padraig", 0, "接待员", 192.78, "透明", LocalDateTime.parse("2011-03-25 16:37:44", DateTimeFormatter.ofPattern(PATTERN)), LocalDateTime.parse("2019-10-05 15:16:39", DateTimeFormatter.ofPattern(PATTERN))));
        goodsList.add(new Goods(2L, "Geoffrey", 0, "接待员", 143.15, "天蓝色", LocalDateTime.parse("2015-05-14 22:23:27", DateTimeFormatter.ofPattern(PATTERN)), LocalDateTime.parse("2019-10-05 15:16:39", DateTimeFormatter.ofPattern(PATTERN))));
        goodsList.add(new Goods(3L, "Juan", 0, "研发工程师", 186.16, "天蓝色", LocalDateTime.parse("2007-11-06 13:06:44", DateTimeFormatter.ofPattern(PATTERN)), LocalDateTime.parse("2019-10-05 15:16:39", DateTimeFormatter.ofPattern(PATTERN))));
        goodsList.add(new Goods(4L, "Jean", 0, "行政经理", 89.25, "桔色", LocalDateTime.parse("2002-03-11 07:12:07", DateTimeFormatter.ofPattern(PATTERN)), LocalDateTime.parse("2019-10-05 15:16:39", DateTimeFormatter.ofPattern(PATTERN))));
        goodsList.add(new Goods(5L, "Anallese", 0, "网络运维工程师", 166.48, "酒红色", LocalDateTime.parse("2004-09-05 23:44:22", DateTimeFormatter.ofPattern(PATTERN)), LocalDateTime.parse("2019-10-05 15:16:39", DateTimeFormatter.ofPattern(PATTERN))));
        goodsList.add(new Goods(6L, "Roscoe", 0, "测试工程师", 30.39, "天蓝色", LocalDateTime.parse("2010-04-27 07:48:08", DateTimeFormatter.ofPattern(PATTERN)), LocalDateTime.parse("2019-10-05 15:16:39", DateTimeFormatter.ofPattern(PATTERN))));
        goodsList.add(new Goods(7L, "Alexandros", 0, "话务员", 107.38, "深灰色", LocalDateTime.parse("2012-07-15 05:46:04", DateTimeFormatter.ofPattern(PATTERN)), LocalDateTime.parse("2019-10-05 15:16:39", DateTimeFormatter.ofPattern(PATTERN))));
        goodsList.add(new Goods(8L, "Adair", 0, "研发工程师", 104.33, "黑色", LocalDateTime.parse("2009-12-18 17:53:51", DateTimeFormatter.ofPattern(PATTERN)), LocalDateTime.parse("2019-10-05 15:16:39", DateTimeFormatter.ofPattern(PATTERN))));
        goodsList.add(new Goods(9L, "Annnora", 0, "话务员", 196.45, "紫色", LocalDateTime.parse("2003-11-07 00:35:14", DateTimeFormatter.ofPattern(PATTERN)), LocalDateTime.parse("2019-10-05 15:16:39", DateTimeFormatter.ofPattern(PATTERN))));
        goodsList.add(new Goods(10L, "Ynez", 0, "产品经理", 74.24, "深卡其布色", LocalDateTime.parse("2015-06-20 03:31:47", DateTimeFormatter.ofPattern(PATTERN)), LocalDateTime.parse("2019-10-05 15:16:39", DateTimeFormatter.ofPattern(PATTERN))));
    }

    /**
     * 筛选出名称，默认排序
     *
     * @return
     */
    public List<String> sorted() {
        List<String> collect = goodsList.stream()
                .map(Goods::getName)
                .sorted()
                .collect(Collectors.toList());
        return collect;
    }

    /**
     * 筛选出名称，传入 Comparator 自定义实现比较器
     *
     * @return
     */
    public List<Object> sorted(Comparator comparator) {
        List<Object> list = (List<Object>) goodsList.stream()
                .map(Goods::getWeight)
                .sorted(comparator)
                .collect(Collectors.toList());
        return list;
    }


}