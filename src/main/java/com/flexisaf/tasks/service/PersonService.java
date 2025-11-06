package com.flexisaf.tasks.service;

import com.flexisaf.tasks.dto.CreatePersonDto;
import com.flexisaf.tasks.dto.UpdatePersonDto;
import com.flexisaf.tasks.exception.PersonNotFoundException;
import com.flexisaf.tasks.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface PersonService {

    List<Person> getAllPeople();

    Person getPersonById(UUID id) throws PersonNotFoundException;

    Person createPerson(CreatePersonDto person);

    Person updatePerson(UUID id, UpdatePersonDto updatedPerson) throws PersonNotFoundException;

    Person deletePerson(UUID id) throws PersonNotFoundException;

}
