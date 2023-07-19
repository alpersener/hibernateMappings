package com.project.hibernatemappings.dao;

import com.project.hibernatemappings.entity.Rector;
import com.project.hibernatemappings.entity.Student;
import com.project.hibernatemappings.entity.University;
import com.project.hibernatemappings.entity.UniversityDepartment;

import java.util.List;

public interface MappingDAO {


   //University operations
    void save(University university);

    void update(University university);

    void deleteUniversityById(int id);

    void deleteUniversityByIdBreakWithDepartment(int id);

    University findUniversityByIdJoinFetch(int id);

    University findUniversityById(int id);




    //Rector Operations
    Rector findRectorById(int id);

    void deleteRectorById(int id);


    //UniversityDepartment operations
    void save(UniversityDepartment universityDepartment);

    List<UniversityDepartment> findDepartmentsByUniversityId(int id);

    void update(UniversityDepartment universityDepartment);

    UniversityDepartment findDepartmentById(int id);

    UniversityDepartment findDepartmentAndDepartmentCoursesByDepartmentId(int id);

    UniversityDepartment findDepartmentAndStudentsByDepartmentId(int id);


    void deleteDepartmentById(int id);


    //Student operations
    Student findStudentAndDepartmentByStudentId(int id);

    void update(Student student);

    void deleteStudentById(int id);















}
