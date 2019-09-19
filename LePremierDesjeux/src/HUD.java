import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {
	
	
	private int bounds = 0;
	public static float HEALTH = 100;
	private float greenValue;
	
	private int score = 0;
	private int coin = 0;
	
	private int level = 1;
	
	
	public void tick() {
	HEALTH = Game.clamp(HEALTH, 0, 100 + (bounds/2));
	greenValue = HEALTH*2;
	greenValue = Game.clamp(greenValue, 0, 255);
	score += 1;
	coin += 1;
	}
	
	public void render(Graphics g) {
		
		
		g.setColor(Color.lightGray);
		g.fillRect(15, 15, 200 + bounds, 32);
		g.setColor(new Color(100,(int)greenValue,0));
		g.fillRect(15, 15, (int)HEALTH*2, 32);
		g.setColor(Color.YELLOW);
		g.drawRect(15, 15, 200 + bounds , 32);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial",0,20));
		g.drawString("Score : " + score, 10, 70);
		g.drawString("Level : " + level, 10, 90);
		g.drawString("Space for Shop", 10, 110);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("arial",0,20));
		g.drawString(HEALTH+" / "+(100+bounds), (int) (HEALTH)/2 + 15, 40);
		}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getBounds() {
		return bounds;
	}

	public void setBounds(int bounds) {
		this.bounds = bounds;
	}
	
	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}

	
}
