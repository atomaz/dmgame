package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Game;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class GameDAO {
	
	public List<Game> listGames() {
		
		ArrayList<Game> list = new ArrayList<Game>();
		
		Session session = Factory.getSessionFactoryInstance().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List result = session.createQuery("FROM Game").list();
			for (Iterator iterator = result.iterator(); iterator.hasNext();) {
				Game p = (Game) iterator.next();
				list.add(p);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return list;
		
	}
	
	public void saveOrUpdate(Game game) {
		Session session = Factory.getSessionFactoryInstance().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(game);
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void delete(Game game) {
		Session session = Factory.getSessionFactoryInstance().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(game);
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	

}
