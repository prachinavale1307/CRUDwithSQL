package com.example.crudwithsql.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="c1")
public class Student {
    @Id

    private Long id;

    @NotBlank(message="name cannot be empty")
    private String name;

    @Email(message="invalid email format")
    @NotBlank(message = "email cannot be empty")
    private String email;
}
