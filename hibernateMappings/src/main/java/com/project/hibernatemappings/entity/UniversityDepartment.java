package com.project.hibernatemappings.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "university_department")
public class UniversityDepartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "department_name")
    private String departmentName;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "university_id")
    private University university;

    @OneToMany(cascade =CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "university_department_id")
    private List<DepartmentCourse> departmentCourses;

    public UniversityDepartment(String departmentName) {
        this.departmentName = departmentName;
    }
    public UniversityDepartment(){

    }
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
                name = "university_department_student",
                joinColumns =@JoinColumn(name ="university_department_id"),
                inverseJoinColumns = @JoinColumn(name = "student_id"))
    public List<Student>students;

    public int getId() {
        return id;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public List<DepartmentCourse> getDepartmentCourses() {
        return departmentCourses;
    }

    public void setDepartmentCourses(List<DepartmentCourse> departmentCourses) {
        this.departmentCourses = departmentCourses;
    }

    //convenience method.
    public void addDepartmentCourse(DepartmentCourse departmentCourse){
        if(departmentCourses==null)
            departmentCourses=new ArrayList<>();
        departmentCourses.add(departmentCourse);

    }

    //convenience method
    public void addStudent(Student student){
        if(students==null)
            students=new ArrayList<>();
        students.add(student);
    }

    @Override
    public String toString() {
        return "UniversityDepartment{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
