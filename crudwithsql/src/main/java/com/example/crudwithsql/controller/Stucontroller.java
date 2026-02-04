package com.example.crudwithsql.controller;

import com.example.crudwithsql.entity.Student;
import com.example.crudwithsql.service.Stuservice;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school")
public class Stucontroller {

    @Autowired
    private Stuservice service;
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Student> addstudent(@Valid @RequestBody Student student){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.add(student));


    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    public ResponseEntity<List<Student>> showallstudents(){
        return ResponseEntity.ok(service.showall());
    }
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Student> getbyid(@PathVariable Long id){
        Student stu= service.findbyid(id);
        if(stu==null)
        {
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(stu);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> dropbyid(@PathVariable Long id){
        service.deletebyid(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")

    @PutMapping("/{id}")
    public ResponseEntity<Student> changbyid(@PathVariable Long id,@Valid @RequestBody Student student){
        Student stu= service.updatebyid(id,student);
        if(stu==null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(stu);
    }


}
