package com.exercicse.integrationtest;

import static io.restassured.RestAssured.given;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.exercicse.configs.TestConfigs;
import com.exercicse.integrationtest.testcontainers.AbstractIntegrationTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationsTest extends AbstractIntegrationTest {
	
	@Test
	public void shoulDisplaySwaggerUiPage() {
		var content =
		given()
			.basePath("/swagger-ui/index.html")
			.port(TestConfigs.SERVER_PORT)
			.when()
				.get()
			.then()
				.statusCode(200)
			.extract()
				.body()
				.asString();
			assertTrue(content.contains("Swagger UI"));
		
	}

}
