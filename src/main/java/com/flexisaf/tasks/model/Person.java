package com.flexisaf.tasks.model;

import com.flexisaf.tasks.exception.FailedValidationException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.UUID;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "persons")
public class Person {

    @Id
    @Column(updatable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Integer age = null;

    @Transient
    private static final int MINIMUM_AGE = 17;

    @Column(name = "full_name", length = 100)
    private String name;

    @Column(name = "email_address", length = 55, unique = true)
    private String email;

    @Column()
    @ColumnDefault("false")
    private Boolean enabled;

    @Column(length = 35)
    @Enumerated(EnumType.STRING)
    private Departments department = Departments.CUSTOMER_SERVICE;

    public static void validate(Person person) throws FailedValidationException {
        validate(person, false);
    }

    public static void validate(Person person, boolean isPost) throws FailedValidationException {
        final boolean agePresent = person.getAge() != null;
        final boolean namePresent = person.getName() != null;
        final boolean allRequiredPresent = agePresent && namePresent;
        if (isPost && !allRequiredPresent ) {
            if (!agePresent && !namePresent) {
                throw new FailedValidationException("Name and Age are required fields");
            } else if (!agePresent) {
                throw new FailedValidationException("Age is a required field");
            } else if (!namePresent) {
                throw new FailedValidationException("Name is a required field");
            }
        }
        if (!agePresent && !namePresent) {
            throw new FailedValidationException("At least one of name or age must be provided");
        }
        if (agePresent && person.age < MINIMUM_AGE) {
            throw new FailedValidationException("Minimum age is " + MINIMUM_AGE);
        }
        if (namePresent) {
            if (person.getName().isBlank()) {
                throw new FailedValidationException("Name cannot be blank");
            } else if (person.getName().trim().length() < 3) {
                throw new FailedValidationException("Name is too short");
            }
        }
    }

}

enum Departments {
    IT, ADMIN, HR, ACCOUNTING, CUSTOMER_SERVICE
}
