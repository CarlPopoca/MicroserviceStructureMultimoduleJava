package com.microservicio.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservicio.dto.ContactDto;

@Service
@Transactional
public interface ContactService {
	public List<ContactDto> getContact();
	
	public ContactDto findById(String id);

	public void save(ContactDto contact);

	public void delete(ContactDto contact);

	
}


