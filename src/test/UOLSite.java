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
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;

public class UOLSite extends BaseTest {

	@Test
	public void test(){
		html = new SafariDriver();

		html.get("http://jogos.uol.com.br/analises/");

		pause(3000);

		ArrayList<String> links = getReviewLinks();

		System.out.println(links);

		ArrayList<Review> reviews = new ArrayList<Review>();


		for (String link : links) {

			html.get(link);
			pause(4000);

			Review r = new Review();
			Game g = new Game();

			g.setName(getFromHtmlNameGame());
			
			r.setGame(g);
			r.setYear(getYear());
			r.setProducer(getProducer());
			r.setMultiplatform(isMultiplatform());
			
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
			System.out.println("Pegou todas as paginas de UOL ... eu espero");
		}


		return links;
	}

	@Override
	public String getFromHtmlNameGame() {

		String name = html.findElement(By.className("mod-titulo")).findElements(By.tagName("a")).get(0).getText().toLowerCase();
		name = name.concat(" ");
		// substituindo a numeracao
		name = name.replaceAll(" 2 ", " ii ");
		name = name.replaceAll(" 3 ", " iii ");
		name = name.replaceAll(" 4 ", " iv ");
		name = name.replaceAll(" 5 ", " v ");

		// retirando dois pontos e traco
		name = name.replaceAll(":", "");
		name = name.replaceAll("-", "");
		name = name.replaceAll("'", "");
		name = name.replaceAll("&", "and");

		System.out.println("Nome: " + name);
		// se o ultimo caracter for um espaco, remove
		if (name.toCharArray()[name.length()-1] == ' ') {
			name = name.substring(0, name.length()-1);
		}
		
		return name;
	}

	
	public String getProducer() {
		
		String producer = html.findElement(By.xpath("//div[@class='info']")).
				findElements(By.tagName("li")).get(0).
				getText().
				replace("Desenvolvedora: ", "").
				toLowerCase();
		System.out.println("Produtor: " + producer);
		return producer;
	}
	
	public int getYear() {
		String[] years = html.findElement(By.xpath("//div[@class='info']")).
				findElements(By.tagName("li")).get(1).
				getText().
				replace("Lançamento: ", "").
				split("/");
		
		
		
		int year = 0;
		
		if(years.length == 3) {
			year = Integer.parseInt(years[2]);
		} else if (years.length == 2) {
			year = Integer.parseInt(years[1]);
		} else if (years.length == 1) {
			try {
				year = Integer.parseInt(years[0]);
			} catch(NumberFormatException e) {
				// do nothing
			}
		}
		
		System.out.println("Ano: " + year);
		
		return year;
	}
	
	public boolean isMultiplatform() {
		return html.findElement(By.xpath("//div[@class='info']/ul")).
				findElements(By.tagName("li")).size() > 1;
	}

}
