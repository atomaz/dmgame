package dao;

import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Factory {

	private static SessionFactory factory;

	
	public static SessionFactory getSessionFactoryInstance() {
		if (factory == null) {
			try{
		         //factory = new Configuration().configure(new File("./src/mapping/hibernate.config.xml")).buildSessionFactory();
		         factory = new Configuration().configure(new File("./resources/mapping/hibernate.config.xml")).buildSessionFactory();
		      }catch (Throwable ex) { 
		         System.err.println("Failed to create sessionFactory object." + ex);
		         ex.printStackTrace();
		         throw new ExceptionInInitializerError(ex); 
		      }
		}
		return factory;
	}

}
