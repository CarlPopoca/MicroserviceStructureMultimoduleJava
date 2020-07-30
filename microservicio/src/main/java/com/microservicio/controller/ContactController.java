package com.microservicio.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiParam;

import com.microservicio.service.ContactService;
import com.microservicio.dto.ContactDto;

@Api
@RestController
@RequestMapping(path= "contact/")
public class ContactController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ContactService contactService;

    @RequestMapping(method = RequestMethod.GET, value ="findall/")
    @ApiOperation(value = "findall", nickname = "findall", notes = "Return all contacts")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "Not Found"), 
		@ApiResponse(code = 500, message = "Failure") })
	public List<ContactDto> getContact()
	{
		return contactService.getContact();
	}
    
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = "save/")
   	@ApiOperation(value = "save", nickname = "save", response = ContactDto.class)
   	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class),
   			@ApiResponse(code = 201, message = "Created"), 
   			@ApiResponse(code = 400, message = "Bad Request"),
   			@ApiResponse(code = 403, message = "Forbidden"),
   			@ApiResponse(code = 404, message = "Not Found"), 
   			@ApiResponse(code = 500, message = "Failure") })
   	public ContactDto save(HttpServletRequest request,
   			@ApiParam(value = "request", required = true) @RequestBody(required = true) ContactDto contact) {
   		contactService.save(contact);
   		logger.debug("successful contact saving");
   		return new ContactDto(contact.getId(), contact.getName(), contact.getGender(), contact.getMobile());
   	}
       
    @RequestMapping(method = {RequestMethod.DELETE}, value = "delete/")
   	@ApiOperation(value = "delete", nickname = "delete", response = ContactDto.class)
   	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class),
   			@ApiResponse(code = 400, message = "Bad Request"),
   			@ApiResponse(code = 403, message = "Forbidden"),
   			@ApiResponse(code = 404, message = "Not Found"), 
   			@ApiResponse(code = 500, message = "Failure") })
   			@ApiImplicitParam(dataType = "string", name = "id")
   	public ContactDto delete(String id) {
   		ContactDto contact = contactService.findById(id);
   		contactService.delete(contact);
   		logger.debug("successful contact removal");
   		return contact;
   	}
}
