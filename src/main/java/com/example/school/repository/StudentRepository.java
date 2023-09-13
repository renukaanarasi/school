package com.example.school.repository;

import java.util.*;

import com.example.school.model.Student;

public interface StudentRepository {

    ArrayList<Student> getStudents();

    Student getStudentById(int studentId);

    Student addStudent(Student school);

    Student updateStudent(int studentId, Student school);

    void deleteStudent(int studentId);
}
