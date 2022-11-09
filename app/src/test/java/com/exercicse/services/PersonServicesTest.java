package com.exercicse.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exercicse.data.vo.v1.PersonVO;
import com.exercicse.exception.RequiredObjectIsNullException;
import com.exercicse.mapper.DozerMapper;
import com.exercicse.model.Person;
import com.exercicse.repositories.PersonRepository;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {
	
	@Mock
	private Person input;
	
	@Mock
	private PersonRepository repository;
	
	@InjectMocks
	private PersonServices service;

	@BeforeEach
	void setUp() throws Exception {
		input = new Person();
		input.setId(1l);
		input.setFistName("Paul");
		input.setLastName("Pirce");
		input.setAdress("Boston");
		input.setGender("Male");
		MockitoAnnotations.openMocks(this);
		
	
		
	}

	@Test
	void testFindAll() {
		Person person = new Person();
		person.setId(1l);
		person.setFistName("Kevin");
		
		Person person1 = new Person();
		person1.setId(2l);
		person1.setFistName("Paul");
		
		List<Person> people = new ArrayList<>();
		people.add(person);
		people.add(person1);
		
		Mockito.when(repository.findAll()).thenReturn(people);
		
		var result = service.findAll();
		
		assertNotNull(result);
		assertEquals("Kevin", result.get(0).getFistName());
		assertEquals(2l, result.get(1).getKey());
		assertTrue(result.size() == 2);
		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		
		
		
	}

	@Test
	void testFindById() {
		Person person = new Person();
		person.setId(1l);
		
		Mockito.when(repository.findById(1L)).thenReturn(Optional.of(person));
		
		var result = service.findById(1l);
		
		assertNotNull(result);
		assertNotNull(result.getLinks());
		assertNotNull(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals(1l, result.getKey());
	}

	@Test
	void testCreate() {
		
		Person person = new Person();
		person.setId(1l);
		person.setFistName("Paul");
		
		Person entity = new Person();
		entity.setId(1l);
		entity.setFistName("Paul");
		
		PersonVO personVo = new PersonVO();
		personVo.setKey(1l);
		personVo.setFistName("Paul");
		
		Mockito.when(repository.save(person)).thenReturn(entity);
		
		var result = service.create(personVo);
		assertNotNull(result);
		assertNotNull(result.getLinks());
		assertNotNull(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals(1l, result.getKey());
		
	}
	
	@Test
	void testCreateWithNullPerson() {
		
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.create(null);
		});

		String expectedMessage = "It is not allowed to persist a null object";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
		
	}

//	@Test
//	void testCreateV2() {
//		fail("Not yet implemented");
//	}
	

	@Test
	void testUpdateWithNullPerson() {
		
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.create(null);
		});

		String expectedMessage = "It is not allowed to persist a null object";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
		
	}

//	@Test
//	void testDelete() {
//		
//	
//	}

}
