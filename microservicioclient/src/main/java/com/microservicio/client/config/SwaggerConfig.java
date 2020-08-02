package com.microservicio.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import io.swagger.annotations.Api;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.builders.ApiInfoBuilder;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	   @Bean	
	    public Docket api() { 
		   return new Docket(DocumentationType.SWAGGER_2)
		    		  .groupName("microservicio")
		              .apiInfo(apiInfo())
		              .select()
		                  .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
		                  .paths(PathSelectors.any())
		                  .build()
		              .pathMapping("/")
		              .genericModelSubstitutes(ResponseEntity.class)
		              .useDefaultResponseMessages(false);                               
	    }

	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("microservicioclient")
	                .description("Microservicio de Java")
	                .version("0.1-SNAPSHOT")
	                .license("Open source licensing")
	                .build();
	    }
}
