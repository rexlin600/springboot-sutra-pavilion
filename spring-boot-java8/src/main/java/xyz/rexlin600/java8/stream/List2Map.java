package xyz.rexlin600.java8.stream;

import xyz.rexlin600.java8.model.Goods;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * List 2 map
 *
 * @author hekunlin
 */
public class List2Map {

	/**
	 * PATTERN
	 */
	private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";
	/**
	 * firstList
	 */
	private static List<Goods> firstList = new ArrayList<>();

	static {
		// 6
		firstList.add(new Goods(1L, "Padraig", 0, "接待员", 192.78, "透明", LocalDateTime.parse("2011-03-25 16:37:44", DateTimeFormatter.ofPattern(PATTERN)), LocalDateTime.parse("2019-10-05 15:16:39", DateTimeFormatter.ofPattern(PATTERN))));
		firstList.add(new Goods(2L, "Geoffrey", 0, "接待员", 143.15, "天蓝色", LocalDateTime.parse("2015-05-14 22:23:27", DateTimeFormatter.ofPattern(PATTERN)), LocalDateTime.parse("2019-10-05 15:16:39", DateTimeFormatter.ofPattern(PATTERN))));
		firstList.add(new Goods(3L, "Juan", 0, "研发工程师", 186.16, "天蓝色", LocalDateTime.parse("2007-11-06 13:06:44", DateTimeFormatter.ofPattern(PATTERN)), LocalDateTime.parse("2019-10-05 15:16:39", DateTimeFormatter.ofPattern(PATTERN))));
		firstList.add(new Goods(4L, "Jean", 0, "行政经理", 89.25, "桔色", LocalDateTime.parse("2002-03-11 07:12:07", DateTimeFormatter.ofPattern(PATTERN)), LocalDateTime.parse("2019-10-05 15:16:39", DateTimeFormatter.ofPattern(PATTERN))));
		firstList.add(new Goods(5L, "Anallese", 0, "网络运维工程师", 166.48, "酒红色", LocalDateTime.parse("2004-09-05 23:44:22", DateTimeFormatter.ofPattern(PATTERN)), LocalDateTime.parse("2019-10-05 15:16:39", DateTimeFormatter.ofPattern(PATTERN))));
		firstList.add(new Goods(6L, "Roscoe", 0, "测试工程师", 30.39, "天蓝色", LocalDateTime.parse("2010-04-27 07:48:08", DateTimeFormatter.ofPattern(PATTERN)), LocalDateTime.parse("2019-10-05 15:16:39", DateTimeFormatter.ofPattern(PATTERN))));
		firstList.add(new Goods(7L, "Alexandros", 0, "话务员", 107.38, "深灰色", LocalDateTime.parse("2012-07-15 05:46:04", DateTimeFormatter.ofPattern(PATTERN)), LocalDateTime.parse("2019-10-05 15:16:39", DateTimeFormatter.ofPattern(PATTERN))));
		firstList.add(new Goods(8L, "Adair", 0, "研发工程师", 104.33, "黑色", LocalDateTime.parse("2009-12-18 17:53:51", DateTimeFormatter.ofPattern(PATTERN)), LocalDateTime.parse("2019-10-05 15:16:39", DateTimeFormatter.ofPattern(PATTERN))));
		firstList.add(new Goods(9L, "Annnora", 0, "话务员", 196.45, "紫色", LocalDateTime.parse("2003-11-07 00:35:14", DateTimeFormatter.ofPattern(PATTERN)), LocalDateTime.parse("2019-10-05 15:16:39", DateTimeFormatter.ofPattern(PATTERN))));
		firstList.add(new Goods(10L, "Ynez", 0, "产品经理", 74.24, "深卡其布色", LocalDateTime.parse("2015-06-20 03:31:47", DateTimeFormatter.ofPattern(PATTERN)), LocalDateTime.parse("2019-10-05 15:16:39", DateTimeFormatter.ofPattern(PATTERN))));
	}

	/**
	 * List 2 map
	 */
	public static void list2Map() {
		// key=id value=name
		Map<Long, String> map1 = firstList.stream().collect(Collectors.toMap(Goods::getId, Goods::getName));
		System.out.println(map1.size());

		// key=id value=goods
		Map<Long, Goods> map2 = firstList.stream().collect(Collectors.toMap(Goods::getId, goods -> goods));
		System.out.println(map2.size());

		// key=color value=goods，重复key会抛弃后者保留前者
		Map<String, Goods> map3 = firstList.stream().collect(Collectors.toMap(Goods::getColor, m -> m, (k1, k2) -> k1));
		System.out.println(map3.size());

		// 指定 map 具体实现来收集数据，重复key会抛弃后者保留前者
		LinkedHashMap<String, Goods> map4 = firstList.stream().collect(Collectors.toMap(Goods::getColor, Function.identity(), (key1, key2) -> key1, LinkedHashMap::new));
		System.out.println(map4.size());
	}

	// -----------------------------------------------------------------------------------------------
	// 测试
	// -----------------------------------------------------------------------------------------------

	/**
	 * Main
	 *
	 * @param args args
	 */
	public static void main(String[] args) {
		list2Map();
	}

}