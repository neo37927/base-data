package com.oop.api.reverseengineering;

import com.oop.api.reverseengineering.generate.Generate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReverseEngineeringApplicationTests {

	@Test
	public void contextLoads() {
		try {
			Generate.generate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
