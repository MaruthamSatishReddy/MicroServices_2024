package com.easyToBuy.Product;

import com.easyToBuy.Product.dto.ProductRequest;
import com.easyToBuy.Product.dto.ProductResponse;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {
	@ServiceConnection
	static MongoDBContainer  mongoDBContainer=new MongoDBContainer("mongo:7.0.7");
	@LocalServerPort
	private Integer localServerPort;
	static {
		mongoDBContainer.start();
	}
	@BeforeEach
	void setUp() {
		RestAssured.baseURI="http://localhost";
		RestAssured.port=localServerPort;
	}
	@Test
	void testCreateProduct()throws Exception{
		ProductRequest productRequest = getProductRequest();
		RestAssured
				.given()
				.contentType("application/json")
				.body(productRequest)
				.when()
				.post("/api/product")
				.then()
				.log().all()
				.statusCode(201)
				.defaultParser(Parser.JSON);
	}
	private ProductRequest getProductRequest() {
		return new ProductRequest("iPhone 13", "iPhone 13", BigDecimal.valueOf(1200));
	}

}
