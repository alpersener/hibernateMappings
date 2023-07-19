package com.project.hibernatemappings;

import com.project.hibernatemappings.dao.MappingDAO;
import com.project.hibernatemappings.entity.*;
import org.hibernate.Hibernate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class HibernateMappingsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HibernateMappingsApplication.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(MappingDAO mappingDAO){

        return runner->{
            //creeateUniversity(mappingDAO);
           //updateUniversity(mappingDAO);
           // deleteUniversityById(mappingDAO);
           // createUniversityWithDepartments(mappingDAO);
            //deleteUniversityByIdBreakWithDepartment(mappingDAO);
            //createUniversityDepartmentAndCourse(mappingDAO);
            //findUniversityByIdJoinFetch(mappingDAO);
            //findRectorById(mappingDAO);
            //deleteRectorById(mappingDAO);
            //deleteDepartmentWithId(mappingDAO);
            //updateDepartment(mappingDAO);
            //findDepartmentWithId(mappingDAO);
            //findDepartmentAndDepartmentCoursesByDepartmentId(mappingDAO);
            //addMoreDepartmentForStudent(mappingDAO);
            //createDepartmentAndStudents(mappingDAO);














        };

    }

    private void createDepartmentAndStudents(MappingDAO mappingDAO) {
        UniversityDepartment universityDepartment=new UniversityDepartment("Elektrik-Eloktrink Muh");
        Student student1=new Student("ahmet","soyisim","deneme@gmail");
        Student student2=new Student("mehmet","soyisim","deneme2@gmail");
        universityDepartment.addStudent(student1);
        universityDepartment.addStudent(student2);
        System.out.println("Department saved"+universityDepartment);
        System.out.println("Students:"+universityDepartment.getStudents());
        mappingDAO.save(universityDepartment);


    }

    private void addMoreDepartmentForStudent(MappingDAO mappingDAO) {
        int id=1;
        Student student=mappingDAO.findStudentAndDepartmentByStudentId(id);
        UniversityDepartment department=new UniversityDepartment("yazilim muh");
        student.addUniversityDepartment(department);
        System.out.println(student.getDepartmentList());
        mappingDAO.update(student);

    }

    private void findDepartmentAndDepartmentCoursesByDepartmentId(MappingDAO mappingDAO) {
        int id=6;
        UniversityDepartment universityDepartment=mappingDAO.findDepartmentAndDepartmentCoursesByDepartmentId(id);
        System.out.println(universityDepartment);

    }

    private void findDepartmentWithId(MappingDAO mappingDAO) {
        int id=5;
        System.out.println("Department id:"+id);
        UniversityDepartment universityDepartment=mappingDAO.findDepartmentById(id);
        System.out.println(universityDepartment);
    }

    private void updateDepartment(MappingDAO mappingDAO) {
        int id=5;
        System.out.println("Find Department id:"+id);
        UniversityDepartment universityDepartment=mappingDAO.findDepartmentById(id);
        System.out.println("Updating department"+id);
        universityDepartment.setDepartmentName("Insaat muh");
        mappingDAO.update(universityDepartment);

    }

    private void deleteRectorById(MappingDAO mappingDAO) {
        int id=3;
        System.out.println("Deleting Rector"+id);
        mappingDAO.deleteRectorById(id);
    }

    private void findRectorById(MappingDAO mappingDAO) {
        int id=3;
        Rector rector=mappingDAO.findRectorById(id);
        System.out.println("rector"+rector);
        System.out.println("associated Uni:"+rector.getUniversity());
    }


    private void deleteDepartmentWithId(MappingDAO mappingDAO) {
        int id=3;
        System.out.println("delete department:"+id);
        mappingDAO.deleteDepartmentById(id);
    }

    private void findUniversityByIdJoinFetch(MappingDAO mappingDAO){
        int id=6;
        System.out.println("Find university:"+id);
        University university=mappingDAO.findUniversityByIdJoinFetch(id);
        System.out.println("Uni:"+university);
        System.out.println("Departments:"+university.getDepartments());
    }

    private void createUniversityWithDepartments(MappingDAO mappingDAO) {
        University university=new University("itu","Istanbul");
        Rector rector=new Rector("ismail","koyuncu");
        university.setRector(rector);
        UniversityDepartment department=new UniversityDepartment("Insaat Muhendisligi");
        UniversityDepartment department1=new UniversityDepartment("Elektrik-Elektronik Muhendisligi");
        university.add(department);
        university.add(department1);
        System.out.println("Saved uni:"+university);
        System.out.println("Departments:"+university.getDepartments());
        mappingDAO.save(university);

    }

    private void createUniversityDepartmentAndCourse(MappingDAO mappingDAO) {
        UniversityDepartment universityDepartment=new UniversityDepartment("Bilgisayar Mühendisligi");
        universityDepartment.addDepartmentCourse(new DepartmentCourse("Yapay Zeka",5));
        universityDepartment.addDepartmentCourse(new DepartmentCourse("Mikroislemciler",4));
        universityDepartment.addDepartmentCourse(new DepartmentCourse("NTP",10));
        System.out.println("Saved department:"+universityDepartment);
        System.out.println("Saved courses"+universityDepartment.getDepartmentCourses());
        mappingDAO.save(universityDepartment);

    }

    private void deleteUniversityByIdBreakWithDepartment(MappingDAO mappingDAO) {
        int id=5;
        System.out.println("Deleting university:"+id);
        mappingDAO.deleteUniversityByIdBreakWithDepartment(id);
    }

    private void deleteUniversityById(MappingDAO mappingDAO){
        int id=2;
        System.out.println("Deleting university by id:"+id);
        mappingDAO.deleteUniversityById(id);
    }


    private void updateUniversity(MappingDAO mappingDAO) {
        int id=1;
        System.out.println("Finding university:"+id);
        University university=mappingDAO.findUniversityById(id);
        System.out.println("Update university id:"+id);
        university.setUniversityName("CÜ");
        university.setLocation("Adana");
        mappingDAO.update(university);

    }

    private void createUniversity(MappingDAO mappingDAO) {
        University university=new University("ödtü","ANKARA");
        Rector rector=new Rector("rektor","soyad");
        university.setRector(rector);
        System.out.println("Saving university:"+university);
        mappingDAO.save(university);


    }



}
