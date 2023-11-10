package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{

    //define field for entity manager
    private EntityManager entityManager;

    //inject entity manager using constructor injection
    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager=entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);

    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {

        // retrieve the instructor.
        Instructor tempInstructor = entityManager.find(Instructor.class, theId);

        // get the course.
        List<Course> courses = tempInstructor.getCourses();

        // break the association of all courses for the instructor.
        for (Course tempCourse : courses) {
            tempCourse.setInstructor(null);
        }

        // delete the instructor.
        entityManager.remove(tempInstructor);

    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Transactional
    @Override
    public void deleteInstructorDetailById(int theId) {

        // retrieve the instructor
        InstructorDetail tempInstructorDetIL = entityManager.find(InstructorDetail.class, theId);

        // remove the associated object reference.
        //break bi-directional link.

        tempInstructorDetIL.getInstructor().setInstructorDetail(null);

        // delete the instructor
        entityManager.remove(tempInstructorDetIL);

    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {

        // create a query.
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id = :data", Course.class);

        query.setParameter("data", theId);

        // execute the query.
        List<Course> courses = query.getResultList();

        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {

        // create a query.
        TypedQuery<Instructor> query = entityManager.createQuery(
                                      "select i from Instructor i "
                                              + "JOIN FETCH i.courses "
                                              + "JOIN FETCH i.instructorDetail "
                                              + "where i.id = :data", Instructor.class);

        query.setParameter("data", theId);

        // execute the query.
        Instructor instructor = query.getSingleResult();

        return instructor;
    }
    @Transactional
    @Override
    public void update(Instructor tempInstructor) {

        entityManager.merge(tempInstructor);
    }

    @Transactional
    @Override
    public void update(Course tempCourse) {

        entityManager.merge(tempCourse);
    }

    @Override
    public Course findCourseById(int theId) {

        return entityManager.find(Course.class, theId);
    }

    @Transactional
    @Override
    public void deleteCourseById(int theId) {

        // retrieve the courses.
        Course course = entityManager.find(Course.class, theId);

        // delete the course.
        entityManager.remove(course);



    }
    @Transactional
    @Override
    public void save(Course theCourse) {
        entityManager.persist(theCourse);

    }

    @Override
    public Course findCourseAndReviewsById(int theId) {

        // create a query.
        TypedQuery<Course> query = entityManager.createQuery(
                  "select c from Course c "
                + "JOIN FETCH c.reviews "
                + "where c.id = :data", Course.class);

        query.setParameter("data", theId);

        // execute the query.
        Course course = query.getSingleResult();

        return course;
    }

    @Override
    public Course findCourseAndsTUDENTSByCourseId(int theId) {

        // create a query.
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                        + "JOIN FETCH c.students "
                        + "where c.id = :data", Course.class);

        query.setParameter("data", theId);

        // execute the query.
        Course course = query.getSingleResult();
        return course;
    }

    @Override
    public Student findStudentAndCoursesById(int theId) {

        // create a query.
        TypedQuery<Student> query = entityManager.createQuery(
                "select s from Student s "
                        + "JOIN FETCH s.courses "
                        + "where s.id = :data", Student.class);

        query.setParameter("data", theId);

        // execute the query.
        Student student = query.getSingleResult();

        return student;
    }

    @Transactional
    @Override
    public void update(Student tempStudent) {
        entityManager.merge(tempStudent);
    }

    @Transactional
    @Override
    public void deleteStudentById(int theId) {

        // retrieve the student.
        Student tempStudent = entityManager.find(Student.class, theId);

        // remove the student.
        entityManager.remove(tempStudent);
    }
}








































