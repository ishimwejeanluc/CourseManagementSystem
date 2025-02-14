package Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.persistence.Query;

import modal.Course;
import modal.Instructor;
import util.HibernateUtil;

public class InstructorDao {
	@SuppressWarnings("null")
	public String saveInstructor(Instructor Instructor) {
		
		Transaction transaction = null;
		
		try(Session session = HibernateUtil.getSession().openSession()){
			
			transaction = session.beginTransaction();
			session.save(Instructor);
			transaction.commit();
			session.close();
			return "saved";
		}catch(Exception ex) {
			ex.printStackTrace();
			return "error";
		}
		
	}
	public String deleteInstructor(Instructor Instructor) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            
            if (Instructor != null) {
                session.delete(Instructor);
                transaction.commit();
                return "deleted";
            } else {
                return "Instructor_not_found";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "error";
        }
    }
	
	public Instructor getInstructorId(String instrId) {
        try (Session session = HibernateUtil.getSession().openSession()) {
        	
        	UUID InstructorId = UUID.fromString(instrId);
            return session.get(Instructor.class, InstructorId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	public String updateInstructor(Instructor InstructorObj) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            session.update(InstructorObj);  
            transaction.commit();
            return "updated";
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return "error";
        }
    }
	
	public List<Course> retrieveCourseByEmail(String email) {
	    List<Course> courseList = new ArrayList<>();

	    try (Session session = HibernateUtil.getSession().openSession()) {
	        Query query = session.createQuery(
	                "SELECT  c FROM Course c WHERE c.instructor.email = :email", Course.class);
	        query.setParameter("email", email);

	        courseList = query.getResultList(); // Retrieve the courses directly

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return courseList; // Return an empty list instead of null
	}

   public List<Instructor> getAllInstructors() {
       try (Session session = HibernateUtil.getSession().openSession()) {
           return session.createQuery("FROM Instructor", Instructor.class).list();
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
   }

}

