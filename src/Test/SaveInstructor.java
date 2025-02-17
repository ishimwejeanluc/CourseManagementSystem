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
public class SaveInstructor {
	
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
    public void saveInstructor() {
        try (Session session = HibernateUtil.getSession().openSession()) {
            Transaction transaction = session.beginTransaction();

           
            String result = instructorDao.saveInstructor(instructor);
            transaction.commit(); 
            
            assertEquals("saved", result);
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    }

	}


