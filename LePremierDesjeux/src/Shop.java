import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Shop extends MouseAdapter {
	
	private Random r = new Random();
	private int red = r.nextInt(255);
	private int green = r.nextInt(255);
	private int blue = r.nextInt(255);
	
	private Color col = new Color(red,green,blue);
	
	private Handler handler;
	private HUD hud;
	private Game game;
	
	private int B1 = 750;
	private int B2 = 750;
	private int B3 = 1000;
	
	public Shop (Handler handler, HUD hud, Game game) {
		this.handler = handler;
		this.hud = hud;
		this.game = game;
	}
	
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
			
		
		if (game.gameState == STATE.Shop) {
			
			//speed up
			if(mouseOver(mx,my,210,150,200,64)) {
				if(hud.getCoin() >= B1) {
					AudioPlayer.getSound("coin").play(1,0.1f);
					hud.setCoin(hud.getCoin() - B1);
					B1 *= 1.4;
					handler.speed += 1;
				}
			}
			
			//HealthUP
			if(mouseOver(mx,my,210,250,200,64)) {
				if(hud.getCoin() >= B2) {
					AudioPlayer.getSound("coin").play(1,0.1f);
					hud.setCoin(hud.getCoin() - B2);
					B2 *= 1.4;
					hud.setBounds(hud.getBounds() + 20);
					HUD.HEALTH += (hud.getBounds() / 2);				
				}
			}
			
			//healtFUll
			if(mouseOver(mx,my,210,350,200,64)) {
				if(hud.getCoin() >= B3) {
					AudioPlayer.getSound("coin").play(1,0.1f);
					hud.setCoin(hud.getCoin() - B3);
					HUD.HEALTH = (100 + (hud.getBounds() / 2));	
				}
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
	public void render (Graphics g) {
		Font font = new Font("arial",3,50);
		Font font2 = new Font("arial",1,15);
					
		g.setFont(font);
		g.setColor(col);
		g.drawString("MARKET PLACE OULALA", 7, 80);
		
		
		
		g.setFont(font2);
		g.drawString("you have : " + hud.getCoin() + " coins", Game.WIDTH/2 - 75 , 120);
		g.drawString("space to go back", Game.WIDTH/2 - 75 , 140);
		
		g.drawRect(210, 150, 200, 64);
		g.drawString("SpeedUP", 240, 180);
		g.drawString("Cost : "+ B1, 240, 200);
		
		g.setFont(font2);
		g.drawRect(210, 250, 200, 64);
		g.drawString("HealthUP", 240, 280);
		g.drawString("Cost : " + B2, 240, 300);
		
		g.setFont(font2);
		g.drawRect(210, 350, 200, 64);
		g.drawString("FullHeal", 240, 380);
		g.drawString("Cost : " + B3, 240, 400);
		
	}
}
