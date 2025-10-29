package com.flexisaf.tasks.dto;

import com.flexisaf.tasks.model.Departments;
import jakarta.validation.constraints.*;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class CreatePersonDto {

    @Min(17)
    @Max(65)
    @NotNull
    private Integer age = null;

    @NotBlank
    @Length(min = 8, max = 65)
    private String name;

    @Email
    @NotBlank
    private String email;

    @Pattern(regexp = "(\\+?234|0)[789][01]\\d{8}", message = "Invalid Nigerian Phone Number entered")
    @NotBlank
    private String phoneNumber;

    private Departments department = Departments.CUSTOMER_SERVICE;
}
