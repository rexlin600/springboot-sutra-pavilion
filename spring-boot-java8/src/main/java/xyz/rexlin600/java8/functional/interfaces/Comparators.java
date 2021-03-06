package xyz.rexlin600.java8.functional.interfaces;


import xyz.rexlin600.java8.model.Goods;

import java.util.Comparator;

/**
 * Comparators
 *
 * @author hekunlin
 */
public class Comparators {

	/**
	 * Gets int comparator *
	 *
	 * @return the int comparator
	 */
	public Comparator getIntComparator() {
		Comparator<Integer> comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				int cm = o1.compareTo(o2);
				return cm;
			}
		};
		return comparator;
	}

	/**
	 * Gets goods comparator *
	 *
	 * @return the goods comparator
	 */
	public Comparator getGoodsComparator() {
		Comparator<Goods> comparator = new Comparator<Goods>() {
			@Override
			public int compare(Goods o1, Goods o2) {
				// 根据名称首字母 从 A -> Z、长度从小到大
				int r = 0;
				int cm = o1.getName().substring(0, 1).compareTo(o2.getName().substring(0, 1));
				if (cm != 0) {
					// 比较几项这里最大数字就写几，然后呈梯次递减（即：3到1 -1到-3），这样代码比较容易阅读
					r = cm > 0 ? 3 : -1;
				} else {
					int len = o1.getName().length() - o2.getName().length();
					if (len != 0) {
						r = len > 0 ? 2 : -2;
					} else {
						int weight = (int) (o1.getWeight() - o2.getWeight());
						if (weight != 0) {
							r = weight > 0 ? 1 : -3;
						}
					}
				}
				return r;
			}
		};
		return comparator;
	}

}