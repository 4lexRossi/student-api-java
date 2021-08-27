package com.alos.fpt.apifaculpratodos.services;

import java.util.Optional;

import com.alos.fpt.apifaculpratodos.entities.Student;
import com.alos.fpt.apifaculpratodos.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public boolean checkStudentAlreadyExist(String id) {
        boolean isRegistered = false;
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()) {
            isRegistered = true;
            return isRegistered;
        }
        return isRegistered;
    }

    public Student getStudentById(String id) {
        return studentRepository.findById(id).get();
    }

    public String buildId(String email, Integer age) {
        if(email.length() != 0 || age != null) {
            return email + age;
        }
        return "Missing email or age values";
    }

}
