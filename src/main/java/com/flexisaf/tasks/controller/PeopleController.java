package com.flexisaf.tasks.controller;

import com.flexisaf.tasks.Person;
import com.flexisaf.tasks.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.lang.Exception;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/people")
public class PeopleController {

    private final PersonService personService;

    public PeopleController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getPeople() {
        return personService.getAllPeople();
    }

    @GetMapping("{id}")
    public ResponseEntity<Person> getOnePerson(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(personService.getPersonById(id));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updatePerson(@PathVariable UUID id, @NonNull @RequestBody Person p) {
        try {
            Person updatedPerson = personService.updatePerson(id, p);
            return ResponseEntity.ok(updatedPerson.getName() + " profile updated successfully");
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> removePerson(@PathVariable UUID id) {
        try {
            Person deletedPerson = personService.deletePerson(id);
            return ResponseEntity.ok(deletedPerson.getName() + " profile deleted successfully");
        } catch (Exception e) {
            return new ResponseEntity<>("Person with id: " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> addPerson(@RequestBody Person person) {
        try {
            Person createdPerson = personService.createPerson(person);
            return new ResponseEntity<>(createdPerson.getName() + "'s profile created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating profile: " + e.getMessage());
        }
    }
}