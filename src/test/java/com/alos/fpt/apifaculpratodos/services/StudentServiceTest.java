package com.alos.fpt.apifaculpratodos.services;

import com.alos.fpt.apifaculpratodos.entities.Student;
import com.alos.fpt.apifaculpratodos.repository.StudentRepository;

import static com.alos.fpt.apifaculpratodos.utils.StudentUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    public void checkBuildIdLogic() {
        Student student = mockBuildStudent();
        String id = studentService.buildId(student.getEmail(), student.getAge());

        assertEquals(student.getId(), id);
    }

    @Test
    public void checkIfStudentAlreadyExist() {
        Student student = mockBuildStudent();
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        boolean studentIsRegistered = studentService.checkStudentAlreadyExist(student.getId());
        assertFalse(studentIsRegistered);
    }

}
