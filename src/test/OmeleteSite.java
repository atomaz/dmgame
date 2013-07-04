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
			
			double grade = getFromHtmlGrade(); 
			// a saída está estranha devido a essa linha, mas não está errado.
			// isso evita pegar resenhas de livros por exemplo. Perco um ou outro jogo
			// mas mantenho dados consistentes
			
			System.out.println("--------------------------------------------------");
			System.out.println(link);
			
			if (grade != -1)
			{	
				g.setName(getFromHtmlNameGame());
				r.setDescription(getFromHtmlDescription());
				r.setGrade(grade);
			}
			
			
			System.out.println("--------------------------------------------------");
			
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
		
		
		//html.get(links.get(0));
		

		return links;
	}

	@Override
	public String getFromHtmlNameGame() {
		
		String nameGame;
		WebElement htmlNameGame = html.findElement(By.xpath("//div[@class='grid_8 omega hdartigo']/h1"));
		
		nameGame = htmlNameGame.getText();
			
		nameGame = nameGame.replace(" | Crítica", "");
		nameGame = nameGame.replace("Crítica | ", "");
		nameGame = nameGame.replace(":", "");
			
			
		System.out.println(nameGame);
			
		return nameGame;
	
	}

	@Override
	public double getFromHtmlGrade() {
		
		double grade = -1;
		String sGrade;
		

		try {
			WebElement htmlGrade = html.findElement(By.xpath("//div[@class='info']/span[contains(@class,'ranking_')]"));
			
			sGrade = htmlGrade.getText();
			sGrade = sGrade.toLowerCase();
			
			
			if (sGrade.equals("ruim"))
				grade = 1.0;
			else if (sGrade.equals("regular"))
				grade = 2.0;
			else if (sGrade.equals("bom"))
				grade = 3.0;
			else if (sGrade.equals("ótimo"))
				grade = 4.0;
			else if (sGrade.equals("excelente"))
				grade = 5.0;
			
			System.out.println(sGrade + " = nota: " + grade);
			
		} catch (NoSuchElementException e) {
			System.out.println("Grade não encontrado");
		}
		
		return grade;
	}

	@Override
	public String getFromHtmlDescription() {
		
		String content;
		StringBuffer allContent = new StringBuffer();
		
		List<WebElement> htmlContentGame = html.findElements(By.xpath("//div[@id='HOTWordsTxt']//p"));
		
		for (WebElement webElement : htmlContentGame) {
			content = webElement.getText();
			allContent.append(content);
		}
		
		System.out.println(allContent);
		
		return null;
	}
	
	
	

}
