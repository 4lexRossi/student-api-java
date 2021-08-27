package com.alos.fpt.apifaculpratodos.repository;

import java.util.List;

import com.alos.fpt.apifaculpratodos.entities.Student;

public interface StudentRepositoryCustom {
    List<Student> findByEmail(String email);
}
