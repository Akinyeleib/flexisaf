package com.flexisaf.tasks.controller;

import com.flexisaf.tasks.Person;
import com.flexisaf.tasks.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/people")
public class PeopleController {

    PersonRepository repository;

    public PeopleController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping
	public List <Person> getPeople() {
		return (List<Person>) this.repository.findAll();
	}

	@GetMapping("{id}")
	public ResponseEntity<Person> getOnePerson(@PathVariable UUID id) {
		try {
            return ResponseEntity.ok(this.repository.findById(id).orElseThrow());
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("{id}")
	public ResponseEntity<String> updatePerson(@PathVariable UUID id, @NonNull @RequestBody Person p) {
		try {
			Person.validate(p);
			Person person = getPerson(id);
            person.setName(p.getName() == null ? person.getName() : p.getName());
            person.setAge(p.getAge() == null ? person.getAge() : p.getAge());
            this.repository.save(person);
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
        this.repository.deleteById(id);
		final String MESSAGE = person.getName() + " profile deleted successfully";
		return ResponseEntity.ok(MESSAGE);
	}

	@PostMapping
	ResponseEntity<String> addPerson(@RequestBody Person person) {
        try {
            Person.validate(person, true);
        } catch (Exception e) {
			return ResponseEntity.badRequest().body("Error creating profile: " + e.getMessage());
        }
        this.repository.save(person);
		return new ResponseEntity<>(person.getName() + "'s profile created successfully", HttpStatus.CREATED);
	}

	Person getPerson(UUID id) throws Exception {
		Optional<Person> person = this.repository.findById(id);
		if (person.isEmpty()) {
			throw new Exception("Person with id: " + id + " not found!");
		}
        return person.get();
	}

}
