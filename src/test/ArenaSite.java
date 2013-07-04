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

public class ArenaSite extends BaseTest {

	
	/**
	 * NAO TA PEGANDO OS DADOS NÃO SEI O PQ
	 * */
	@Test
	public void test(){
		
		  
		html = new HtmlUnitDriver();
		
		html.get("http://arena.ig.com.br/analises/");
		
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
		
		String btproximo = "//li[@class='nav next']/a[contains(.,'Próxima')]";
		try {
			do {
				
				
				System.out.println("Pega uma pagina ... ");
				
				List<WebElement> elinks = html.findElements(By.xpath("//a[contains(@href,'/analises')]"));
				
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
		
		String name = html.findElement(By.xpath("//h1[@id='noticia-titulo']/h1")).getText().replace("Análise - ", "").replace("'", "");
		
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
		String nota = html.findElement(By.xpath("//h3/strong[contains(.,'Nota: ')]")).getText().split(" ")[1];
		if(nota == null || nota == "")
			return -1;
		// a nota varia de 0 a 5. Queremos que a nota de todos os jogos variem entre 0 e 10. 
		return Double.parseDouble(nota)*2;
	}

	@Override
	public String getFromHtmlDescription() {
		String contents = html.findElement(By.xpath("//div[@id='noticia']")).getText();
		
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
