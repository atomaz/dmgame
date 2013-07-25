package test;

import java.util.ArrayList;
import java.util.List;

import model.Game;
import model.GameType;
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
	 * Testa a remocao de tags html de uma string
	 * */
	@Test
	public void testXpath() {
		//		String htmldescription = html.findElement(By.xpath("")).toString();
		String html = "<html><head>html title</head><body>some text inside body</body></html>";
		// remover tags de html description deixando so o texto
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


			// pegando o tipo de jogo (acao, aventura)
			GameType type = new GameType();
			type.setName(getGameType());
			r.setGameType(type);

			// pegando as notas do jogo
			int[] grades = getGrades();
			r.setGradeGraphic(grades[0]);
			r.setGradeJogability(grades[1]);
			r.setGradeFun(grades[2]);
			r.setGradeSound(grades[3]);

			r.setGame(g);
			r.setUrl(link);

			reviews.add(r);

			saveReview(r);
		}

	}


	@Override
	public ArrayList<String> getReviewLinks() {

		ArrayList<String> links = new ArrayList<String>();

		String btproximo = "//a[@class='botoes' and @title='Pr\u00f3xima']";
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
			System.out.println("Pegou todas as paginas de TechTudo ... eu espero");
		}


		return links;
	}

	@Override
	public String getFromHtmlNameGame() {

		String name = html.findElement(By.xpath("//div[@class='conteudo-item primeiro']/h1")).getText(); 

		// substituindo a numeracao
		name = name.replaceAll("2", "ii");
		name = name.replaceAll("3", "iii");
		name = name.replaceAll("4", "iv");
		name = name.replaceAll("5", "v");

		// retirando dois pontos e traco
		name = name.replaceAll(":", "");
		name = name.replaceAll("-", "");
		name = name.replaceAll("&", "and");

		return name.toLowerCase();
	}	


	public String getGameType() {
		return html.findElement(By.className("nome-categoria")).getText().replaceFirst("Jogos de ", "");
	}


	/**
	 * Retorna uma lista com as notas na seguinte ordem: 
	 * GRAFICOS, JOGABILIDADE, DIVERSAO, SOM
	 * 
	 * <li class"l7">
	 * 		<label>GRAFICOS</label>
	 * 		<span>7</span>
	 * </li>
	 * 
	 * */
	public int[] getGrades() {
		int[] grades = new int[4];

		for (int i = 0; i < grades.length; i++) {
			grades[i] = -1;
		}


		WebElement wgrades = html.findElement(By.className("nota-editor-middle"));
		List<WebElement> wg = wgrades.findElements(By.tagName("li"));

		for (int i = 0; i < wg.size(); i++) {
			char gradeOption = wg.get(i).findElement(By.tagName("label")).getText().toLowerCase().charAt(0);
			int nota = Integer.parseInt(wg.get(i).findElement(By.tagName("span")).getText());
			switch (gradeOption) {
			case ('g'): grades[0] = nota; break;
			case ('j'): grades[1] = nota; break;
			case ('d'): grades[2] = nota; break;
			case ('s'): grades[3] = nota; break;
			default: nota = -1;
			}

		}

		return grades;
	}

}
