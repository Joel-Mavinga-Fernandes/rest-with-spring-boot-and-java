package com.exercicse.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import com.exercicse.controller.PersonController;
import com.exercicse.data.vo.v1.PersonVO;
import com.exercicse.data.vo.v2.PersonVOV2;
import com.exercicse.exception.RequiredObjectIsNullException;
import com.exercicse.exception.ResourceNotFoundException;
import com.exercicse.mapper.DozerMapper;
import com.exercicse.mapper.custom.PersonMapper;
import com.exercicse.model.Person;
import com.exercicse.repositories.PersonRepository;

@Service
public class PersonServices {
	
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	private PersonRepository repository; 
	
	@Autowired
	private PersonMapper personMapper;
	
	public List<PersonVO> findAll () {
		
		logger.info("Finding all persons");
		var persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
		persons.stream()
		.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		return persons;
	}
	
	public PersonVO findById (Long id) {
		
		logger.info("Finding one person");
		
			var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
			PersonVO vo = DozerMapper.parseObjects(entity, PersonVO.class);
			vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
			return vo;
	}
	
	public PersonVO create (PersonVO person) {
		
		logger.info("Creating one person");
		if(person == null) throw new RequiredObjectIsNullException();
		
		var entity = DozerMapper.parseObjects(person, Person.class);
		var saved = repository.save(entity);
		return DozerMapper.parseObjects(saved, PersonVO.class);
	}
	
	public PersonVOV2 createV2 (PersonVOV2 person) {
		
		logger.info("Creating one person");
		
		var entity = DozerMapper.parseObjects(person, Person.class);
		var saved = repository.save(entity);
		return personMapper.convertEntityToVo(saved);
		 
	}
	
	public PersonVO update (PersonVO person) {
		
		logger.info("Updateting one person");
		if(person == null) throw new RequiredObjectIsNullException();
		
		var entity = repository.findById(person.getKey())
		.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		entity.setFistName(person.getFistName());
		entity.setLastName(person.getLastName());
		entity.setGender(person.getGender());
		entity.setAdress(person.getAdress());
		
		var vo = DozerMapper.parseObjects(repository.save(entity), PersonVO.class);
		
		return vo;
	}
	
	public void delete (Long id) {
		
		logger.info("deleting one person");
		repository.deleteById(id);
	
	}
	
	

}
