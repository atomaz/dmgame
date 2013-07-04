package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Game;
import model.Review;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaixakiSite extends BaseTest {

	
	@Test
	public void test(){
		
		  
		html = new HtmlUnitDriver();
		
		html.get("http://www.baixakijogos.com.br/analises");
		
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
			
			//saveReview(r);
		}
		
	}
	
	
	@Override
	public ArrayList<String> getReviewLinks() {
		
		ArrayList<String> links = new ArrayList<String>();
		
		String btproximo = "//div[@class='paginantion']/a[contains(.,'Próximo')]";
		try {
			do {
				
				
				System.out.println("Pega uma pagina ... ");
				
				List<WebElement> elinks = html.findElements(By.xpath("//li[contains(@id,'review')/a[contains(@href,'/analise')]"));
				
				for (WebElement webElement : elinks) {
					links.add(webElement.getAttribute("href"));
				}
				
				html.findElement(By.xpath(btproximo)).click();
				pause(3000);
			} while (html.findElement(By.xpath(btproximo)) != null);
		} catch(NoSuchElementException e) {
			System.out.println("Pegou todas as páginas de Arena ... eu espero");
		}
		

		return links;
	}

	@Override
	public String getFromHtmlNameGame() {
		
		String name = html.findElement(By.xpath("//h1[@class='gametitle']/span/span")).getText().replace("'", "");
		
		// substituindo a numeração
		name = name.replaceAll("2", "ii");
		name = name.replaceAll("3", "iii");
		name = name.replaceAll("4", "iv");
		name = name.replaceAll("5", "v");
		
		// retirando dois pontos e traço
		name = name.replaceAll(":", "");
		name = name.replaceAll("-", "");
		
		return name.toLowerCase();
	}

	@Override
	public double getFromHtmlGrade() {
		String nota = html.findElement(By.xpath("//span[@class='num rating']")).getText();
		
		// a nota varia de 0 a 100. Queremos que a nota de todos os jogos variem entre 0 e 10. 
		return Double.parseDouble(nota)/10;
	}

	@Override
	public String getFromHtmlDescription() {
		String contents = html.findElement(By.xpath("//div[@class='text description']")).getText();
		
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println(contents);
		System.out.println("---------------------------------------------------------------------------------------");
		// só funciona em drivers que executam JS
		//String contents = (String)((JavascriptExecutor)html).executeScript("return arguments[0].innerHTML;", element);
		
		//This replaces the HTML with a single space, then collapses whitespace, and then trims any on the ends.
		//String description = contents.replaceAll("<[^>]*>", " ").replaceAll("\\s+", " ").trim();; 
		return contents;
	}
	
	
	

}
