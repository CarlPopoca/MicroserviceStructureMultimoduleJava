package com.microservicio.client.service;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.microservicio.dto.ContactDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@ConfigurationProperties(prefix="microservicio.contact")
public class ContactClientService {
	
	private static Logger logger = LoggerFactory.getLogger(ContactClientService.class);
	
	//A estas propiedades se les pondra la url correspondiente y que se obtendra del archivo de configuración de git
	private String findAllUrl;
	private String saveUrl;
	private String deleteUrl;
	
	 //Se ocupa RestTemplate de Spring que integra Ribbon como cliente con capacidad de balanceo para consumir el microservicio 
	@Autowired
	private RestTemplate restTemplate;
	
	//En caso de que se suceda un error se controlara se ejecuta el método retrieveFallbackfindAll para controlar el resultado esperado
    @HystrixCommand(fallbackMethod="retrieveFallbackfindAll")
	public  List<ContactDto> findAll() {
    	logger.debug("ContactClientService::findAll");
		List<ContactDto> getList;
		ResponseEntity<List<ContactDto>> response = restTemplate.exchange(findAllUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<ContactDto>>() {});
		getList = response.getBody();
		return getList;
	}
	
	//En caso de que se suceda un error se controlara se ejecuta el método retrieveFallbacksave para controlar el resultado esperado
	@HystrixCommand(fallbackMethod= "retrieveFallbacksave")
	public ContactDto save(ContactDto contact){
		logger.debug("ContactClientService::save");
		return restTemplate.postForObject(saveUrl, contact, ContactDto.class);
	}
	
	//En caso de que se suceda un error se controlara se ejecuta el método retrieveFallbacksave para controlar el resultado esperado
	@HystrixCommand(fallbackMethod="retrieveFallbacksave")
	public ContactDto delete(String id){
		//Map < String, String > params = new HashMap < String, String > ();
		//return restTemplate.delete(contactUrl, params);
		logger.debug("ContactClientService::delete");
		return restTemplate.postForObject(deleteUrl, id, ContactDto.class);
	}

	public List<ContactDto> retrieveFallbackfindAll(){
		List<ContactDto> getList = null;
		return getList;
	}
	
	public ContactDto retrieveFallbacksave(ContactDto contact)
	{
		return new ContactDto();
	}

	public String getFindAllUrl() {
		return findAllUrl;
	}

	public void setFindAllUrl(String findAllUrl) {
		this.findAllUrl = findAllUrl;
	}

	public String getSaveUrl() {
		return saveUrl;
	}

	public void setSaveUrl(String saveUrl) {
		this.saveUrl = saveUrl;
	}

	public String getDeleteUrl() {
		return deleteUrl;
	}

	public void setDeleteUrl(String deleteUrl) {
		this.deleteUrl = deleteUrl;
	}



}
