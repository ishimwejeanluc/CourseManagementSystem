package Test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.BeforeClass;
import org.junit.Test;

import Dao.InstructorDao;
import modal.Instructor;
import util.HibernateUtil;


/*To maintain the relationship and the testcases to run successfully follower this order:
1.Instructortestcase(SaveInstructor,UpdateInstructor,DeleteInstructor and retriveInstructor)
2.CourseTestCases(SaveCourse, UpdateCourse,deleteCourse, retrieveCourse, retrieveCourseByEmial)*/
public class RetrieveInstructor {
	
	static InstructorDao instructorDao;
    Instructor instructor;
    
    @BeforeClass
    public static void initialization() {
        
        instructorDao = new InstructorDao();
    }

	@Test
	public void getAllInstructors() {
	    try (Session session = HibernateUtil.getSession().openSession()) {
	        Transaction transaction = session.beginTransaction(); 
	        List<Instructor> instructors = instructorDao.getAllInstructors();
	        List<String> actualNames = instructors.stream().map(Instructor::getFirstName).toList();

	        List<String> expectedNames = Arrays.asList("Carlos", "Jean Luc");
            
	        assertEquals(expectedNames.size(), actualNames.size());
	        assertTrue( expectedNames.containsAll(actualNames));

	        transaction.commit(); 
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

}
}