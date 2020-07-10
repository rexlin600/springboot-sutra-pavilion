package xyz.rexlin600.java8.functional.interfaces;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PredicatesTest {

	private Predicates predicates;

	@Before
	public void setUp() throws Exception {
		predicates = new Predicates();
	}

	@Test
	public void judgeNumberOne() {
		boolean max = predicates.judgeNumberOne(1000);
		boolean min = predicates.judgeNumberOne(10);

		assertTrue(max);
		assertFalse(min);
	}

	@Test
	public void judgeNumberTwo() {
		boolean max = predicates.judgeNumberTwo(1000);
		boolean min = predicates.judgeNumberTwo(10);

		assertTrue(max);
		assertFalse(min);
	}

	@Test
	public void judgeNumberNegate() {
		boolean max = predicates.judgeNumberNegate(1000);
		boolean min = predicates.judgeNumberNegate(10);

		assertFalse(max);
		assertTrue(min);

	}
}