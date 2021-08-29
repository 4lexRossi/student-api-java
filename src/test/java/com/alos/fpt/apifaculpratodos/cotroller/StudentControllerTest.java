package com.alos.fpt.apifaculpratodos.cotroller;

import com.alos.fpt.apifaculpratodos.controller.StudentController;
import com.alos.fpt.apifaculpratodos.entities.Student;
import com.alos.fpt.apifaculpratodos.repository.StudentRepository;
import com.alos.fpt.apifaculpratodos.services.StudentService;
import com.alos.fpt.apifaculpratodos.response.MessageResponseDTO;

import static com.alos.fpt.apifaculpratodos.utils.StudentUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

    String baseUrl = "http://localhost:8080/api/v1/facul-pra-todos";

    @Autowired
    StudentController studentController;

    @Autowired
    MessageResponseDTO messageResponseDTO;

    @MockBean
    StudentRepository studentRepository;

    @MockBean
    StudentService studentService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void should_findAll_successfully() throws Exception {

        List<Student> studentList = Arrays.asList(mockBuildStudent(), mockBuildStudent());

        when(studentRepository.findAll()).thenReturn(studentList);
        this.mockMvc.perform(get(baseUrl + "/fetch-students"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.length()", is(2)))
            .andExpect(jsonPath("$.[0].firstName").value(studentList.get(0).getFirstName()))
            .andExpect(jsonPath("$.[0].lastName").value(studentList.get(0).getLastName()))
            .andExpect(jsonPath("$.[0].collegeName").value(studentList.get(0).getCollegeName()))
            .andExpect(jsonPath("$.[0].courseArea").value(studentList.get(0).getCourseArea()))
            .andExpect(jsonPath("$.[0].monthlyValue").value(studentList.get(0).getMonthlyValue()))
            .andExpect(jsonPath("$.[0].email").value(studentList.get(0).getEmail()))
            .andExpect(jsonPath("$.[0].id").value(studentList.get(0).getId()));
    }

    @Test
    public void should_findByEmail_successfully() throws Exception {

        List<Student> studentList = Arrays.asList(mockBuildStudent(), mockBuildStudent());

        when(studentRepository.findByEmail(any())).thenReturn(studentList);
        this.mockMvc.perform(get(baseUrl + "/fetch-students/email").param("email", "alex@email.com"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.length()", is(2)))
            .andExpect(jsonPath("$.[0].email").value(studentList.get(0).getEmail()))
            .andExpect(jsonPath("$.[0].id").value(studentList.get(0).getId()));
    }

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

    @Test
    public void should_findById_not_successfully() throws Exception {

        Student student = mockBuildStudent();

        when(studentRepository.findById(student.getId()));
        this.mockMvc.perform(get(baseUrl + "/fetch-students/" + student.getId()))
            .andExpect(status().isNotFound());
    }

}
