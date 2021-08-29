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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/facul-pra-todos")
@Api(value="Api Rest Students")
@CrossOrigin(origins="*")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentService studentService;

    @Autowired
    MessageResponseDTO messageResponseDTO;

    @GetMapping("/fetch-students")
    @ApiOperation(value="Return All Students")
    public List<Student> listAllStudents() {
        List<Student> allStudents = studentRepository.findAll();
        return allStudents;
    }

    @GetMapping("/fetch-students/{id}")
    @ApiOperation(value="Fetch a Student by ID")
    public Student fetchStudentById(@PathVariable(value = "id")String id) {
        try {
            return studentService.getStudentById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/fetch-students/email")
    @ApiOperation(value="Fetch a Student by email")
    public List<Student> fetchStudentByEmail(@RequestParam(value = "email")String studentEmail) {
        return studentRepository.findByEmail(studentEmail);
    }

    @PostMapping("/add-student")
    @ApiOperation(value="Register a Student")
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

    @PutMapping("/add-student/{id}")
    @ApiOperation(value="Update a registered Student")
    public ResponseEntity<Student> updateStudent(@PathVariable(value = "id")String id, @RequestBody Student student) {

        Student existingStudent = studentService.getStudentById(id);

        existingStudent.getId();
        existingStudent.getEmail();
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setAge(student.getAge());
        existingStudent.setCollegeName(student.getCollegeName());
        existingStudent.setCourseArea(student.getCourseArea());
        existingStudent.setCourseName(student.getCourseName());
        existingStudent.setMonthlyValue(student.getMonthlyValue());
        studentRepository.save(existingStudent);

        return new ResponseEntity<>(existingStudent, HttpStatus.OK);
    }

    @DeleteMapping("/delete-student")
    @ApiOperation(value="Delete a Student sending his ID in the body")
    public ResponseEntity<String> deleteStudentById(@RequestBody Student student) {
        try {
            Student studentDeleted = studentService.getStudentById(student.getId());
            studentRepository.delete(studentDeleted);
            return new ResponseEntity<>("Student deleted from our database", HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
