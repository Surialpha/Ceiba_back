package com.ceiba_cov.app.Controller;

import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;
import java.time.Instant;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba_cov.app.Entity.Person;
import com.ceiba_cov.app.Service.PersonService;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
@RestController
@RequestMapping("api/person")
public class PersonController {

	@Autowired
	private PersonService personService;

	// Select all, return list of Objects
	@GetMapping
	public ResponseEntity<?> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(personService.findAll());
	}

	// Select by DNI, return true of false, depending if the person exist
	public Boolean getByCedula(Long id) {

		Optional<Person> result = personService.findByCedula(id);

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

	@PostMapping
	public ResponseEntity<?> create(@RequestBody Person person) {
		try {

			// Validate if DNI already Exist
			if (getByCedula(person.getCedula())) {
				String msg = "ya existe esta cedula registrada, verifícala de nuevo";
				return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
			}
			// Validate if person is and adult
			if (validarEdad(person.getFechaNac())) {
				String msg = "El usuario es menor de edad y no puede ser registrado";
				return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
			}

			return ResponseEntity.status(HttpStatus.CREATED).body(personService.save(person));

		} catch (Exception e) {
			String msg = "Algo salió mal, vuelve a intentarlo";
			return new ResponseEntity<>(msg, HttpStatus.CONFLICT);
		}
	}

}
