package model;
import java.io.Serializable;
import java.util.ArrayList;


public class Review implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private String url;
	private Game game;
	private double grade;
	private String description;
	private ArrayList<Platform> platforms;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ArrayList<Platform> getPlatforms() {
		return platforms;
	}
	public void setPlatforms(ArrayList<Platform> platforms) {
		this.platforms = platforms;
	}

	
	
}
