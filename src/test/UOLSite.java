package test;

import java.util.ArrayList;
import java.util.List;

import model.Game;
import model.Review;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.Assert;

public class UOLSite extends BaseTest {

	@Test
	public void test(){
		html = new HtmlUnitDriver();
		
		html.get("http://jogos.uol.com.br/analises/");
		
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
			r.setGame(g);
			r.setUrl(link);
			
			reviews.add(r);
			
			saveReview(r);
		}
		
	}
	
	
	@Override
	public ArrayList<String> getReviewLinks() {
		
		ArrayList<String> links = new ArrayList<String>();
		
		String btproximo = "//div[@class='paginacao rodape']/a[contains(@href,'http://jogos.uol.com.br/analises/?pgProx=')]";
		try {
			do {
				
				
				System.out.println("Pega uma pagina ... ");
				
				List<WebElement> elinks = html.findElements(By.xpath("//h2/a[contains(@href,'/analises')]"));
				
				for (WebElement webElement : elinks) {
					links.add(webElement.getAttribute("href"));
				}
				
				html.findElement(By.xpath(btproximo)).click();
				pause(3000);
			} while (html.findElement(By.xpath(btproximo)) != null);
		} catch(NoSuchElementException e) {
			System.out.println("Pegou todas as p‡ginas de UOL ... eu espero");
		}
		

		return links;
	}

	@Override
	public String getFromHtmlNameGame() {
		
		return html.findElement(By.xpath("//div[@id='analise']/h1")).getText().replace("An‡lise: ", "").replace("'", "");
	}

	@Override
	public double getFromHtmlGrade() {
		// Ou seja, n‹o possui nota
		return -1;
	}

	@Override
	public String getFromHtmlDescription() {
		String contents = html.findElement(By.xpath("//div[@class='resumo']")).getText();
		
		List<WebElement> list = html.findElements(By.xpath("//div[@class='boxModulo']"));
		for (WebElement e : list) {
			contents += e.getText();
		}
		
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println(contents);
		System.out.println("---------------------------------------------------------------------------------------");
		// s— funciona em drivers que executam JS
		//String contents = (String)((JavascriptExecutor)html).executeScript("return arguments[0].innerHTML;", element);
		
		//This replaces the HTML with a single space, then collapses whitespace, and then trims any on the ends.
		//String description = contents.replaceAll("<[^>]*>", " ").replaceAll("\\s+", " ").trim();; 
		return contents;
	}
	
	
	

}
