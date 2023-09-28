package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Person;
import com.example.demo.service.ServiceImpl;

@RestController
public class PersonController {

	@Autowired
	private ServiceImpl impl;
	
	@PostMapping("/save")
	public ResponseEntity<Person> savePersonWithAadhar(@RequestBody Person person)
	{
		Person savedPerson=impl.savePersonWithAadhar(person);
		return ResponseEntity.ok(savedPerson);
	}
	@GetMapping("/get/{id}")
	public ResponseEntity<Person> getPersonById(@PathVariable Long id)
	{
		Optional<Person> person=impl.getPersonbyid(id);
		return person.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<Person> updatePerson(@RequestBody Person person,@PathVariable Long id)
	{
		return new ResponseEntity<Person>(impl.updatePerson(person, id),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePerson(@PathVariable Long id)
	{
	   impl.deletePerson(id);
	   return new ResponseEntity<String>("Deleted Successfully from DB",HttpStatus.OK);
	}
}
