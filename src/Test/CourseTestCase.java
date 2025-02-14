package Test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import modal.Course;
import modal.Instructor;
import Dao.CourseDao;
import Dao.InstructorDao;
import util.HibernateUtil;


//First run the test in the following order by uncomment the test you want to make and uncomment to you finished: saveCourse, updateCourse,deleteCourse,retrieveCourse and retrieveCourseByEmail



public class CourseTestCase {
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
        // Initialize objects before each test
        String instructorUUID = "bd64b61b-b0cb-4797-99ae-7374de516e8c";
        UUID instId = UUID.fromString(instructorUUID);
        instructor = new Instructor();
        instructor.setInstructorId(instId);

        course = new Course();
        course.setTitle("mobile");
        course.setInstructor(instructor);
    }

    //@Test
    public void saveCourse() {
        try (Session session = HibernateUtil.getSession().openSession()) {
            Transaction transaction = session.beginTransaction();

            String result = courseDao.saveCourse(course);
            transaction.rollback(); // Rollback the transaction to keep tests independent

            assertEquals("saved", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //@Test
    public void deleteCourse() {
        try (Session session = HibernateUtil.getSession().openSession()) {
            Transaction transaction = session.beginTransaction();

            courseDao.saveCourse(course);
            String result = courseDao.deleteCourse(course);
            transaction.rollback(); // Rollback to maintain test isolation

            assertEquals("deleted", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //@Test
    public void updateCourse() {
        try (Session session = HibernateUtil.getSession().openSession()) {
            Transaction transaction = session.beginTransaction();

            courseDao.saveCourse(course);
            course.setTitle("Advanced Mobile Development");

            String result = courseDao.updateCourse(course);
            Course updatedCourse = courseDao.getCourseId(course.getCourseId().toString());

            transaction.rollback(); // Prevent persistence across tests

            assertEquals("updated", result);
            assertEquals("Advanced Mobile Development", updatedCourse.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //@Test
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

    //@Test
    public void retrieveCourseByEmail() {
        try (Session session = HibernateUtil.getSession().openSession()) {
            Transaction transaction = session.beginTransaction();
            List<Course> courses = instructorDao.retrieveCourseByEmail("ljeanluc394@gmail.com");
            String[] actualCourses = new String[courses.size()];
            for (int i = 0; i < courses.size(); i++) {
                actualCourses[i] = courses.get(i).getTitle();
            }
            
            transaction.rollback();

            String[] expectedCourses = { "mobile", "Advanced Mobile Development"}; 

            assertArrayEquals(expectedCourses, actualCourses);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
