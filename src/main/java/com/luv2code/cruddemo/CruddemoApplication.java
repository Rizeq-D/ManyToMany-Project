package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {

		return runner -> {

			// createCourseAndStudents(appDAO);

			// findCoursesAndStudents(appDAO);

			// findStudentAndCourses(appDAO);

			// addMoreCoursesForStudent(appDAO);

			// deleteTheCourse(appDAO);

			deleteStudent(appDAO);

		};
	}

	private void deleteStudent(AppDAO appDAO) {

		int theId = 1;

		System.out.println("deleting student " + theId);

		appDAO.deleteStudentById(theId);

		System.out.println("done");
	}

	private void addMoreCoursesForStudent(AppDAO appDAO) {

		int theId = 2;

		// find the student.
		Student tempStudent = appDAO.findStudentAndCoursesById(theId);

		// create new courses.
		Course tempCourse1 = new Course("dancing on the moon");
		Course tempCourse2 = new Course("jumping on one leg for 3 million kilometers");

		// add the courses to the student.
		tempStudent.addCourse(tempCourse1);
		tempStudent.addCourse(tempCourse2);

		System.out.println("saving the student: " + tempStudent);

		System.out.println("associated courses: " + tempStudent.getCourses());

		appDAO.update(tempStudent);

		System.out.println("done");


	}

	private void findStudentAndCourses(AppDAO appDAO) {

		int theId = 2;

		Student tempStudent = appDAO.findStudentAndCoursesById(theId);

		System.out.println("loaded student: " + tempStudent);
		System.out.println("courses: " + tempStudent.getCourses());

		System.out.println("done");
	}

	private void findCoursesAndStudents(AppDAO appDAO) {

		int theId = 10;

		Course tempCourse = appDAO.findCourseAndsTUDENTSByCourseId(theId);

		System.out.println("loading courses: " + tempCourse);
		System.out.println("the students: " + tempCourse.getStudents());

		System.out.println("done");

	}

	private void createCourseAndStudents(AppDAO appDAO) {

		// create a course.
		Course tempcourse = new Course("drinking milk");

		// create the students.
		Student student1 = new Student("Mirko", "Sirko", "mir@sir.com");
		Student student2 = new Student("Timm", "Diana", "ttt@ddd.com");

		// add the students to the courses.
		tempcourse.addStudent(student1);
		tempcourse.addStudent(student2);

		// save the course and the associated students.
		System.out.println("Saving the course: " + tempcourse);
		System.out.println("associate the students: " + tempcourse.getStudents());
		appDAO.save(tempcourse);

		System.out.println("DDDDDOOOOOOONNNNNEEEEEEE");
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {

		int theId = 10;
		System.out.println("deleting the course and review ID: " + theId);

		appDAO.deleteCourseById(theId);

		System.out.println("DN");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {

		// get the courses and reviews.
		int theId = 10;
		Course tempCourse = appDAO.findCourseAndReviewsById(theId);

		// print the course.
		System.out.println(tempCourse);

		// print the reviews.
		System.out.println(tempCourse.getReviews());

		System.out.println("D");

	}


	private void createCourseAndReviews(AppDAO appDAO) {

		// create a course.
		Course tempCourse = new Course("teaching making a green tea");

		// add some reviews.
		tempCourse.addReview(new Review("fucking boring"));
		tempCourse.addReview(new Review("not recommended"));
		tempCourse.addReview(new Review("5 stars!!!!"));

		// save the course.
		System.out.println("saving the courses");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());

		appDAO.save(tempCourse);

		System.out.println("done");

	}

	private void deleteTheCourse(AppDAO appDAO) {

		int theId = 10;

		System.out.println("deleting the course: " + theId);

		appDAO.deleteCourseById(theId);

		System.out.println("done");
	}

	private void updateCourses(AppDAO appDAO) {

		int theId = 10;

		// find the course.
		System.out.println("finding the course id: " + theId);
		Course tempCourse = appDAO.findCourseById(theId);

		// update the course.
		System.out.println("updating the course: " + theId);
		tempCourse.setTitle("reading courses");

		appDAO.update(tempCourse);

		System.out.println("DDDone");
	}

	private void updateInstructor(AppDAO appDAO) {

		int theId = 1;

		System.out.println("finding the instructor: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);

		// update the instructor.
		System.out.println("updating the Instructor: " + theId);
		tempInstructor.setLastName("TESTER");

		appDAO.update(tempInstructor);

		System.out.println("done");
	}
	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {

		int theId = 1;

		// find the instructor.
		System.out.println("finding the instructor: " + theId);
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated courses: " + tempInstructor.getCourses());

		System.out.println("done");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {

		int theId = 1;
		System.out.println("Finding Instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("the tempInstructor: " + tempInstructor);

		// find courses for instructor.
		System.out.println("finding instructor for id: " + theId);
		List<Course> courses = appDAO.findCoursesByInstructorId(theId);

		// associate the objects.
		tempInstructor.setCourses(courses);

		System.out.println("the associated courses: " + tempInstructor.getCourses());
		System.out.println("DoNNNe");

	}

	private void findInstructorWithCourses(AppDAO appDAO) {

		int theId = 1;
		System.out.println("Finding Instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("the tempInstructor: " + tempInstructor);
		System.out.println("the associated courses: " + tempInstructor.getCourses());

		System.out.println("Done");

	}

	private void createInstructorWithCourses(AppDAO appDAO) {

		// create the instructor.
		Instructor tempInstructor = new Instructor("shukri", "alpha", "sh@al.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail (
						"http://www.youtube.com",
						"shu");


		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// create some courses
		Course tempCourse1 = new Course("playing guitar in an airplane upside down");
		Course tempCourse2= new Course("dancing in U8");

		// add courses to the Instructor.
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		// save the instructors.
		//NOTE: this will save the courses too because of the cascadeType.PERSIST.

		System.out.println("saving instructions: " + tempInstructor);
		System.out.println("the course: " + tempInstructor.getCourses());
		appDAO.save(tempInstructor);

		System.out.println("DONE! ! ! ! !");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {

		int theId = 3;

		System.out.println("deleting instruction id: " + theId);

		appDAO.deleteInstructorDetailById(theId);

		System.out.println("DONE!");
	}

	private void findInstructorDetail(AppDAO appDAO) {

		// get the instructor detail object.
		int theId = 2;
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);

		// print the instructor detail.
		System.out.println("TempInstructorDetail: " + tempInstructorDetail);

		// print the associated instructor.
		System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());

		System.out.println("Done!!!!");

	}

	private void deleteInstruction(AppDAO appDAO) {

		int theId = 1;
		System.out.println("deleting Instructor id: " + theId);

		appDAO.deleteInstructorById(theId);

		System.out.println("Done!!!");
	}

	private void findInstruct(AppDAO appDAO) {

		int theId =2;
		System.out.println("finding instructor id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);

		System.out.println("the associated instructorDetail only: " + tempInstructor.getInstructorDetail());


	}

	private void createInstructor(AppDAO appDAO) {

	/*	// create the instructor.
		Instructor tempInstructor = new Instructor("curry", "face", "curry@face.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail (
						"http://www.curry.com/youtube",
						"eating curry");

	 */

		// create the instructor.
		Instructor tempInstructor = new Instructor("dodo", "hoho", "dodo@hoho.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail (
						"http://www.dodo.com/youtube",
						"hao dao");


		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// save the instructor
		//
		//NOTE: this will also save the details of the object
		//because of cascadeType.All
		//
		System.out.println("save instructor: " + tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("Done!!!");
	}

}