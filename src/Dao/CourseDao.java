package Dao;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.Transaction;

import modal.Course;
import util.HibernateUtil;

public class CourseDao {
	
	@SuppressWarnings("null")
	public String saveCourse(Course course) {
		
		Transaction transaction = null;
		
		try(Session session = HibernateUtil.getSession().openSession()){
			
			transaction = session.beginTransaction();
			session.save(course);
			transaction.commit();
			session.close();
			return "saved";
		}catch(Exception ex) {
			ex.printStackTrace();
			return "error";
		}
		
	}
	public String deleteCourse(Course Course) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            
            if (Course != null) {
                session.delete(Course);
                transaction.commit();
                return "deleted";
            } else {
                return "Course_not_found";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "error";
        }
    }
	
	public Course getCourseId(String courseId) {
        try (Session session = HibernateUtil.getSession().openSession()) {
        	
        	UUID CourseId = UUID.fromString(courseId);
            return session.get(Course.class, CourseId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public String updateCourse(Course course) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            
            // Update the course object in the database
            session.update(course);
            
            transaction.commit();
            return "updated";
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return "update failed";
        }
    }
	
	public List<String> getAllCourses() {
		try (Session session = HibernateUtil.getSession().openSession()) {
            return session.createQuery("SELECT DISTINCT c.title FROM Course c", String.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

}
}