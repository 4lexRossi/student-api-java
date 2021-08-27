package com.alos.fpt.apifaculpratodos.controller;

import java.util.List;

import com.alos.fpt.apifaculpratodos.entities.Student;
import com.alos.fpt.apifaculpratodos.repository.StudentRepository;
import com.alos.fpt.apifaculpratodos.response.MessageResponseDTO;
import com.alos.fpt.apifaculpratodos.services.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/facul-pra-todos")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentService studentService;

    @Autowired
    MessageResponseDTO messageResponseDTO;

    @GetMapping("/fetch-students")
    public List<Student> listAllStudents() {
        List<Student> allStudents = studentRepository.findAll();
        return allStudents;
    }

    @GetMapping("/fetch-students/{id}")
    public Student getStudentByIdl(@PathVariable(value = "id")String id) {
        try {
            return studentRepository.findById(id).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/fetch-students/email")
    public List<Student> getStudentByEmail(@RequestParam(value = "email")String studentEmail) {
        return studentRepository.findByEmail(studentEmail);
    }

    @PostMapping("/add-student")
    public ResponseEntity<MessageResponseDTO> addStudent(@RequestBody Student student) {
        String id = studentService.buildId(student.getEmail(), student.getAge());
        if(!studentService.checkStudentAlreadyExist(id)) {
            student.setId(id);
            studentRepository.save(student);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("uniqueId", student.getId());

            messageResponseDTO.setMsg("Registered Successfully!");
            return new ResponseEntity<>(messageResponseDTO, httpHeaders, HttpStatus.CREATED);
        }
        messageResponseDTO.setMsg("user with email: " + student.getEmail() + " already exists");
        return new ResponseEntity<>(messageResponseDTO, HttpStatus.ACCEPTED);
    }


}
