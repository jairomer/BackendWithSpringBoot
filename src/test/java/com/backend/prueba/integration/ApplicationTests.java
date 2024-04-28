package com.backend.prueba.integration;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.text.StringContainsInOrder.stringContainsInOrder;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.backend.prueba.adapter.v1.price.PriceController;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private PriceController priceController;
	
	@Test
	void contextLoads() {
		assertThat(priceController, notNullValue());
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

	@Test
	void testPriceFetchingHappyPath() {
		String responseBody = given()
		.port(port)
		.get("/api/v1/price/1/1/20220101150000")
		.then()
		.statusCode(200)
		.extract()
		.body()
		.asString();
		//System.out.print(responseBody);
	}
}
