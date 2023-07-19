package com.project.hibernatemappings.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "university")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;


    @Column(name ="university_name")
    private String universityName;

    @Column(name ="location" )
    private String location;

    @JoinColumn(name ="rector_id")
    @OneToOne(cascade = CascadeType.ALL)
    private Rector rector;


    @OneToMany(mappedBy = "university"
    ,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
    ,fetch = FetchType.LAZY)
    private List<UniversityDepartment> departments;


    public University(){

    }
    public University(String universityName, String location) {
        this.universityName = universityName;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Rector getRector() {
        return rector;
    }

    public void setRector(Rector rector) {
        this.rector = rector;
    }

    public List<UniversityDepartment> getDepartments() {
        return departments;
    }

    public void setDepartments(List<UniversityDepartment> departments) {
        this.departments = departments;
    }

    public void add(UniversityDepartment universityDepartment){
        if(departments==null)
            departments=new ArrayList<>();
        departments.add(universityDepartment);
        universityDepartment.setUniversity(this);
    }

    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", universityName='" + universityName + '\'' +
                ", location='" + location + '\'' +
                ", rector=" + rector +
                '}';
    }
}
