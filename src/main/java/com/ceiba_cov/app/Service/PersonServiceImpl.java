package com.ceiba_cov.app.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
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
	
	
	// Select by DNI, return true of false, depending if the person exist
	public Boolean getByCedula(Long id) {

		Optional<Person> result = repository.findByCedula(id);

		if (!result.isPresent()) {
			return false;
		}

		return true;
	}

	public Boolean validarEdad(Date date) {

		Instant instant = date.toInstant();
		ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
		LocalDate fechaRecibida = zone.toLocalDate();
		Period period = Period.between(fechaRecibida, LocalDate.now());

		if (period.getYears() >= 18) {
			return false;
		} else {
			return true;
		}

	}


}
