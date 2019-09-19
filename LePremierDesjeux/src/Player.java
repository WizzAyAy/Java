import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Player extends GameObject{

	Handler handler;
	
	private BufferedImage player_image;
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		SpriteSheet ss = new SpriteSheet(Game.spriteSheet);
		player_image = ss.grabImage(1, 1, 32, 32);
		
		}

	public Rectangle getBounds() {
		
		return new Rectangle((int)x,(int)y,32,32);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH -37);
		y = Game.clamp(y, 0, Game.HEIGHT -61);
		
		
		handler.addObject(new Trail((int)x, (int)y, 32, 32, ID.Trail, handler, Color.white, 0.2f));
		collison();
	}
	
	private void collison() {
		for (int i = 0; i < handler.object.size(); i++) {
			
			GameObject tempObject =  handler.object.get(i);
			
			if (tempObject.getId() == ID.BasicEnemy ) {
				if (getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 1; 
					AudioPlayer.getSound("ouch").play(1,0.01f);
				}
			}
			
			if (tempObject.getId() == ID.fastEnemy ) {
				if (getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 4; 
					AudioPlayer.getSound("ouch").play(1,0.01f);
				}
			}
			
			if (tempObject.getId() == ID.SmartEnemy ) {
				if (getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 10; 
					AudioPlayer.getSound("ouch").play(1,0.01f);
				}
			}
			
			if (tempObject.getId() == ID.BossEnemy ) {
				if (getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 100; 
					AudioPlayer.getSound("ouch").play(1,0.01f);
				}
			}
			
			if (tempObject.getId() == ID.BossEnemyBullet ) {
				if (getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 1; 
					AudioPlayer.getSound("ouch").play(1,0.01f);
				}
			}
		}
	}

	public void render(Graphics g) {
	
		//g.setColor(new Color(37,100,170));
		//g.fillRect((int)x, (int)y, 32, 32);
		
		g.drawImage(player_image, (int)x, (int)y, null);
		
	}


	
	
	
	

}
