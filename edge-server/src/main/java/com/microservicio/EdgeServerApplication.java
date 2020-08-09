package com.microservicio;

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@EnableZuulProxy
public class EdgeServerApplication {
	public static void main(String[] args) {

		SpringApplication.run(EdgeServerApplication.class, args); 
	}
	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}

}
