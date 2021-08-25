package com.alos.fpt.apifaculpratodos.controller;

import java.util.List;

import com.alos.fpt.apifaculpratodos.entities.Student;
import com.alos.fpt.apifaculpratodos.repository.StudentRepository;
import com.alos.fpt.apifaculpratodos.services.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/student")
@AllArgsConstructor
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    StudentService studentService;

    @GetMapping("/fetch-students")
    public List<Student> listAllStudents() {
        List<Student> allStudents = studentRepository.findAll();
        return allStudents;
    }
}
