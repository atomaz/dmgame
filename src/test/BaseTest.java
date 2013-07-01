package test;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

/**
 * 
 * Classe base para coleta dos dados
 * */
abstract class BaseTest {

	protected WebDriver html;
	
	public abstract String getNameGame();
	public abstract String getGrade();
	public abstract String getDescription();
	public abstract ArrayList<String> getReviewLinks();
	
	public void pause(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
