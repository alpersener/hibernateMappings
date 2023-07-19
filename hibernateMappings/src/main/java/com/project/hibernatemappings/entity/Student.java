package com.project.hibernatemappings.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;


    @Column(name = "first_name")
    public String firstName;

    @Column(name = "last_name")
    public String lastName;

    @Column(name = "email")
    public String email;


    @ManyToMany(cascade ={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
            ,fetch = FetchType.LAZY)
    @JoinTable(
            name = "university_department_student",
            joinColumns =@JoinColumn(name = "student_id"),
            inverseJoinColumns =@JoinColumn(name = "university_department_id"))
    private List<UniversityDepartment> departments;

    public List<UniversityDepartment> getDepartmentList() {
        return departments;
    }

    public void setDepartmentList(List<UniversityDepartment> departmentList) {
        this.departments = departments;
    }

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    //convenience method
    public void addUniversityDepartment(UniversityDepartment universityDepartment){
        if(departments==null)
            departments=new ArrayList<>();
        departments.add(universityDepartment);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
