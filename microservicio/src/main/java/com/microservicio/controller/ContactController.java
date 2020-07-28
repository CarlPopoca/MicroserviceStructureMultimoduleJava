package com.microservicio.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.microservicio.service.ContactService;
import com.microservicio.dto.ContactDto;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class ContactController {

	@Autowired
	private ContactService contactService;

	@GetMapping("/getContact")
	public List<ContactDto> getContact()
	{
		return contactService.getContact();
	}
}
