package xyz.rexlin600.java8.lambda;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.rexlin600.model.Goods;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 * Lambda 测试类
 *
 * @author: rexlin600
 * @date: 2020-01-09 01:15:24
 */
@Slf4j
@Service
public class LambdaBiz {

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
     * 构建 Stream 的各种方法
     */
    public void buildStream() {
        // empty stream
        Stream<Object> empty = Stream.empty();
        log.info("empty stream = [{}]", empty);

        // stream of one goodsList
        Stream<List<Goods>> oneListStream = Stream.of(goodsList);
        log.info("one list stream = [{}]", oneListStream);

        // stream of two goodsList
        Stream<List<Goods>> twoListStream = Stream.of(goodsList, goodsList);
        log.info("two list stream = [{}]", twoListStream);

        // build stream
        Stream<Object> buildStream = Stream.builder().add(goodsList).build();
        log.info("build stream = [{}]", buildStream);

        // concat stream
        Stream<List<Goods>> concatStream = Stream.concat(oneListStream, twoListStream);
        log.info("concat two stream = [{}]", concatStream);

        // -----------------------------------------------------------------------------------------------
        // 无限长度的 Stream ：可以无限对这个流操作下去，无情鞭尸！
        // -----------------------------------------------------------------------------------------------
        // generate random number stream
        Stream<Double> generateRandomStream = Stream.generate(() -> Math.random());
        log.info("generate random number stream = [{}]", generateRandomStream);

        // generate list stream
        // this method can simplify by lambda
        Stream<List<Goods>> generateSimplifyListStream = Stream.generate(() -> goodsList);
        Stream<List<Goods>> generateListStream = Stream.generate(new Supplier<List<Goods>>() {
            @Override
            public List<Goods> get() {
                return goodsList;
            }
        });
        log.info("generate list stream by simplify lamdba = [{}]", generateSimplifyListStream);
        log.info("generate list stream = [{}]", generateListStream);

        // iterate
        Stream<Goods> iterateStream = Stream.iterate(goodsList.get(0), new UnaryOperator<Goods>() {
            @Override
            public Goods apply(Goods goods) {
                String goodsName = "Cool " + goods.getName();
                goods.setName(goodsName);
                // FIXME not print log
                log.info("==> Stream iterate goods name = [{}]", goodsName);
                return goods;
            }
        });
        log.info("iterate goods stream = [{}]", iterateStream);

        // iterate println
        Stream.iterate(Math.random(), m -> m + 1)
                .limit(5)
                .forEach(new Consumer<Double>() {
                    @Override
                    public void accept(Double num) {
                        log.info("==> Stream iterate number = [{}]", num);
                    }
                });
    }


}