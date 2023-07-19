package com.project.hibernatemappings.dao;

import com.project.hibernatemappings.entity.Rector;
import com.project.hibernatemappings.entity.Student;
import com.project.hibernatemappings.entity.University;
import com.project.hibernatemappings.entity.UniversityDepartment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class MappingDAOImpl implements MappingDAO {

    private EntityManager entityManager;


    @Autowired //cons injection
    public MappingDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(University university) {
        entityManager.persist(university);

    }

    @Override
    @Transactional
    public void update(University university) {
        entityManager.merge(university);

    }

    @Override
    @Transactional
    public void deleteUniversityById(int id) {
        University university=entityManager.find(University.class,id);
        entityManager.remove(university);

    }

    @Override
    @Transactional
    public void deleteUniversityByIdBreakWithDepartment(int id) {
        University university=entityManager.find(University.class,id);
        List<UniversityDepartment> departments=university.getDepartments();
        for (UniversityDepartment universityDepartment:departments) {
            universityDepartment.setUniversity(null);
        }
        entityManager.remove(university);

    }

    @Override
    public University findUniversityByIdJoinFetch(int id) {
        TypedQuery<University> query=entityManager.createQuery(
                "select u from University u " +
                        "join fetch u.departments " +
                        "join fetch u.rector " +
                        "where u.id= :data", University.class);
        query.setParameter("data",id);
        University university=query.getSingleResult();
        return university;
    }

    @Override
    public University findUniversityById(int id) {
        return entityManager.find(University.class,id);
    }




    @Override
    public Rector findRectorById(int id) {
        return entityManager.find(Rector.class,id);
    }

    @Override
    @Transactional
    public void deleteRectorById(int id) {
        Rector rector=entityManager.find(Rector.class,id);
        rector.getUniversity().setRector(null);
        entityManager.remove(rector);

    }

    @Override
    @Transactional
    public void save(UniversityDepartment universityDepartment) {
        entityManager.persist(universityDepartment);

    }

    @Override
    public List<UniversityDepartment> findDepartmentsByUniversityId(int id) {
        TypedQuery<UniversityDepartment> query= entityManager.createQuery(
                "from UniversityDepartment where university.id=:data",UniversityDepartment.class);
        query.setParameter("data",id);
        List<UniversityDepartment> departments=query.getResultList();
        return departments;

    }

    @Override
    @Transactional
    public void update(UniversityDepartment universityDepartment) {
        entityManager.merge(universityDepartment);

    }

    @Override
    public UniversityDepartment findDepartmentById(int id) {
         return entityManager.find(UniversityDepartment.class,id);
    }

    @Override
    public UniversityDepartment findDepartmentAndDepartmentCoursesByDepartmentId(int id) {
        TypedQuery<UniversityDepartment>query= entityManager.createQuery(
                "select ud from UniversityDepartment ud " +
                        "join fetch ud.departmentCourses " +
                        "where ud.id=:data", UniversityDepartment.class);
        query.setParameter("data",id);
        UniversityDepartment universityDepartment=query.getSingleResult();
        return universityDepartment;

    }

    @Override
    public UniversityDepartment findDepartmentAndStudentsByDepartmentId(int id) {
        TypedQuery<UniversityDepartment>query= entityManager.createQuery(
                "select ud from UniversityDepartment ud " +
                        "join fetch ud.students " +
                        "where ud.id=:data", UniversityDepartment.class);
        query.setParameter("data",id);
        UniversityDepartment universityDepartment=query.getSingleResult();
        return universityDepartment;
    }



    @Override
    @Transactional
    public void deleteDepartmentById(int id) {
        UniversityDepartment universityDepartment=entityManager.find(UniversityDepartment.class,id);
        entityManager.remove(universityDepartment);

    }

    @Override
    public Student findStudentAndDepartmentByStudentId(int id) {
        TypedQuery<Student> query=entityManager.createQuery(
                "select s from Student s " +
                        "join fetch s.departments " +
                        "where s.id=:data",Student.class);
        query.setParameter("data",id);
        Student student=query.getSingleResult();
        return student;

    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);

    }

    @Override
    @Transactional
    public void deleteStudentById(int id) {
        Student student=entityManager.find(Student.class,id);
        entityManager.remove(student);

    }
}
