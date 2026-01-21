package com.example.crudwithsql.service;


import com.example.crudwithsql.entity.Student;
import com.example.crudwithsql.repository.Sturepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class Stuservice {
    @Autowired
    private Sturepo repository;
    public Student add(Student student){
        return repository.save(student);

    }
    public List<Student> showall(){
        return repository.findAll();
    }
    public Student findbyid(Long id){
        return repository.findById(id).orElse(null);
    }
    public void deletebyid(Long id){
         repository.deleteById(id);
    }
    public Student updatebyid(Long id,Student newdata){
        Student student=repository.findById(id).orElse(null);
        if(student != null){
            student.setName(newdata.getName());
            student.setEmail(newdata.getEmail());
            return repository.save(student);
        }
        return null;
    }
}
