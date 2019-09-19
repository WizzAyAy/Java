import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BossEnemyBullet extends GameObject{
	
	private Handler handler;
	private Random r = new Random();
	private GameObject player;
	private int bulletChoice;
	private Color col = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));

	public BossEnemyBullet(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
				
		for (int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ID.Player)
				player = handler.object.get(i);
		}
		
		bulletChoice = r.nextInt(5);
		
		if(bulletChoice == 1) {
		float diffX = x - player.getX() - 8;
		float diffY = y - player.getY() - 8;
		float distance = (float) Math.sqrt((x - player.getX())*(x - player.getX()) + (y - player.getY())*(y - player.getY()));
		
		velX = (float) ((-1.0/distance) * diffX) * 5;
		velY = (float) ((-1.0/distance) * diffY) * 5;
		}
		
		else {
			velX = r.nextInt(5- -5)-5;
			velY = 5;
		}
	}


	public Rectangle getBounds() {
		
		return new Rectangle((int)x,(int)y,16,16);
	}

	public void tick() {
		x += velX;
		y += velY;
				
		if(y <= 0 || y >= Game.HEIGHT - 16*2) handler.removeObject(this);
		if(x <= 0 || x >= Game.WIDTH - 16) handler.removeObject(this);
		
		handler.addObject(new Trail((int)x, (int)y, 16, 16, ID.Trail, handler, col, 0.1f));
		
	}
	
	

	public void render(Graphics g) {
		
		g.setColor(col);
		g.fillRect((int)x, (int)y, 16, 16);		
	}


}
