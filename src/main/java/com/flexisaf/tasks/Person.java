package com.flexisaf.tasks;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Integer age = null;

    private static final int MINIMUM_AGE = 17;

    private String name;

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
        if (namePresent) {
            if (person.getName().isBlank()) {
                throw new Exception("Name cannot be blank");
            } else if (person.getName().trim().length() < 3) {
                throw new Exception("Name is too short");
            }
        }
    }

}
