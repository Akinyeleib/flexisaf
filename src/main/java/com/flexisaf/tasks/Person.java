package com.flexisaf.tasks;

import java.util.UUID;

public class Person {

    private UUID id;
    private int age;
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
        if (person.age < MINIMUM_AGE) {
            throw new Exception("Minimum age is " + MINIMUM_AGE);
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
