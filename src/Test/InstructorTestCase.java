package Test;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Dao.InstructorDao;
import modal.Instructor;
import util.HibernateUtil;

//First run this Instructor Testcase before running the course testcases.



public class InstructorTestCase {

    static InstructorDao instructorDao;
    Instructor instructor;

    @BeforeClass
    public static void initialization() {
        
        instructorDao = new InstructorDao();
    }

    @Before
    public void setUp() {
       
        instructor = new Instructor();
        instructor.setFirstName("Eric");
        instructor.setLastName("Rugwiza");
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

    @Test
    public void deleteInstructor() {
        try (Session session = HibernateUtil.getSession().openSession()) {
            Transaction transaction = session.beginTransaction();
            
            
            instructorDao.saveInstructor(instructor);
            
            
            String result = instructorDao.deleteInstructor(instructor);
            transaction.commit(); 
            
            assertEquals("deleted", result);
        } catch (Exception e) {
            e.printStackTrace();
            
        }
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

    @Test
    public void getAllInstructors() {
        try (Session session = HibernateUtil.getSession().openSession()) {
            Transaction transaction = session.beginTransaction();
            
          
            instructorDao.saveInstructor(instructor);
            
           
            List<Instructor> instructors = instructorDao.getAllInstructors();
            String[] actualNames = new String[instructors.size()];
            for (int i = 0; i < instructors.size(); i++) {
                actualNames[i] = instructors.get(i).getFirstName();
            }

            String[] expectedNames = {"Eric"};
            assertArrayEquals(expectedNames, actualNames);
            transaction.commit(); 
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    }
}
