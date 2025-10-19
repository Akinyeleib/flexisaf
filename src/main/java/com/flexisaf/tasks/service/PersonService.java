package com.flexisaf.tasks.service;

import com.flexisaf.tasks.Person;
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

    public Person getPersonById(UUID id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Person with id: " + id + " not found!"));
    }

    public Person createPerson(Person person) throws Exception {
        Person.validate(person, true);
        return repository.save(person);
    }

    public Person updatePerson(UUID id, Person updatedPerson) throws Exception {
        Person.validate(updatedPerson);
        Person existingPerson = getPersonById(id);

        if (updatedPerson.getName() != null) {
            existingPerson.setName(updatedPerson.getName());
        }
        if (updatedPerson.getAge() != null) {
            existingPerson.setAge(updatedPerson.getAge());
        }

        return repository.save(existingPerson);
    }

    public Person deletePerson(UUID id) throws Exception {
        Person person = getPersonById(id);
        repository.deleteById(id);
        return person;
    }
}
