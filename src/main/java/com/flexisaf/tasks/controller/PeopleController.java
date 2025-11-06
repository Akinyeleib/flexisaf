package com.flexisaf.tasks.controller;

import com.flexisaf.tasks.dto.CreatePersonDto;
import com.flexisaf.tasks.dto.UpdatePersonDto;
import com.flexisaf.tasks.exception.PersonNotFoundException;
import com.flexisaf.tasks.model.Person;
import com.flexisaf.tasks.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
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
    public ResponseEntity<String> updatePerson(@PathVariable UUID id, @NonNull @RequestBody @Valid UpdatePersonDto person) throws PersonNotFoundException {
        Person updatedPerson = personService.updatePerson(id, person);
        return ResponseEntity.ok(updatedPerson.getName() + " profile updated successfully");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> removePerson(@PathVariable UUID id) throws PersonNotFoundException {
        Person deletedPerson = personService.deletePerson(id);
        return ResponseEntity.ok(deletedPerson.getName() + " profile deleted successfully");
    }

    @PostMapping
    public ResponseEntity<String> addPerson(@RequestBody @Valid CreatePersonDto person) {
        Person createdPerson = personService.createPerson(person);
        return new ResponseEntity<>(createdPerson.getName() + "'s profile created successfully", HttpStatus.CREATED);
    }

}
