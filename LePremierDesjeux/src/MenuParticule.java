import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticule extends GameObject {

	private Handler handler;
	private Random r = new Random();
	private int red,blue,green;
	
	public MenuParticule(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		velX = 0;
		velY = r.nextInt(5)+2;
		this.handler = handler;
		
		red = r.nextInt(255);
		green = r.nextInt(255);
		blue = r.nextInt(255);
	}


	public Rectangle getBounds() {
		
		return new Rectangle((int)x,(int)y,16,16);
	}

	public void tick() {
		x += velX;
		y += velY;
				
		if(y <= 0 || y >= Game.HEIGHT - 16*2) {
			y = 0;
			x = r.nextInt(Game.WIDTH);
			velY = r.nextInt(5)+2;
		}
		
		
		handler.addObject(new Trail((int)x, (int)y, 16, 16, ID.Trail, handler, new Color(red,green,blue), 0.05f));
	}
	

	public void render(Graphics g) {
		
		g.setColor(new Color(red,green,blue));
		g.fillRect((int)x, (int)y, 16, 16);		
	}
}
