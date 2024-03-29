import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class FastEnemy extends GameObject{
	private Handler handler;
	
	public FastEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		velX = 9;
		velY = 2;
		this.handler = handler;
	}


	public Rectangle getBounds() {
		
		return new Rectangle((int)x,(int)y,16,16);
	}

	public void tick() {
		x += velX;
		y += velY;
				
		if(y <= 0 || y >= Game.HEIGHT - 16*2) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
		
		handler.addObject(new Trail((int)x, (int)y, 16, 16, ID.Trail, handler, new Color(150,50,50), 0.02f));
		
	}
	
	

	public void render(Graphics g) {
		
		g.setColor(Color.WHITE);
		g.fillRect((int)x, (int)y, 16, 16);		
	}
}
