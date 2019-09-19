import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import org.newdawn.slick.Sound;

public class Menu extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	private Random r = new Random();
	private HUD hud;
	private boolean focusDifficulty = false;
	
	
	
	private int red = r.nextInt(255);
	private int green = r.nextInt(255);
	private int blue = r.nextInt(255);
	
	private Color col = new Color(red,green,blue);
	
	public Menu(Game game,Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;

	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
						
		
		if (game.gameState == STATE.Menu) {
			//bouton play
			if(mouseOver(mx,my,210,150,200,64)) {
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32,Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				hud.setLevel(1);
				hud.setScore(0);
				AudioPlayer.getSound("click").play(1,0.1f);			
			}
			//bouton help
			if(mouseOver(mx,my,210,250,200,64)) {
				game.gameState = STATE.Help;
				AudioPlayer.getSound("click").play(1,0.1f);
				
			}
			//bouton quit
			if(mouseOver(mx,my,210,350,200,64)) {
				System.exit(0);
			}
			//boutton Hard
			if(mouseOver(mx,my,550,350,64,64)) {
				game.hardDifficulty = true;
				focusDifficulty = true;
				AudioPlayer.getSound("click").play(1,0.1f);			
			}
			//bouton Easy
			if(mouseOver(mx,my,450,350,64,64)) {
				game.hardDifficulty = false;
				focusDifficulty = false;
				AudioPlayer.getSound("click").play(1,0.1f);
			}
		}
		
		if (game.gameState == STATE.Help || game.gameState == STATE.End ) {
			//bouton return du menu help et du END GAME
			if(mouseOver(mx,my,210,350,200,64)) {
				game.gameState = STATE.Menu;
				AudioPlayer.getSound("click").play(1,0.1f);
			}
		}
	}
	
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			}else return false;
		}else return false;
		
	}
	
	public void tick() {
	
	}
	
	public void render(Graphics g) {
		if (game.gameState == STATE.Menu) {
		Font font = new Font("arial",3,50);
		Font font2 = new Font("arial",1,30);
		Font fontTxt = new Font("arial",1,20);
		
		g.setFont(font);
		g.setColor(col);
		g.drawString("GAME BY KANT1", 120, 70);
		
		g.setFont(font2);		
		g.drawRect(210, 150, 200, 64);
		g.drawString("Play", 270, 190);
		
		
		g.drawRect(210, 250, 200, 64);
		g.drawString("Help", 270, 290);
		
		
		g.drawRect(210, 350, 200, 64);
		g.drawString("Quit", 270, 390);
		
		
		g.setFont(fontTxt);
		
		if(!focusDifficulty) g.setColor(Color.WHITE);
		else g.setColor(col);
		
		g.drawRect(450, 350, 64, 64);
		g.drawString("Easy", 460, 390);
		
		if(focusDifficulty)g.setColor(Color.WHITE);
		else g.setColor(col);
		
		g.drawRect(550, 350, 64, 64);
		g.drawString("Hard", 560, 390);		
		}
		
		else if (game.gameState == STATE.Help) {
			Font font = new Font("arial",3,50);
			Font font2 = new Font("arial",1,30);
			Font fontTxt = new Font("arial",1,15);
			
			
			g.setFont(font);
			g.setColor(col);
			g.drawString("Help", 240, 70);
			
			g.setFont(font2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Return", 270, 390);
			
			g.setFont(fontTxt);
			g.drawString("salut a tous les amis, c'est dx9", 100,150);
			g.drawString("Utilise ZQSD pour déplace ton perso", 100,175);
			g.drawString("P for Pasue", 100,200);
		
		}
		else if (game.gameState == STATE.End) {
			Font font = new Font("arial",3,50);
			Font font2 = new Font("arial",1,30);
						
			g.setFont(font);
			g.setColor(col);
			if(hud.getScore() > 10000)
			g.drawString("GG !", 240, 70);
			else 
			g.drawString("Game Over !", 145, 70);
			
			g.setFont(font2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Return", 270, 390);
			g.drawString("Vous avez fait un score de : " + hud.getScore(), 100,200);
			
		}
	}
}
