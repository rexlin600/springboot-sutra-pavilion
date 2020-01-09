package xyz.rexlin600.java8.functional.interfaces;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import java.util.function.Supplier;

import static org.junit.Assert.*;

/**
 * @description
 * @auther hekunlin
 * @create 2020-01-09 15:16
 */
public class SuppliersTest {

    Suppliers suppliers;

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