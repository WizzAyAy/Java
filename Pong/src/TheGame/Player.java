package TheGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{
	
	

	@SuppressWarnings("unused")
	private Handler hanlder;
	private GameObject ball;
	private Game game;
	
	public Player(float x, float y, ID id, Handler hanlder, Game game ) {
		super(x, y, id);
		this.hanlder = hanlder;
		this.game = game;
		
		for (int i = 0; i < hanlder.object.size(); i++) {
			
			if (hanlder.object.get(i).getId() == ID.Ball) {
				System.out.println("lol");
				ball = hanlder.object.get(i);
			}
		}
	}

	@Override
	public void tick() {
		if (id == ID.Player2 && game.getState() == STATE.GameIa) {
				
				float diffY = y - ball.getY() - 75;
				float distance = (float) Math.sqrt((y - ball.getY())*(y - ball.getY()));
				
				
				velY = (float) ((-1.0/distance) * diffY) * 50;
			
		}
		y += velY;			
		y = Game.clamp(y, 0, Game.HEIGHT - 150);
		
		
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect((int)x, (int)y, 25, 150);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,25,150);
	}
}
