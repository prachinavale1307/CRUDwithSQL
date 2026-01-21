package com.example.crudwithsql.controller;

import com.example.crudwithsql.entity.Student;
import com.example.crudwithsql.service.Stuservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school")
public class Stucontroller {

    @Autowired
    private Stuservice service;
    @PostMapping
    public ResponseEntity<Student> addstudent(@RequestBody Student student){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.add(student));


    }
    @GetMapping
    public ResponseEntity<List<Student>> showallstudents(){
        return ResponseEntity.ok(service.showall());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> getbyid(@PathVariable Long id){
        Student stu= service.findbyid(id);
        if(stu==null)
        {
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(stu);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> dropbyid(@PathVariable Long id){
        service.deletebyid(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> changbyid(@PathVariable Long id,@RequestBody Student student){
        Student stu= service.updatebyid(id,student);
        if(stu==null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(stu);
    }


}
