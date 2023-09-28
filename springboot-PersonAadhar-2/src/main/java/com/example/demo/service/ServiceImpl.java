package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.hibernate.sql.Update;
import org.springframework.*;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.model.AadharCard;
import com.example.demo.model.Person;
import com.example.demo.repository.AadharRepository;
import com.example.demo.repository.PersonRepository;

@Service
public class ServiceImpl{

	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private AadharRepository aadharRepository;
	
	public Person savePersonWithAadhar(Person person)
	{
		return personRepository.save(person);
	}
	
	public Optional<Person> getPersonbyid(Long id)
	{
		return personRepository.findById(id);
	}
	 
	public Person updatePerson(Person person,Long id)
	{
		Person person2=personRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Person", "id", id));
		person2.setFirstname(person.getFirstname());
		person2.setLastname(person.getLastname());
		person2.setAge(person.getAge());
		personRepository.save(person2);
		return person2;
	}
	public void deletePerson(Long id)
	{
		personRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Person", "id", id));
		personRepository.deleteById(id);
	}
}
