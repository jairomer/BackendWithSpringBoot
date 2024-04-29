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

import com.backend.prueba.controllers.v1.price.PriceController;
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
	void testPriceFetching1000hDay14Product35455Brand1() {
		String responseBody = given()
		.port(port)
		.get("/api/v1/price/1/35455/20200614100000")
		.then()
		.statusCode(200)
		.extract()
		.body()
		.asString();

		assertDoesNotThrow(() -> {
			ProductPrice pp = ProductPrice.Builder.buildFromJson(responseBody);
			/* 
			* Price List with id 1 has the largest priority on this timeframe,
			* so it will be the one applied.
			*/
			Timestamp expectedStartDate = 
				Timestamp.valueOf("2020-06-14 00:00:00");
			Timestamp expectedEndDate = 
				Timestamp.valueOf("2020-12-31 23:59:59");
			assertEquals(1L, pp.getBrandId());
			assertEquals(35455L, pp.getProductId());
			assertEquals(1L, pp.getPriceListId());
			assertEquals(35.50, pp.getFinalPrice().doubleValue());
			assertEquals(expectedStartDate, pp.getStartDate());
			assertEquals(expectedEndDate, pp.getEndDate());
		});
	}
	
	@Test
	void testPriceFetching1600hDay14Product35455Brand1() {
		String responseBody = given()
		.port(port)
		.get("/api/v1/price/1/35455/20200614160000")
		.then()
		.statusCode(200)
		.extract()
		.body()
		.asString();

		assertDoesNotThrow(() -> {
			ProductPrice pp = ProductPrice.Builder.buildFromJson(responseBody);
			/* 
			* Price List with id 2 has the largest priority on this timeframe,
			* so it will be the one applied.
			*/
			Timestamp expectedStartDate = 
				Timestamp.valueOf("2020-06-14 15:00:00");
			Timestamp expectedEndDate = 
				Timestamp.valueOf("2020-06-14 18:30:00");
			assertEquals(1L, pp.getBrandId());
			assertEquals(35455L, pp.getProductId());
			assertEquals(2L, pp.getPriceListId());
			assertEquals(25.45, pp.getFinalPrice().doubleValue());
			assertEquals(expectedStartDate, pp.getStartDate());
			assertEquals(expectedEndDate, pp.getEndDate());
		});
	}
	
	@Test
	void testPriceFetching2100hDay14Product35455Brand1() {
		String responseBody = given()
		.port(port)
		.get("/api/v1/price/1/35455/20200614210000")
		.then()
		.statusCode(200)
		.extract()
		.body()
		.asString();

		assertDoesNotThrow(() -> {
			ProductPrice pp = ProductPrice.Builder.buildFromJson(responseBody);
			/* 
			* Price List with id 1 has the largest priority on this timeframe,
			* so it will be the one applied.
			*/
			Timestamp expectedStartDate = 
				Timestamp.valueOf("2020-06-14 00:00:00");
			Timestamp expectedEndDate = 
				Timestamp.valueOf("2020-12-31 23:59:59");
			assertEquals(1L, pp.getBrandId());
			assertEquals(35455L, pp.getProductId());
			assertEquals(1L, pp.getPriceListId());
			assertEquals(35.50, pp.getFinalPrice().doubleValue());
			assertEquals(expectedStartDate, pp.getStartDate());
			assertEquals(expectedEndDate, pp.getEndDate());
		});
	}
	
	@Test
	void testPriceFetching1000hDay15Product35455Brand1() {
		String responseBody = given()
		.port(port)
		.get("/api/v1/price/1/35455/20200615100000")
		.then()
		.statusCode(200)
		.extract()
		.body()
		.asString();

		assertDoesNotThrow(() -> {
			ProductPrice pp = ProductPrice.Builder.buildFromJson(responseBody);
			/* 
			* Price List with id 3 has the largest priority on this timeframe,
			* so it will be the one applied.
			*/
			Timestamp expectedStartDate = 
				Timestamp.valueOf("2020-06-15 00:00:00");
			Timestamp expectedEndDate = 
				Timestamp.valueOf("2020-06-15 11:00:00");
			assertEquals(1L, pp.getBrandId());
			assertEquals(35455L, pp.getProductId());
			assertEquals(3L, pp.getPriceListId());
			assertEquals(30.50, pp.getFinalPrice().doubleValue());
			assertEquals(expectedStartDate, pp.getStartDate());
			assertEquals(expectedEndDate, pp.getEndDate());
		});
	 }

	@Test
	void testPriceFetching2100hDay16Product35455Brand1() {
		String responseBody = given()
		.port(port)
		.get("/api/v1/price/1/35455/20200616210000")
		.then()
		.statusCode(200)
		.extract()
		.body()
		.asString();

		assertDoesNotThrow(() -> {
			ProductPrice pp = ProductPrice.Builder.buildFromJson(responseBody);
			/* 
			* Price List with id 4 has the largest priority on this timeframe,
			* so it will be the one applied.
			*/
			Timestamp expectedStartDate = 
				Timestamp.valueOf("2020-06-15 16:00:00");
			Timestamp expectedEndDate = 
				Timestamp.valueOf("2020-12-31 23:59:59");
			assertEquals(1L, pp.getBrandId());
			assertEquals(35455L, pp.getProductId());
			assertEquals(4L, pp.getPriceListId());
			assertEquals(38.95, pp.getFinalPrice().doubleValue());
			assertEquals(expectedStartDate, pp.getStartDate());
			assertEquals(expectedEndDate, pp.getEndDate());
		});
	 }

	 
	@Test
	void testPriceFetchinNonExistantPath() {
		given()
		.port(port)
		.get("/api/v1/price/1/1/20220101150000")
		.then()
		.statusCode(404);
	}
	
	@Test
	void testPriceFetchinNegativeBrandIdInPath() {
		given()
		.port(port)
		.get("/api/v1/price/-1/1/20220101150000")
		.then()
		.statusCode(400);
	}
	
	@Test
	void testPriceFetchinNegativeProductIdInPath() {
		given()
		.port(port)
		.get("/api/v1/price/1/-1/20220101150000")
		.then()
		.statusCode(400);
	}
	
	@Test
	void testPriceFetchinBadDateNonPattern() {
		given()
		.port(port)
		.get("/api/v1/price/1/1/2020")
		.then()
		.statusCode(400);
	}
	
	@Test
	void testPriceFetchinBadDateBadChracters() {
		given()
		.port(port)
		.get("/api/v1/price/1/1/abcd")
		.then()
		.statusCode(400);
	}

	@Test
	void testPriceFetchinProductIdBadChracters() {
		given()
		.port(port)
		.get("/api/v1/price/1/abcd/20220101150000")
		.then()
		.statusCode(400);
	}

	@Test
	void testPriceFetchinBrandIdBadChracters() {
		given()
		.port(port)
		.get("/api/v1/price/abcd/1/20220101150000")
		.then()
		.statusCode(400);
	}
}
