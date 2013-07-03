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

public class TechTudoSite extends BaseTest {

	/**
	 * Testa a remoção de tags html de uma string
	 * */
	@Test
	public void testXpath() {
//		String htmldescription = html.findElement(By.xpath("")).toString();
		String html = "<html><head>html title</head><body>some text inside body</body></html>";
		// remover tags de html description deixando só o texto
		//This replaces the HTML with a single space, then collapses whitespace, and then trims any on the ends.
		String description = html.replaceAll("<[^>]*>", " ").replaceAll("\\s+", " ").trim();; 
		
		Assert.assertEquals(description, "html title some text inside body");
	}
	
	
	@Test
	public void test(){
		html = new HtmlUnitDriver();
		
		html.get("http://www.techtudo.com.br/tudo-sobre/jogos/ultimas-de/reviews.html");
		
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
		
		String btproximo = "//a[@class='botoes' and @title='Próxima']";
		try {
			do {
				
				
				System.out.println("Pega uma pagina ... ");
				
				List<WebElement> elinks = html.findElements(By.xpath("//h3[@class='tt-plantao-titulo']/a[contains(@href,'/review')]"));
				
				for (WebElement webElement : elinks) {
					links.add(webElement.getAttribute("href"));
				}
				
				html.findElement(By.xpath(btproximo)).click();
				pause(3000);
			} while (html.findElement(By.xpath(btproximo)) != null);
		} catch(NoSuchElementException e) {
			System.out.println("Pegou todas as páginas de TechTudo ... eu espero");
		}
		

		return links;
	}

	@Override
	public String getFromHtmlNameGame() {
		
		String name = html.findElement(By.xpath("//div[@class='conteudo-item primeiro']/h1")).getText(); 
		
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
				
		String grade = html.findElement(By.xpath("//div[@class='nota-editor-right']/div")).getAttribute("content");
		
		return Double.parseDouble(grade);
	}

	@Override
	public String getFromHtmlDescription() {
		String contents = html.findElement(By.id("materia-letra")).getText();
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
