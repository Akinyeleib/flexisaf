package com.flexisaf.tasks.service;

import com.flexisaf.tasks.exception.PersonNotFoundException;
import com.flexisaf.tasks.model.Person;
import com.flexisaf.tasks.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PersonService {

    @Autowired
    PersonRepository repository;

    public List<Person> getAllPeople() {
        return (List<Person>) repository.findAll();
    }

    public Person getPersonById(UUID id) throws PersonNotFoundException {
        return repository.findById(id).orElseThrow(() -> new PersonNotFoundException("Person with id: " + id + " not found!"));
    }

    public Person createPerson(Person person) {
        return repository.save(person);
    }

    public Person updatePerson(UUID id, Person updatedPerson) throws PersonNotFoundException {
        Person existingPerson = getPersonById(id);

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
