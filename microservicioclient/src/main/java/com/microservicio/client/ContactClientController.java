package com.microservicio.client;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import com.microservicio.client.service.ContactClientService;
import com.microservicio.dto.ContactDto;

@Api
@RestController
@RequestMapping("contactclient/")
public class ContactClientController {
private static Logger logger = LoggerFactory.getLogger(ContactClientController.class);
	
	//Se inyecta el servicio de este Cliente
	 @Autowired
	 private ContactClientService contactClientService;
	 
	 
	 /**
	  * Se ocupar para obtener todos los registros
	  * @param 
	  * @return  Lista de objetos ContactDto
	 */
	 @RequestMapping(method = RequestMethod.GET, value ="findall/")
	    @ApiOperation(value = "findall", nickname = "findall", notes = "Return all contacts")
		@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"), 
			@ApiResponse(code = 500, message = "Failure") })
		public  List<ContactDto> findAll() {
	    	logger.debug("ContactsClientController::findAll");
			return contactClientService.findAll();
		}
	 
	 /**
	  * Se ocupa para insertar o actualizar un registro
	  * @param recibira un objeto con la estructura del Dto
	  * @return objeto ContactDto
	 */
	 @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = "save/")
		@ApiOperation(value = "save", nickname = "save", response = ContactDto.class)
		@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class),
				@ApiResponse(code = 201, message = "Created"), 
				@ApiResponse(code = 400, message = "Bad Request"),
				@ApiResponse(code = 403, message = "Forbidden"),
				@ApiResponse(code = 404, message = "Not Found"), 
				@ApiResponse(code = 500, message = "Failure") })
		public ContactDto save(
				@ApiParam(value = "request", required = true) @RequestBody(required = true) ContactDto contact) {
			logger.debug("ContactsClientController::save");
			return contactClientService.save(contact);
		}
	 
	 /**
	  * Se ocupa para eliminar un registro
	  * @param recibira el Id 
	  * @return objeto ContactDto
	 */
	 @RequestMapping(method = {RequestMethod.DELETE}, value = "delete/")
		@ApiOperation(value = "delete", nickname = "delete", response = ContactDto.class)
		@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class),
				@ApiResponse(code = 400, message = "Bad Request"),
				@ApiResponse(code = 403, message = "Forbidden"),
				@ApiResponse(code = 404, message = "Not Found"), 
				@ApiResponse(code = 500, message = "Failure") })
				@ApiImplicitParam(dataType = "string", name = "id")
		public ContactDto delete(String id) {
			logger.debug("ContactsClientController::delete");
			return contactClientService.delete(id);
		}
	    
}
