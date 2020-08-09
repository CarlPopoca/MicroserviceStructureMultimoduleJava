package com.microservicio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import com.microservicio.model.Contact;
import com.microservicio.dto.ContactDto;
import com.microservicio.repository.ContactRepository;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.modelmapper.ModelMapper;

//Se debe agregar la anotación Component para que el @ComponentScan la reconozca

@Component
public class ContactServiceImpl implements ContactService {

	private static final Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);

	@Autowired
	private ContactRepository contactRepository;

	@Override
	public List<ContactDto> getContact() {

		List<ContactDto> lista = new ArrayList<>();
		for (Contact contact : contactRepository.findAll()) {
			ModelMapper modelMapper = new ModelMapper();
			ContactDto contactDto = modelMapper.map(contact, ContactDto.class);
			lista.add(contactDto);
			// lista.add(new ContactDto(contact));
		}
		logger.debug("ContactServiceImpl::findAll");
		return lista;
	}

	@Override
	public ContactDto findById(String id) {
		logger.debug("ContactServiceImpl::findById");
		Optional<Contact> contact = contactRepository.findById(id);
		ModelMapper modelMapper = new ModelMapper();
		ContactDto contactDto = modelMapper.map(contact.get(), ContactDto.class);
		return contactDto;
		// return new ContactDto(contact.get());
	}

	@Override
	public void save(ContactDto contact) {
		logger.debug("ContactServiceImpl::save");
		ModelMapper modelMapper = new ModelMapper();
		Contact contactDto = modelMapper.map(contact, Contact.class);
		contactRepository.save(contactDto);
		// contactRepository.save (contact.getEntity());
	}

	@Override
	public void delete(ContactDto contact) {
		logger.debug("ContactServiceImpl::delete");
		ModelMapper modelMapper = new ModelMapper();
		Contact contactDto = modelMapper.map(contact, Contact.class);
		contactRepository.delete(contactDto);
		// contactRepository.delete(contact.getEntity())
	}
}
