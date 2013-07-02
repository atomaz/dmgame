package test;

import java.util.ArrayList;
import java.util.List;

import model.Game;
import model.Review;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class OmeleteSite extends BaseTest {

	
	@Test
	public void test(){
		html = new HtmlUnitDriver();
		
		html.get("http://omelete.uol.com.br/todas/?secao=games&tipo=criticas");
		
		pause(3000);
		
		ArrayList<String> links = getReviewLinks();
		
		System.out.println(links);
		
		ArrayList<Review> reviews = new ArrayList<Review>();
		
		for (String link : links) {
			
			html.get(link);
			
			Review r = new Review();
			Game g = new Game();
			
			g.setName(getFromHtmlNameGame());
			r.setDescription(getFromHtmlDescription());
			r.setGrade(getFromHtmlGrade());
			
			
			
		}
		
	}
	
	
	@Override
	public ArrayList<String> getReviewLinks() {
		
		ArrayList<String> links = new ArrayList<String>();
		
		try {
			do {
				
				
				System.out.println("Pega uma pagina ... ");
				
				List<WebElement> elinks = html.findElements(By.xpath("//li[@class='games']//span[@class='tit']/a[contains(@href,'/games')]"));
				
				for (WebElement webElement : elinks) {
					links.add(webElement.getAttribute("href"));
				}
				
				
				
				html.findElement(By.partialLinkText("pr—xima È")).click();
				pause(3000);
			} while (html.findElement(By.partialLinkText("pr—xima È")) != null);
		} catch(NoSuchElementException e) {
			System.out.println("Pegou todas as p‡ginas ... eu espero");
		}
		
		
		html.get(links.get(0));
		

		return links;
	}

	@Override
	public String getFromHtmlNameGame() {
		
		return null;
	}

	@Override
	public double getFromHtmlGrade() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getFromHtmlDescription() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
