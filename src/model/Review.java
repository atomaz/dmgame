package model;
import java.io.Serializable;
import java.util.ArrayList;


public class Review implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private String url;
	private Game game;
	private Platform platform;
	private GameType gameType;
	private int gradeGrafic;
	private int gradeJogability;
	private int gradeFun;
	private int gradeSound;
	private String description;
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
	public Platform getPlatform() {
		return platform;
	}
	public void setPlatform(Platform platform) {
		this.platform = platform;
	}
	public GameType getGameType() {
		return gameType;
	}
	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}
	public int getGradeGrafic() {
		return gradeGrafic;
	}
	public void setGradeGrafic(int gradeGrafic) {
		this.gradeGrafic = gradeGrafic;
	}
	public int getGradeJogability() {
		return gradeJogability;
	}
	public void setGradeJogability(int gradeJogability) {
		this.gradeJogability = gradeJogability;
	}
	public int getGradeFun() {
		return gradeFun;
	}
	public void setGradeFun(int gradeFun) {
		this.gradeFun = gradeFun;
	}
	public int getGradeSound() {
		return gradeSound;
	}
	public void setGradeSound(int gradeSound) {
		this.gradeSound = gradeSound;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
}
