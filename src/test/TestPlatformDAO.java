package test;

import java.util.ArrayList;

import junit.framework.Assert;
import model.Platform;

import org.junit.Test;

import dao.PlatformDAO;

public class TestPlatformDAO {

	@Test
	public void insert() {
		
		Platform p = new Platform();
		p.setName("Play Station 4");
		PlatformDAO dao = new PlatformDAO();
		
		// salva
		dao.saveOrUpdate(p); 
		
		// lista
		ArrayList<Platform> platforms = (ArrayList<Platform>)dao.listPlatforms();
		
		Assert.assertEquals("Objeto n‹o foi salvo!", 1, platforms.size());
		
		// deleta
		dao.delete(platforms.get(0));
		
		platforms = (ArrayList<Platform>)dao.listPlatforms();
		
		Assert.assertEquals("Objeto n‹o foi deletado!", 0, platforms.size());
	}

}
