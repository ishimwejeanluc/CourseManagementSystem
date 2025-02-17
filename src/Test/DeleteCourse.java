package Test;

import static org.junit.Assert.*;

import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Dao.CourseDao;
import Dao.InstructorDao;
import modal.Course;
import modal.Instructor;
import util.HibernateUtil;

/*To maintain the relationship and the testcases to run successfully follower this order:
 1.Instructortestcase(SaveInstructor,UpdateInstructor,DeleteInstructor and retriveInstructor)
 2.CourseTestCases(SaveCourse, UpdateCourse,deleteCourse, retrieveCourse, retrieveCourseByEmial)*/

public class DeleteCourse {
   
	static CourseDao courseDao;
    static InstructorDao instructorDao;
    Instructor instructor;
    Course course;

    
    

    @BeforeClass
    public static void initialization() {
        courseDao = new CourseDao();
        instructorDao = new InstructorDao();
    }

    @Before
    public void setUp() {
 
    	String instructorUUID = "6d33c2d4-3a80-4597-a3b8-44b0799dba95";
        UUID instId = UUID.fromString(instructorUUID);
        instructor = new Instructor();
        instructor.setInstructorId(instId);

        course = new Course();
        course.setTitle("mobile");
        course.setInstructor(instructor);
    }
	 @Test
    public void deleteCourse() {
        try (Session session = HibernateUtil.getSession().openSession()) {
            Transaction transaction = session.beginTransaction();

            courseDao.saveCourse(course);
            String result = courseDao.deleteCourse(course);
            transaction.rollback(); 

            assertEquals("deleted", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
