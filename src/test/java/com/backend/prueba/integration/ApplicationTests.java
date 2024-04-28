package com.backend.prueba.integration;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.backend.prueba.adapter.v1.price.PriceController;
import com.backend.prueba.model.service.ProductPrice;


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
	void testPriceFetchingHappyPath() {
		String responseBody = given()
		.port(port)
		.get("/api/v1/price/1/1/20220101150000")
		.then()
		.statusCode(200)
		.extract()
		.body()
		.asString();

		assertDoesNotThrow(() -> {
			ProductPrice pp = ProductPrice.Builder.buildFromJson(responseBody);

			Timestamp expectedStartDate = 
				Timestamp.valueOf("2020-06-14 15:00:00");
			Timestamp expectedEndDate = 
				Timestamp.valueOf("2020-12-14 15:00:00");
			assertEquals(1L, pp.getBrandId());
			assertEquals(1L, pp.getProductId());
			assertEquals(1L, pp.getPriceListId());
			assertEquals(0.0, pp.getFinalPrice().doubleValue());
			assertEquals(expectedStartDate, pp.getStartDate());
			assertEquals(expectedEndDate, pp.getEndDate());
		});
	}
	
	@Test
	void testPriceFetching1000hDay14Product35455Brand1() {
		String responseBody = given()
		.port(port)
		.get("/api/v1/price/1/1/20200614100000")
		.then()
		.statusCode(200)
		.extract()
		.body()
		.asString();
		System.out.print(responseBody);
	}
	
	@Test
	void testPriceFetching1600hDay14Product35455Brand1() {
		String responseBody = given()
		.port(port)
		.get("/api/v1/price/1/1/20200614160000")
		.then()
		.statusCode(200)
		.extract()
		.body()
		.asString();
		System.out.print(responseBody);
	}
	
	@Test
	void testPriceFetching2100hDay14Product35455Brand1() {
		String responseBody = given()
		.port(port)
		.get("/api/v1/price/1/1/20200614210000")
		.then()
		.statusCode(200)
		.extract()
		.body()
		.asString();
		System.out.print(responseBody);
	}
	
	@Test
	void testPriceFetching1000hDay15Product35455Brand1() {
		String responseBody = given()
		.port(port)
		.get("/api/v1/price/1/1/20200615210000")
		.then()
		.statusCode(200)
		.extract()
		.body()
		.asString();
		System.out.print(responseBody);
	 }

	@Test
	void testPriceFetching2100hDay16Product35455Brand1() {
		String responseBody = given()
		.port(port)
		.get("/api/v1/price/1/1/20200616210000")
		.then()
		.statusCode(200)
		.extract()
		.body()
		.asString();
		System.out.print(responseBody);
	 }
}
