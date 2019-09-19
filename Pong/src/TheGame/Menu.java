package TheGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
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
	private Ball ball;
	
	public Menu (Game game, Ball ball) {
		this.game = game;
		this.ball = ball;
		
	}
	
	
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if (game.getState() == STATE.Menu) {
			if (mouseOver(mx, my, Game.WIDTH/2 - 150, Game.HEIGHT/2 - 300, 300, 100)) {
				System.out.println("Play vs player");
				game.setState(STATE.Game);
			}
			
			if (mouseOver(mx, my,Game.WIDTH/2 - 150, Game.HEIGHT/2 - 100, 300, 100)) {
				System.out.println("Play vs Ia");
				game.setState(STATE.GameIa);
			}
			
			if (mouseOver(mx, my,Game.WIDTH/2 - 150, Game.HEIGHT/2 + 100, 300, 100)) {
				System.exit(0);
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
		
		
		if (game.getState() == STATE.Menu) {
		
		Font font = new Font("Comic Sans MS",1,40);

		g.setColor(col);
		g.fillRect(Game.WIDTH/2 - 150, Game.HEIGHT/2 - 300, 300, 100);
		g.fillRect(Game.WIDTH/2 - 150, Game.HEIGHT/2 - 100, 300, 100);
		g.fillRect(Game.WIDTH/2 - 150, Game.HEIGHT/2 + 100, 300, 100);
		
		g.setColor(Color.WHITE);
		g.drawRect(Game.WIDTH/2 - 150, Game.HEIGHT/2 - 300, 300, 100);
		g.drawRect(Game.WIDTH/2 - 150, Game.HEIGHT/2 - 100, 300, 100);
		g.drawRect(Game.WIDTH/2 - 150, Game.HEIGHT/2 + 100, 300, 100);
		
		
		g.setColor(Color.BLACK);
		drawCenteredString(g, "Play vs Player", new Rectangle(Game.WIDTH/2 - 150, Game.HEIGHT/2 - 300, 300, 100), font);
		drawCenteredString(g, "Play vs Ia", new Rectangle(Game.WIDTH/2 - 150, Game.HEIGHT/2 - 100, 300, 100), font);
		drawCenteredString(g, "Quit", new Rectangle(Game.WIDTH/2 - 150, Game.HEIGHT/2 + 100, 300, 100), font);
		
		}
		
		
	}
	
	public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
	    // Get the FontMetrics
	    FontMetrics metrics = g.getFontMetrics(font);
	    // Determine the X coordinate for the text
	    int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
	    // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
	    int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
	    // Set the font
	    g.setFont(font);
	    // Draw the String
	    g.drawString(text, x, y);
	}
	
	public void tick() {
		
	}
}

