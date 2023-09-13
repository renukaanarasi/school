package com.example.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import com.example.school.model.Student;
import com.example.school.service.StudentH2Service;

@RestController
public class StudentController {

    @Autowired
    private StudentH2Service schoolService;

    @GetMapping("/students")
    public ArrayList<Student> getStudents() {
        return schoolService.getStudents();
    }

    @PostMapping("/students/bulk")
    public Student addStudent(@RequestBody Student school){
        return schoolservice.addStudent(school);
    }

    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable("studentId") int studentId) {
        return schoolService.getStudentById(studentId);
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student school) {
        return schoolService.addStudent(school);
    }

    @PutMapping("/students/{studentId}")
    public Student updateStudent(@PathVariable("studentId") int studentId, @RequestBody Student school) {
        return schoolService.updateStudent(studentId, school);
    }

    
    @DeleteMapping("/students/{studentId}")
    public void deleteStudent(@PathVariable int studentId) {
        schoolService.deleteStudent(studentId);
    }

}
