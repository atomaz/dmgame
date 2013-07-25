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
			
			// a sa�da est� estranha devido a essa linha, mas n�o est� errado.
			// isso evita pegar resenhas de livros por exemplo. Perco um ou outro jogo
			// mas mantenho dados consistentes
			
			System.out.println("--------------------------------------------------");
			System.out.println(link);
				
			g.setName(getFromHtmlNameGame());
			
			

			
			
			r.setGame(g);
			
			saveReview(r);
			
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
				
				
				
				html.findElement(By.partialLinkText("pr�xima �")).click();
				pause(3000);
			} while (html.findElement(By.partialLinkText("pr�xima �")) != null);
		} catch(NoSuchElementException e) {
			System.out.println("Pegou todas as p�ginas ... eu espero");
		}
		
		
		//html.get(links.get(0));
		

		return links;
	}

	@Override
	public String getFromHtmlNameGame() {
		
		String nameGame;
		WebElement htmlNameGame = html.findElement(By.xpath("//div[@class='grid_8 omega hdartigo']/h1"));
		
		nameGame = htmlNameGame.getText().toLowerCase();
			
		nameGame = nameGame.replace(" cr�tica do livro", "");
		nameGame = nameGame.replace(" cr�tica ", "");
		nameGame = nameGame.replace("|", "");
		nameGame = nameGame.replace(":", "");
			
	
		// substituindo a numera��o
		nameGame = nameGame.replaceAll("2", "ii");
		nameGame = nameGame.replaceAll("3", "iii");
		nameGame = nameGame.replaceAll("4", "iv");
		nameGame = nameGame.replaceAll("5", "v");
		
		// retirando dois pontos e tra�o
		nameGame = nameGame.replaceAll("'s", "");
		nameGame = nameGame.replaceAll("'", "");
		nameGame = nameGame.replaceAll(":", "");
		nameGame = nameGame.replaceAll("-", "");
		
		System.out.println(nameGame);
			
		return nameGame;
	
	}

	
	
	

}
