package com.flexisaf.tasks.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.UUID;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "persons")
public class Person {

    @Id
    @Column(updatable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Integer age = null;

    @Column(name = "full_name", length = 100)
    private String name;

    @Column(name = "email_address", length = 55, unique = true)
    private String email;

    @Column()
    @ColumnDefault("true")
    private Boolean enabled;

    private String phoneNumber;

    @Column(length = 35)
    @Enumerated(EnumType.STRING)
    private Departments department = Departments.CUSTOMER_SERVICE;

}
