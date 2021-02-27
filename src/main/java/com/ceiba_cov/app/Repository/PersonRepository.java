package com.ceiba_cov.app.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ceiba_cov.app.Entity.Person;


public interface PersonRepository extends JpaRepository<Person, Long> {

	@Query(value = "SELECT * FROM ceiba_cov.person WHERE cedula = ?1",nativeQuery = true)
	public Optional<Person> findByCedula(Long cedula);
	
}
