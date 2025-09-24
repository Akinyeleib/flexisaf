package com.flexisaf.tasks.controller;

import com.flexisaf.tasks.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/people")
public class PeopleController {

	List <Person> persons = new ArrayList<>();

	@GetMapping
	public List <Person> getPeople() {
		return persons;
	}

	@GetMapping("{id}")
	public ResponseEntity<Person> getOnePerson(@PathVariable UUID id) {
		try {
			return ResponseEntity.ok(getPerson(id));
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("{id}")
	public ResponseEntity<String> updatePerson(@PathVariable UUID id, @NonNull @RequestBody Person p) {
		try {
			Person.validate(p);
			Person person = getPerson(id);
			getPerson(id).setName(p.getName() == null ? person.getName() : p.getName());
			getPerson(id).setAge(p.getAge() == null ? person.getAge() : p.getAge());
			return ResponseEntity.ok(person.getName() + " profile updated successfully");
		} catch (Exception e) {
			return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> removePerson(@PathVariable UUID id ) {
        Person person;
        try {
            person = getPerson(id);
        } catch (Exception e) {
			return new ResponseEntity<>("Person with id: " + id + " not found", HttpStatus.NOT_FOUND);
        }
		boolean removed = persons.remove(person);
		final String MESSAGE = removed ? person.getName() + " profile deleted successfully" : "Error deleting Person with id: " + id;
		final HttpStatus status = removed ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
		return new ResponseEntity<>(MESSAGE, status);
	}

	@PostMapping
	ResponseEntity<String> addPerson(@RequestBody Person person) {
        try {
            Person.validate(person, true);
        } catch (Exception e) {
			return ResponseEntity.badRequest().body("Error creating profile: " + e.getMessage());
        }
		person.setId(UUID.randomUUID());
        persons.add(person);
		return new ResponseEntity<>(person.getName() + "'s profile created successfully", HttpStatus.CREATED);
	}

	Person getPerson(UUID id) throws Exception {
		Optional<Person> person = persons.stream().filter(p1 -> p1.getId().equals(id)).findAny();
		if (person.isEmpty()) {
			throw new Exception("Person with id: " + id + " not found!");
		}
        return person.get();
	}

}
