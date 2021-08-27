package com.alos.fpt.apifaculpratodos;

import com.alos.fpt.apifaculpratodos.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiFaculPraTodosApplication {

  @Autowired
  StudentRepository studentRepository;

  public static void main(String[] args) {
    SpringApplication.run(ApiFaculPraTodosApplication.class, args);
  }

}
