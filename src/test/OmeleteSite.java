package test;

import java.util.ArrayList;
import java.util.List;

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
		
	}
	
	
	@Override
	public ArrayList<String> getReviewLinks() {
		
		ArrayList<String> links = new ArrayList<String>();
		
		try {
			while (html.findElement(By.partialLinkText("pr—xima È")) != null) {
				
				System.out.println("Pega uma pagina ... ");
				
				List<WebElement> elinks = html.findElements(By.xpath("//li[@class='games']//span[@class='tit']/a"));
				
				for (WebElement webElement : elinks) {
					links.add(webElement.getText());
				}
				
				html.findElement(By.partialLinkText("pr—xima È")).click();
				pause(3000);
			}
		} catch(NoSuchElementException e) {
			System.out.println("Pegou todas as p‡ginas ... eu espero");
		}
		
		
		
		return links;
	}

	@Override
	public String getNameGame() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGrade() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
