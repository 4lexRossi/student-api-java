package com.alos.fpt.apifaculpratodos.repository;

import java.util.ArrayList;
import java.util.List;

import com.alos.fpt.apifaculpratodos.entities.Student;

import org.springframework.beans.factory.annotation.Autowired;

public class StudentRepositoryImpl implements StudentRepositoryCustom {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<Student> findByEmail(String studentEmail) {
        List<Student> studentMail = new ArrayList<>();
        List<Student> studentMailList = studentRepository.findAll();
        for(Student student : studentMailList) {
            if(student.getEmail().equals(studentEmail)) {
                studentMail.add(student);
            }
        }
        return studentMail;
    }

}
