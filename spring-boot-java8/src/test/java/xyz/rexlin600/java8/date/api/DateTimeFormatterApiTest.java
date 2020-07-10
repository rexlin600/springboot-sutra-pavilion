package xyz.rexlin600.java8.date.api;

import org.junit.Before;
import org.junit.Test;

public class DateTimeFormatterApiTest {

	private DateTimeFormatterApi dateTimeFormatterApi;

	@Before
	public void setUp() throws Exception {
		dateTimeFormatterApi = new DateTimeFormatterApi();
	}

	@Test
	public void format() {
		dateTimeFormatterApi.format();
	}
}