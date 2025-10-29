package com.flexisaf.tasks.controller;

import com.flexisaf.tasks.exception.FailedValidationException;
import com.flexisaf.tasks.exception.PersonNotFoundException;
import com.flexisaf.tasks.model.Person;
import com.flexisaf.tasks.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Person> getOnePerson(@PathVariable UUID id) throws PersonNotFoundException {
        return ResponseEntity.ok(personService.getPersonById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updatePerson(@PathVariable UUID id, @NonNull @RequestBody Person p) throws PersonNotFoundException, FailedValidationException {
        Person updatedPerson = personService.updatePerson(id, p);
        return ResponseEntity.ok(updatedPerson.getName() + " profile updated successfully");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> removePerson(@PathVariable UUID id) throws PersonNotFoundException {
        Person deletedPerson = personService.deletePerson(id);
        return ResponseEntity.ok(deletedPerson.getName() + " profile deleted successfully");
    }

    @PostMapping
    public ResponseEntity<String> addPerson(@RequestBody Person person) throws PersonNotFoundException, FailedValidationException {
        Person createdPerson = personService.createPerson(person);
        return new ResponseEntity<>(createdPerson.getName() + "'s profile created successfully", HttpStatus.CREATED);
    }
}
