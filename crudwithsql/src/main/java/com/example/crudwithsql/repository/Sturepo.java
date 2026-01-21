package com.example.crudwithsql.repository;

import com.example.crudwithsql.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Sturepo extends JpaRepository<Student,Long> {
}
