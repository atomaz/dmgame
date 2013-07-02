package test;

import java.util.ArrayList;

import junit.framework.Assert;
import model.Game;
import model.Platform;

import org.junit.Test;

import dao.GameDAO;
import dao.PlatformDAO;

public class TestGameDAO {

	@Test
	public void insert() {
		
		Game p = new Game();
		p.setName("Play Station 4");
		GameDAO dao = new GameDAO();
		
		// salva
		dao.saveOrUpdate(p); 
		
		// lista
		ArrayList<Game> games = (ArrayList<Game>)dao.listGames();
		
		System.out.println(games);
		
		Assert.assertEquals("Objeto n�o foi salvo!", 1, games.size());
		
		// deleta
		dao.delete(games.get(0));
		
		games = (ArrayList<Game>)dao.listGames();
		
		Assert.assertEquals("Objeto n�o foi deletado!", 0, games.size());
	}

}