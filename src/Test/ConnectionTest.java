package Test;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.junit.Test;

import util.HibernateUtil;

public class ConnectionTest {
	
	@Test
	public void test()  {
		Session session = HibernateUtil.getSession().openSession();
		
		assertNotNull(session);
	}

}
