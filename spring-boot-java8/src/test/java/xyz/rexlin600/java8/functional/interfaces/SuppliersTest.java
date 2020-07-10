package xyz.rexlin600.java8.functional.interfaces;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SuppliersTest {

	private Suppliers suppliers;

	@Before
	public void setUp() throws Exception {
		suppliers = new Suppliers();
	}

	@Test
	public void suppliers() {
		Supplier<String> stringSupplier = suppliers.suppliers("Good Dog");
		assertEquals("Hi,Good Dog", stringSupplier.get());
	}

	@Test
	public void suppliers1() {
		Supplier<Double> suppliers = this.suppliers.suppliers(20.01D);
		System.out.println(suppliers.get());
	}
}