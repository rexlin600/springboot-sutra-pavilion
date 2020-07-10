package xyz.rexlin600.java8.defaultmethod;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultMethodTest {

	private DefaultMethod.InnerDefaultMethod defaultMethod;

	@Before
	public void setUp() throws Exception {
		defaultMethod = new DefaultMethod.InnerDefaultMethod();
	}

	@Test
	public void hello() {
		defaultMethod.hello();
	}


}