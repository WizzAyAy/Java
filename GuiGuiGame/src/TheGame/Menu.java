package TheGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;


public class Menu extends MouseAdapter {

	private Random r = new Random();

	private int red = r.nextInt(255);
	private int green = r.nextInt(255);
	private int blue = r.nextInt(255);

	private Color col = new Color(red,green,blue);

	private Game game;
	private Engine engine;
	
	public Menu (Game game, Engine engine) {
		this.game = game;
		this.engine = engine;
	}
	
	
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		
		if (game.gameState == STATE.Menu) {
			if(mouseOver(mx, my, 220, 80, 200, 60)) {
				game.gameState = STATE.Game;
				AudioPlayer.getSound("click").play(1,0.03f);
			}
			else if(mouseOver(mx, my, 220, 180, 200, 60)) {
				game.gameState = STATE.Help;
				AudioPlayer.getSound("click").play(1,0.03f);				
			}
			else if(mouseOver(mx, my, 220, 280, 200, 60)) {
				AudioPlayer.getSound("click").play(1,0.03f);
				System.exit(0);
				
			}
		}
		
		else if  (game.gameState == STATE.Help) {
			if(mouseOver(mx, my, 220, Game.HEIGHT - 90, 200, 60)) {
				game.gameState = STATE.Menu;
				AudioPlayer.getSound("click").play(1,0.03f);
			}
		}
		
		else if  (game.gameState == STATE.Win) {
			if(mouseOver(mx, my, 220, Game.HEIGHT - 90, 200, 60)) {
				game.gameState = STATE.Menu;
				AudioPlayer.getSound("click").play(1,0.03f);
				}
			}
		else if (game.gameState == STATE.Game) {
			if(mouseOver(mx, my, 5,5, 100, 25)) {
				game.gameState = STATE.Menu;
				engine.setColorMouve(false);
				engine.setFirstRound(true);
				engine.setWhiteMouve(false);
				engine.setYellowTurn(true);
				engine.clearALL();
				engine.make();
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
	
	
	
	public void render(Graphics g) {
		
		g.setColor(col);
		g.setFont(new Font("arial", 0, 30));
		
		if (game.gameState == STATE.Menu) {

			g.setColor(col);
			g.fillRect(220, 80, 200, 60);
			g.fillRect(220, 180, 200, 60);
			g.fillRect(220, 280, 200, 60);
			
			g.setColor(Color.BLACK);
			g.drawRect(220, 80, 200, 60);
			g.drawRect(220, 180, 200, 60);
			g.drawRect(220, 280, 200, 60);
			
			
			g.drawString("Help", 240, 220);
			g.drawString("New Game", 240, 120);
			g.drawString("Quit", 240, 320);
			
			
			g.setColor(Color.RED);
			g.fillRect(95, 385, 200, 60);
			g.setColor(Color.YELLOW);
			g.fillRect(335, 385, 230, 60);
			
			g.setColor(Color.BLACK);
			g.drawRect(95, 385, 200, 60);
			g.drawRect(335, 385, 230, 60);
			
			g.drawString("red : "+ game.redVictory+ " Victory", 100, 420);
			g.drawString("yellow : "+ game.yellowVictory+ " Victory", 340, 420);
			
			g.setColor(Color.BLACK);
			g.drawString("red : "+ game.redVictory+ " Victory", 100, 420);
			g.setColor(Color.BLACK);
			g.drawString("yellow : "+ game.yellowVictory+ " Victory", 340, 420);
			
		}
		else if (game.gameState == STATE.Help) {
			g.setColor(Color.black);
			g.fillRect(0, 0, Game.WIDTH, 300);
			g.setColor(col);
			g.setFont(new Font("arial",0,22));
			
			
			g.drawString("Le but est de mettre le pion blanc dans votre Camp!", 20, 25);
			g.drawString("Les joueurs joue tour par tour,", 20, 50);
			g.drawString("le premier joueur à jouer bouge qu'un pion de couleur,", 20, 75);
			g.drawString("le pion de couleur doit bouger jusqu'a un obstacle.", 20, 100);
			
			g.drawString("Ensuite les joueurs bouge dans l'ordre de leur choix", 20, 150);
			g.drawString("leurs pions !", 20, 175);
			g.drawString("le pion blanc bouge d'une case", 20, 200);
			
			g.drawString("si le joueur ne peut pas bouger le pion blanc c'est draw !", 20, 250);
			g.drawString("Enjoy the Game !", 20, 275);
			
			g.fillRect(220, Game.HEIGHT - 90, 200, 60);
			g.setColor(Color.BLACK);
			g.drawString("Back", 240, Game.HEIGHT - 50);
			g.drawRect(220, Game.HEIGHT - 90, 200, 60);
		}
		else if (game.gameState == STATE.Win) {			
			
			g.setFont(new Font("arial",0,20));
			if (engine.lastWin == 1) {
				g.setColor(Color.black);
				g.fillRect(10, 55, 500, 40);
				g.setColor(Color.RED);
				g.drawString("GG au joueur ROUGE, Cheh au JAUNE LE NAZE", 20, 80);
			}
			else if (engine.lastWin == 2) {
				g.setColor(Color.black);
				g.fillRect(10, 55, 450, 40);
				g.setColor(Color.YELLOW);
				g.drawString("GG au joueur JAUNE, Cheh au ROUGE LE NAZE", 20, 80);
			}
			else {
				g.setColor(Color.black);
				g.fillRect(10, 55, 450, 40);
				g.setColor(Color.YELLOW);
				g.drawString("EGALITE DE SES MORTS LE BLANC EST BLOCKER", 20, 80);
			}
			g.setFont(new Font("arial",0,25));
			g.fillRect(220, Game.HEIGHT - 90, 200, 60);
			g.setColor(Color.black);
			g.drawRect(220, Game.HEIGHT - 90, 200, 60);
			g.drawString("Back", 240, Game.HEIGHT - 50);
		}
	
	}
	
	public void tick() {
		
	}
}
