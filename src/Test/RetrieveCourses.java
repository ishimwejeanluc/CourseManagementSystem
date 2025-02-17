package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
public class RetrieveCourses {
	
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
    public void retrieveCourses() {
        try (Session session = HibernateUtil.getSession().openSession()) {
            Transaction transaction = session.beginTransaction();

           
            List<String> courseNames = courseDao.getAllCourses();

            List<String> expectedCourses = Arrays.asList("Advanced Mobile Development", "mobile");

            transaction.rollback(); 

            assertEquals(expectedCourses.size(), courseNames.size());
            assertTrue(courseNames.containsAll(expectedCourses));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
