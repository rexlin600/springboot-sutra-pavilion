package xyz.rexlin600.java8.stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.rexlin600.java8.model.Goods;

import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilterTest {

	private Filter filter;

	@Before
	public void setUp() throws Exception {
		filter = new Filter();
	}

	@Test
	public void filerColor() {
		Long count = filter.filerColor();

		assertEquals(3l, count.longValue());
	}

	@Test
	public void filerPosition() {
		Long count = filter.filerPosition();

		assertEquals(2l, count.longValue());
	}

	@Test
	public void filerBoth() {
		Long count = filter.filerBoth();

		assertEquals(1l, count.longValue());
	}

	@Test
	public void filer() {
		// 通过传入一个谓词对象可以非常方便的辅助我们完成 Filter 操作，代码复用度高
		Long count = filter.filer(new Predicate<Goods>() {
			@Override
			public boolean test(Goods o) {
				return o.getPosition().equals("研发工程师") && o.getColor().equals("天蓝色");
			}
		});

		assertEquals(1l, count.longValue());
	}
}