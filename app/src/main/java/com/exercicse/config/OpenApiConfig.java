package com.exercicse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class OpenApiConfig {
	
	@Bean
	public OpenAPI customOpenAPI () {
		return new OpenAPI()
				.info(new io.swagger.v3.oas.models.info.Info()
						.title("SpringBoot Trainning")
						.version("v1")
						.description("Developing projec")
						.termsOfService("https://localhost8080")
						.license(
							new io.swagger.v3.oas.models.info.License()
								.name("Apache")
								.url("https://localhost8080")
								)
						);
	}
	

}
