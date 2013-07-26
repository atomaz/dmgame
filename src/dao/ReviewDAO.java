package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Game;
import model.GameType;
import model.Review;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ReviewDAO {

	public Review selectReviewByNameGame(String name) {
		Session session = Factory.getSessionFactoryInstance().openSession();
		Transaction tx = null;
		Review r = null;
		try {
			tx = session.beginTransaction();
			List result = session.createQuery("FROM Review r WHERE r.game.name = '" + name + "'").list();
			if (result.size() > 0) {
				r = (Review) result.get(0);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return r;
		
	}
	
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
				savingReview(review, session);
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

			savingReview(review, session);

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

	public void savingReview(Review review, Session session) {
		// verificar se o objeto game dentro da review existe
		Review reviewToBeSaved = review;
		System.out.println("********* INSERTING ***********");
		System.out.println(review);
		System.out.println("*******************************");
		
		Query q = session.createQuery("FROM Game g WHERE g.name = '" + reviewToBeSaved.getGame().getName() + "'");
		if (q != null) {
			List games = q.list();
			if (games.size() > 0) {
				reviewToBeSaved.setGame((Game)games.get(0));
				
			}
		}
		session.saveOrUpdate(reviewToBeSaved.getGame());
		if (reviewToBeSaved.getGame().getId() > -1) {
			q = session.createQuery("FROM Review r WHERE r.game.id = '" + review.getGame().getId() + "'");
			List reviews = q.list();
			if (reviews.size() > 0) {
				// existe um jogo com o nome dado .. basta atualizá-lo
				reviewToBeSaved = (Review) reviews.get(0);
				
				System.out.println("* * * * * UPDATING * * * * *");
				System.out.println(reviewToBeSaved);
				System.out.println("* * * * * * * * * * * * * * *");
			}
		}
		reviewToBeSaved.setGameType(review.getGameType());
		if (review.getGameType() != null) {
			// com o tipo
			q = session.createQuery("FROM GameType gt WHERE gt.name = '" + reviewToBeSaved.getGameType().getName() + "'");
			if (q != null) {
				List games = q.list();
				if (games.size() > 0) {
					reviewToBeSaved.setGameType((GameType)games.get(0));
				}
			}
			
			session.saveOrUpdate(reviewToBeSaved.getGameType());
		}
		reviewToBeSaved.setYear(review.getYear());
		reviewToBeSaved.setProducer(review.getProducer());
		reviewToBeSaved.setMultiplatform(review.isMultiplatform());
		//reviewToBeSaved.setMultiplayer(review.isMultiplayer());
		
		session.saveOrUpdate(reviewToBeSaved);
	}


}
