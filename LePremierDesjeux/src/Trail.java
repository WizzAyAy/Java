import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Trail extends GameObject{

	private float alpha = 1;
	private Handler handler;
	private Color color;
	private int height, width ;
	private float life; // entre 0.001 et 0.1 = durée de vie des particules
	
	
	public Trail(int x, int y, int height, int width, ID id, Handler handler, Color color, float life) {
		super(x, y, id);
		this.handler = handler;
		this.color = color;
		this.height = height;
		this.width = width;
		this.life = life;
	}

	
	public void tick() {
		if(alpha > life) {
			alpha -= life - 0.001f;
		}
		else handler.removeObject(this);
	}

	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		
		g.setColor(color);
		g.drawRect((int)x, (int)y, width, height);
		
		g2d.setComposite(makeTransparent(1));
	}
	
	private AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type, alpha));
	}

	
	public Rectangle getBounds() {
		return null;
	}

}
