package Test;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Dao.InstructorDao;
import modal.Instructor;
import util.HibernateUtil;

/*To maintain the relationship and the testcases to run successfully follower this order:
1.Instructortestcase(SaveInstructor,UpdateInstructor,DeleteInstructor and retriveInstructor)
2.CourseTestCases(SaveCourse, UpdateCourse,deleteCourse, retrieveCourse, retrieveCourseByEmial)*/
public class UpdateInstructor {
	
	static InstructorDao instructorDao;
    Instructor instructor;

    @BeforeClass
    public static void initialization() {
        
        instructorDao = new InstructorDao();
    }

    @Before
    public void setUp() {
       
    	 instructor = new Instructor();
         instructor.setFirstName("irankunda");
         instructor.setLastName("Carlos");
         instructor.setEmail("lukatoni@gmail.com");
    }

	@Test
    public void updateInstructor() {
        try (Session session = HibernateUtil.getSession().openSession()) {
            Transaction transaction = session.beginTransaction();
            
           
            instructorDao.saveInstructor(instructor);
            
            
            instructor.setFirstName("Jean Luc");
            instructor.setEmail("ljeanluc394@gmail.com");
            String result = instructorDao.updateInstructor(instructor);
            transaction.commit(); // Commit the transaction
            
            assertEquals("updated", result);
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    }

}
