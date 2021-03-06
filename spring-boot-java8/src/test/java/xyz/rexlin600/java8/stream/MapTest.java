package xyz.rexlin600.java8.stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.rexlin600.java8.model.Goods;

import java.util.List;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapTest {

	private Map map;

	@Before
	public void setUp() throws Exception {
		map = new Map();
	}

	@Test
	public void map() {
		// Functions 接口，期盼传入一个 Goods、返回 Long
		Function<Goods, Long> function = new Function<Goods, Long>() {
			@Override
			public Long apply(Goods goods) {
				if (goods.getColor().equals("天蓝色")) {
					return goods.getId();
				}
				return null;
			}
		};

		List<Long> list = map.mapFunction(function);

		assertEquals(3l, list.size());
	}


	@Test
	public void mapToInt() {
		List<Integer> list = map.mapToInt();

		assertEquals(10l, list.size());
	}

	@Test
	public void mapToDouble() {
		List<Double> list = map.mapToDouble();

		assertEquals(10l, list.size());
	}

	@Test
	public void mapToLong() {
		List<Long> list = map.mapToLong();

		assertEquals(10l, list.size());
	}
}