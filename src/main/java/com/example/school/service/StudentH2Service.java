
package com.example.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

import com.example.school.model.Student;
import com.example.school.repository.StudentRepository;
import com.example.school.model.StudentRowMapper;

@Service
public class StudentH2Service implements StudentRepository {

    @Autowired
    private JdbcTemplate db;

    @Override
    public ArrayList<Student> getStudents() {
         return (ArrayList<Student>) db.query("select * from STUDENT", new StudentRowMapper());
    }

    @Override
    public Student getStudentById(int studentId) {
        try {
            return db.queryForObject("select * from STUDENT where studentId = ?", new StudentRowMapper(), studentId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

   @Override
    public Student addStudent(Student school) {
        db.update("insert into STUDENT(studentName, gender, standard) values (?,?,?)", school.getStudentName(),
                school.getGender(), school.getStandard());
        return db.queryForObject("select * from STUDENT where studentName = ? and gender = ?", new StudentRowMapper(),
                school.getStudentName(), school.getGender());
    }

    @Override
    public Student updateStudent(int studentId, Student school) {
        if (school.getStudentName() != null) {
            db.update("update STUDENT set studentName = ? where studentId =?", school.getStudentName(), studentId);
        }
        if (school.getGender() != null) {
            db.update("update STUDENT set gender = ? where studentId =?", school.getGender(), studentId);
        }
        if (school.getStandard() != 0) {
            db.update("update STUDENT set standard = ? where studentId =?", school.getStandard(), studentId);
        }
        return getStudentById(studentId);
    }

    

    @Override
    public void deleteStudent(int studentId) {
        db.update("delete from STUDENT where studentId = ?", studentId);
    }

}
