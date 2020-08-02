package com.microservicio.client.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.client.RestTemplate;
import com.microservicio.client.service.ContactClientService;

@Configuration
public class ContactClientConfig {
	@Bean
	@RefreshScope
	ContactClientService contactClientService()
	{
		return new ContactClientService();
	}
	
	 /**
	  * Se agrega la anotación @LoadBalanced a RestTemplate para que integre Ribbon con capacidad de balanceo
	  * @param 
	  * @return  Lista de objetos ContactDto
	 */
	@Bean
	@LoadBalanced
	RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
