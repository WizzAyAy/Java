package TheGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Ball extends GameObject {

	int width = 25;
	int height = 25;
	int velX = 15;
	int velY = 15;
	int Score1 = 0;
	int Score2 = 0;
		
	private Handler handler;
	private Game game;

	
	
	public Ball(float x, float y, ID id,Handler handler, Game game) {
		super(x, y, id);
		this.handler = handler;
		this.game = game;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		
								
		if(y <= 0 || y >= Game.HEIGHT - 25) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 25) velX *= -1;
		
		collison();
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect((int)x, (int)y, width, height);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,25,25);
		
	}
	
	private void collison() {
		for (int i = 0; i < handler.object.size(); i++) {
			
			GameObject tempObject =  handler.object.get(i);
								
			if (tempObject.getId() == ID.Player2 ) {
					if (getBounds().intersects(tempObject.getBounds())) {
						System.out.println("player2");
						velX *=-1 ;
					}
				}
			
			if (tempObject.getId() == ID.Player1 ) {
				if (getBounds().intersects(tempObject.getBounds())) {
					System.out.println("player1");
					velX *=-1;
				}
			}
			if (game.getState() == STATE.Game || game.getState() == STATE.GameIa) {
				if (tempObject.getId() == ID.Wall1 ) {
					if (getBounds().intersects(tempObject.getBounds())) {
						System.out.println("wall1");
						x = Game.WIDTH / 2;
						y = Game.HEIGHT / 2;
						Score2++;
						
						
					}
				}
				
				if (tempObject.getId() == ID.Wall2 ) {
					if (getBounds().intersects(tempObject.getBounds())) {
						System.out.println("wall2");	
						x = Game.WIDTH / 2;
						y = Game.HEIGHT / 2;
						Score1++;
						
					}
				}
			}
		}
	}
}
