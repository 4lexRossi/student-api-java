package com.alos.fpt.apifaculpratodos.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RegisterStudents")
public class Student {

  @Id
  @Column(name = "id")
  private String id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email", unique = true)
  private String email;

  @Column(name = "age")
  private int age;

  @Column(name = "college_name")
  private String collegeName;

  @Column(name = "course_name")
  private String courseName;

  @Column(name = "course_area")
  private String courseArea;

  @Column(name = "monthly_value")
  private int monthlyValue;

  public String getId() {
    return id;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Student other = (Student) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCollegeName() {
    return collegeName;
  }

  public void setCollegeName(String collegeName) {
    this.collegeName = collegeName;
  }

  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public String getCourseArea() {
    return courseArea;
  }

  public void setCourseArea(String courseArea) {
    this.courseArea = courseArea;
  }

  public int getMonthlyValue() {
    return monthlyValue;
  }

  public void setMonthlyValue(int monthlyValue) {
    this.monthlyValue = monthlyValue;
  }

  @Override
  public String toString() {
    return "Student [age=" + age + ", collegeName=" + collegeName + ", courseArea=" + courseArea + ", courseName="
        + courseName + ", email=" + email + ", firstName=" + firstName + ", id=" + id + ", lastName=" + lastName
        + ", monthlyValue=" + monthlyValue + "]";
  }


}
