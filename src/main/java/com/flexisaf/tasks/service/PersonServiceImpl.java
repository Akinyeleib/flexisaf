package com.flexisaf.tasks.service;

import com.flexisaf.tasks.PersonRepository;
import com.flexisaf.tasks.dto.CreatePersonDto;
import com.flexisaf.tasks.dto.UpdatePersonDto;
import com.flexisaf.tasks.exception.PersonNotFoundException;
import com.flexisaf.tasks.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository repository;

    public List<Person> getAllPeople() {
        return (List<Person>) repository.findAll();
    }

    public Person getPersonById(UUID id) throws PersonNotFoundException {
        return repository.findById(id).orElseThrow(() -> new PersonNotFoundException("Person with id: " + id + " not found!"));
    }

    public Person createPerson(CreatePersonDto personDto) {

        Person person = Person.builder()
                .age(personDto.getAge())
                .name(personDto.getName())
                .email(personDto.getEmail())
                .enabled(true)
                .phoneNumber(personDto.getPhoneNumber())
                .department(personDto.getDepartment())
                .build();

        return repository.save(person);
    }

    public Person updatePerson(UUID id, UpdatePersonDto person) throws PersonNotFoundException {
        Person existingPerson = getPersonById(id);

        Person updatedPerson = Person.builder()
                .age(person.getAge())
                .name(person.getName())
                .email(person.getEmail())
                .enabled(true)
                .phoneNumber(person.getPhoneNumber())
                .department(person.getDepartment())
                .build();

        if (updatedPerson.getName() != null) {
            existingPerson.setName(updatedPerson.getName());
        }
        if (updatedPerson.getAge() != null) {
            existingPerson.setAge(updatedPerson.getAge());
        }

        return repository.save(existingPerson);
    }

    public Person deletePerson(UUID id) throws PersonNotFoundException {
        Person person = getPersonById(id);
        repository.deleteById(id);
        return person;
    }
}
