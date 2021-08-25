package com.alos.fpt.apifaculpratodos.repository;

import com.alos.fpt.apifaculpratodos.entities.Student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {


}
