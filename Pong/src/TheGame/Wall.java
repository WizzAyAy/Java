package TheGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall extends GameObject {

	public Wall(float x, float y, ID id) {
		super(x, y, id);
	}


	@Override
	public void render(Graphics g) {

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,25,Game.HEIGHT);
	}


	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

}
