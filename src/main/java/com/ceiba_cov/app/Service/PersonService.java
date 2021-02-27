package com.ceiba_cov.app.Service;

import java.util.Optional;

import com.ceiba_cov.app.Entity.Person;

public interface PersonService {

	public Iterable<Person> findAll();
	
	public Person save(Person person);
	
	public Optional<Person> findByCedula(Long cedula);
	
}
