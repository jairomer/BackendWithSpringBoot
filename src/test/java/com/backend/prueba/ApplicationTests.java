package com.backend.prueba;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.core.IsNull.notNullValue;; 


@SpringBootTest
class ApplicationTests {

	@Autowired
	private BaseController baseController;

	@Test
	void contextLoads() {
		assertThat(baseController, notNullValue());
	}

}
