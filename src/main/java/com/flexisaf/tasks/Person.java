package com.flexisaf.tasks;

import java.util.UUID;

public class Person {

    private UUID id;
    private Integer age = null;
    private static final int MINIMUM_AGE = 17;
    private String name;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    public static void validate(Person person) throws Exception {
        validate(person, false);
    }

    public static void validate(Person person, boolean isPost) throws Exception {
        final boolean agePresent = person.getAge() != null;
        final boolean namePresent = person.getName() != null;
        final boolean allRequiredPresent = agePresent && namePresent;
        if (isPost && !allRequiredPresent ) {
            if (!agePresent && !namePresent) {
                throw new Exception("Name and Age are required fields");
            } else if (!agePresent) {
                throw new Exception("Age is a required field");
            } else if (!namePresent) {
                throw new Exception("Name is a required field");
            }
        }
        if (!agePresent && !namePresent) {
            throw new Exception("At least one of name or age must be provided");
        }
        if (agePresent && person.age < MINIMUM_AGE) {
            throw new Exception("Minimum age is " + MINIMUM_AGE);
        }
        if (namePresent && person.getName().isBlank()) {
            throw new Exception("Name cannot be blank");
        }
        if (namePresent && person.getName().trim().length() < 3) {
            throw new Exception("Name is too short");
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
