package model;
import java.io.Serializable;
import java.util.ArrayList;


public class Review implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private String url;
	private Game game;
	private boolean multiplatform;
	private GameType gameType;
	private int gradeGraphic;
	private int gradeJogability;
	private int gradeFun;
	private int gradeSound;
	private String country;
	private boolean multiplayer;
	private String producer;
	private int year;
	
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
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
	public boolean isMultiplatform() {
		return multiplatform;
	}
	public void setMultiplatform(boolean multiplatform) {
		this.multiplatform = multiplatform;
	}
	public GameType getGameType() {
		return gameType;
	}
	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}
	public int getGradeGraphic() {
		return gradeGraphic;
	}
	public void setGradeGraphic(int gradeGraphic) {
		this.gradeGraphic = gradeGraphic;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public boolean isMultiplayer() {
		return multiplayer;
	}
	public void setMultiplayer(boolean multiplayer) {
		this.multiplayer = multiplayer;
	}
	
}
