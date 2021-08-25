package com.alos.fpt.apifaculpratodos.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.alos.fpt.apifaculpratodos.dto.request.StudentDTO;
import com.alos.fpt.apifaculpratodos.entities.Student;
import com.alos.fpt.apifaculpratodos.dto.mapper.StudentMapper;
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

    public Student getById(String id) {
        return studentRepository.findById(id).get();
    }

}
