package xyz.rexlin600.java8.functional.interfaces;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.rexlin600.java8.model.Goods;

import java.util.function.Function;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FunctionsTest {

	private Functions functions;

	@Before
	public void setUp() throws Exception {
		functions = new Functions();
	}

	@Test
	public void plusOneHundredFunction() {
		Long result = functions.plusOneHundredFunction();

		assertEquals(110l, result.intValue());
	}

	@Test
	public void behavioralParameterization() {
		functions.behavioralParameterization((Function<Goods, String>) goods -> goods.getName().toString());
	}
}