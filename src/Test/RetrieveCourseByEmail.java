package Test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
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
public class RetrieveCourseByEmail {
	static CourseDao courseDao;
    static InstructorDao instructorDao;
    Instructor instructor;
    Course course;

    
    

    @BeforeClass
    public static void initialization() {
        courseDao = new CourseDao();
        instructorDao = new InstructorDao();
    }
	@Test
	public void retrieveCourseByEmail() {
	    try (Session session = HibernateUtil.getSession().openSession()) {
	        Transaction transaction = session.beginTransaction();
	        List<Course> courses = instructorDao.retrieveCourseByEmail("ljeanluc394@gmail.com");
	        List<String> actualCourses = courses.stream().map(Course::getTitle).toList();

	        transaction.rollback();

	        List<String> expectedCourses = Arrays.asList("mobile", "Advanced Mobile Development");
            
	        assertEquals(expectedCourses.size(), actualCourses.size());
	        assertTrue( expectedCourses.containsAll(actualCourses));
	                   
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
