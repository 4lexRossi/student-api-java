package com.alos.fpt.apifaculpratodos.cotroller;

import com.alos.fpt.apifaculpratodos.controller.StudentController;
import com.alos.fpt.apifaculpratodos.entities.Student;
import com.alos.fpt.apifaculpratodos.repository.StudentRepository;
import com.alos.fpt.apifaculpratodos.services.StudentService;
import com.alos.fpt.apifaculpratodos.response.MessageResponseDTO;

import static com.alos.fpt.apifaculpratodos.utils.StudentUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

    @Autowired
    StudentController studentController;

    @Autowired
    MessageResponseDTO messageResponseDTO;

    @MockBean
    StudentRepository studentRepository;

    @MockBean
    StudentService studentService;

    @Test
    public void should_addStudent_successfully() {
        Student student = mockBuildStudent();

        when(studentService.buildId(student.getEmail(), student.getAge())).thenReturn(student.getId());
        when(studentService.checkStudentAlreadyExist(student.getId())).thenReturn(false);

        ResponseEntity<?> responseEntity = studentController.addStudent(mockBuildStudent());
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        messageResponseDTO = (MessageResponseDTO) responseEntity.getBody();

        assertEquals("Registered Successfully!", messageResponseDTO.getMsg());
    }

    @Test
    public void should_addStudent_returnStudentAlreadyExists() {
        Student student = mockBuildStudent();

        when(studentService.buildId(student.getEmail(), student.getAge())).thenReturn(student.getId());
        when(studentService.checkStudentAlreadyExist(student.getId())).thenReturn(true);

        ResponseEntity<?> responseEntity = studentController.addStudent(mockBuildStudent());
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());

        messageResponseDTO = (MessageResponseDTO) responseEntity.getBody();

        assertEquals("user with email: " + student.getEmail() + " already exists", messageResponseDTO.getMsg());
    }

}
