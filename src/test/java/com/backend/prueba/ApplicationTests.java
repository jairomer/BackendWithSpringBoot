package com.backend.prueba;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.text.StringContainsInOrder.stringContainsInOrder;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.restassured.RestAssured; 


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private BaseController baseController;
	
	@Test
	void contextLoads() {
		assertThat(baseController, notNullValue());
	}

	@Test
	void testHelloWorld() {
		String responseBody = given()
		.port(port)
		.get("/").then()
		.statusCode(200)
		.extract()
		.body()
		.asString();
		
		assertThat(responseBody, stringContainsInOrder("Hello, World!"));
	}

}
