package com.project.blog.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
	
	 public static final String SECURITY_SCHEME_NAME = "JWT";

	    @Bean
	    public OpenAPI blogOpenAPI() {
	        return new OpenAPI()
	                .info(new Info()
	                        .title("Blog Application API")
	                        .description("Spring Boot blog application backend using Spring Boot")
	                        .version("1.0.0"))
	                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
	                .components(new Components()
	                        .addSecuritySchemes(SECURITY_SCHEME_NAME, 
	                            new SecurityScheme()
	                                .name(SECURITY_SCHEME_NAME)
	                                .type(SecurityScheme.Type.HTTP)
	                                .scheme("bearer")
	                                .bearerFormat("JWT")));
	    }
	
}
