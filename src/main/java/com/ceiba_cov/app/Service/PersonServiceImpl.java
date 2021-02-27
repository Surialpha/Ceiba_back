package com.ceiba_cov.app.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba_cov.app.Entity.Person;
import com.ceiba_cov.app.Repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository repository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Person> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional
	public Person save(Person person) {
		return repository.save(person);
	}

	@Override
	public Optional<Person> findByCedula(Long cedula) {
		return repository.findByCedula(cedula);
	}

}
