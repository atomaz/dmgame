package test.dao;

import java.util.ArrayList;

import junit.framework.Assert;
import model.Game;
import model.Platform;
import model.Review;

import org.junit.Test;

import dao.ReviewDAO;

public class TestReviewDAO {

	@Test
	public void insert() {
		
		Review review = new Review();
		//review.setDescription("a description here");
		review.setUrl("http://www.google.com.br");
//		review.setGrade(10.0);
		Game game = new Game();
		game.setName("Jogos Mortais");
		review.setGame(game);
		Platform p = new Platform();
	
		ReviewDAO dao = new ReviewDAO();
		
		// salva
		dao.saveOrUpdate(review); 
		
		// lista
		//ArrayList<Review> reviews = (ArrayList<Review>)dao.listAllReviews();
		
		//System.out.println(reviews);
		
		//Assert.assertEquals("Objeto n�o foi salvo!", 1, reviews.size());
		
		// deleta
		//dao.delete(reviews.get(0));
		
		//reviews = (ArrayList<Review>) dao.listAllReviews();
		
		//Assert.assertEquals("Objeto n�o foi deletado!", 0, reviews.size());
	}

}
