package com.alos.fpt.apifaculpratodos.utils;

import com.alos.fpt.apifaculpratodos.entities.Student;

public class StudentUtils {
    public static Student mockBuildLibrary() {
        Student studentMock = new Student();

        studentMock.setFirstName("Alex");
        studentMock.setLastName("Rossi");
        studentMock.setEmail("alex@email.com");
        studentMock.setAge(25);
        studentMock.setCollegeName("DIO");
        studentMock.setCourseArea("Spring-boot");
        studentMock.setMonthlyValue(11700);
        studentMock.setId(studentMock.getEmail() + studentMock.getAge());

        return studentMock;
    }

    public static Student mockUpdateLibrary(String id) {
        Student studentUpdateMock = new Student();

        studentUpdateMock.setFirstName("AlexUpdate");
        studentUpdateMock.setLastName("RossiUpdate");
        studentUpdateMock.setAge(30);
        studentUpdateMock.setCollegeName("DioUpdate");
        studentUpdateMock.setCourseArea("Spring-bootUpdate");
        studentUpdateMock.setMonthlyValue(10000);
        studentUpdateMock.setEmail(mockBuildLibrary().getEmail());
        studentUpdateMock.setId(id);

        return studentUpdateMock;
    }
}
