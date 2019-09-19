import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BossEnemy extends GameObject {
	
	private Handler handler;
	private Random r = new Random();
	private int timer = 70, timer2 = 50;

	public BossEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		velX = 0;
		velY = 2;
		this.handler = handler;
	}


	public Rectangle getBounds() {
		
		return new Rectangle((int)x,(int)y,16,16);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if (timer < 0) velY = 0;
		else timer--;
		
		if (timer < 0) timer2--;
		if (timer2 < 0) {
			if (velX==0) velX = 3;
			int spawn = r.nextInt(15);
			if(spawn < 2) {
				handler.addObject(new BossEnemyBullet((int)x+15,(int)y+120, ID.BossEnemyBullet, handler));			
			}
			if(spawn > 13) {
				handler.addObject(new BossEnemyBullet((int)x+65,(int)y+120, ID.BossEnemyBullet, handler));			
			}
			
		}
				
		
		if(x <= 0 || x >= Game.WIDTH - 94) velX *= -1;
		
		handler.addObject(new Trail((int)x, (int)y, 100, 100, ID.Trail, handler, Color.cyan, 0.1f));
	}
	
	

	public void render(Graphics g) {
		
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, 100, 100);
		g.setColor(Color.cyan);
		g.drawRect((int)x, (int)y, 100, 100);	
		
		g.setColor(Color.cyan);
		g.drawRect((int)x + 14, (int)y + 74, 21, 51);	
		g.drawRect((int)x + 64, (int)y + 74, 21, 51);
		
		g.setColor(Color.darkGray);
		g.fillRect((int)x + 15, (int)y + 75, 20, 50);	
		g.fillRect((int)x + 65, (int)y + 75, 20, 50);
	}

}
