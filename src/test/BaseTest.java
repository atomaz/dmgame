package test;

import java.util.ArrayList;

import model.Review;

import org.openqa.selenium.WebDriver;

/**
 * 
 * Classe base para coleta dos dados
 * */
abstract class BaseTest {

	protected WebDriver html;
	
	public abstract String getFromHtmlNameGame();
	public abstract double getFromHtmlGrade();
	public abstract String getFromHtmlDescription();
	public abstract ArrayList<String> getReviewLinks();
	
	public void pause(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveReview(Review review) {
		// salvar no banco
	}
	
	
}
