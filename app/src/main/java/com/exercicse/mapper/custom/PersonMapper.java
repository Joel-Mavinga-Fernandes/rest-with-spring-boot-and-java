package com.exercicse.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.exercicse.data.vo.v2.PersonVOV2;
import com.exercicse.model.Person;

@Service
public class PersonMapper {
	
	public PersonVOV2 convertEntityToVo(Person person) {
		PersonVOV2 vo = new PersonVOV2();
		vo.setId(person.getId());
		vo.setAdress(person.getAdress());
		vo.setBirthday(new Date());
		vo.setFistName(person.getFistName());
		vo.setLastName(person.getLastName());
		vo.setGender(person.getGender());
		
		return vo;
	}
	
	public Person convertVoEntity(PersonVOV2 person) {
		Person entity = new Person();
		entity.setId(person.getId());
		entity.setAdress(person.getAdress());
		//vo.setBirthday(new Date());
		entity.setFistName(person.getFistName());
		entity.setLastName(person.getLastName());
		entity.setGender(person.getGender());
		
		return entity;
	}
		

}
