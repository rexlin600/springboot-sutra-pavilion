package xyz.rexlin600.java8.date.api;

import org.junit.Before;
import org.junit.Test;

public class InstantApiTest {

	private InstantApi instantApi;

	@Before
	public void setUp() throws Exception {
		instantApi = new InstantApi();
	}

	@Test
	public void now() {
		instantApi.now();
	}

	@Test
	public void mill() {
		instantApi.mill();
	}

	@Test
	public void build() {
		instantApi.build();
	}

	@Test
	public void compare() {
		instantApi.compare();
	}
}