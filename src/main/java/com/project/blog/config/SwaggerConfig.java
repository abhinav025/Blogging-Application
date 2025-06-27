package com.project.blog.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	
	
	private Apikey apiKeys() {
		return new ApiKey("JWT", AppConstants.AUTHORIZATION_HEADER, "header");
	}
	
	@Bean
//	public OpenAPI customOpenAPI() {
//		return new OpenAPI()
//				.info(new Info()
//						.title("Blog App API")
//						.version("1.0.0")
//						.description("Spring Boot blog application backend using Spring Boot"));
//	}
	public Docket api() {
	    return new Docket(DocumentationType.SWAGGER_2)
	            .apiInfo(getInfo())
	            .securityContexts()
	            .securitySchemes(Arrays.asList(apiKeys))
	            .select()
	            .apis(RequestHandlerSelectors.any())
	            .paths(PathSelectors.any())
	            .build();
	}
}
