package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Game;
import model.Review;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ReviewDAO {
	
	public List<Review> listAllReviews() {
		
		ArrayList<Review> list = new ArrayList<Review>();
		
		Session session = Factory.getSessionFactoryInstance().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List result = session.createQuery("FROM Review").list();
			for (Iterator iterator = result.iterator(); iterator.hasNext();) {
				Review p = (Review) iterator.next();
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
	
	public void saveOrUpdate(List<Review> reviews) {
		Session session = Factory.getSessionFactoryInstance().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			for (Review review : reviews) {
				// verificar se o objeto game dentro da review existe
				Query q = session.createQuery("FROM Game g WHERE g.name_game = '" + review.getGame().getName() + "'");
				if (q != null) {
					List games = q.list();
					if (games.size() > 0) {
						review.setGame((Game)games.get(0));
					}
				}
				session.saveOrUpdate(review.getGame());
				
				session.saveOrUpdate(review);
			}
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void saveOrUpdate(Review review) {
		Session session = Factory.getSessionFactoryInstance().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			// verificar se o objeto game dentro da review existe
			List games = session.createQuery("FROM Game g WHERE g.name = '" +review.getGame().getName() + "'").list();
			if (games.size() > 0) {
				review.setGame((Game)games.get(0));
			}
			GameDAO dao = new GameDAO();
			dao.saveOrUpdate(review.getGame());
			
			session.save(review);
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void delete(Review review) {
		Session session = Factory.getSessionFactoryInstance().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(review);
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	

}
