package TheGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Pion extends GameObject {
	
	private int size = Game.HEIGHT/5;
	private BufferedImage image;

	public Pion(int x, int y, ID id, BufferedImage image) {
		super(x, y, id);
		this.image = image;
		
	}

	
	public void tick() {
	
	}
	
	public void render(Graphics g) {
		
		if (id == ID.White) {
			g.setColor(Color.WHITE);
			g.drawImage(image, (x-1)*size, (y-1)*size, null);
		}
		else if(id == ID.Red) {
			g.setColor(Color.RED);
			g.drawImage(image, (x-1)*size, (y-1)*size, null);
		}
		else if (id == ID.Yellow) {
			g.setColor(Color.YELLOW);
			g.drawImage(image, (x-1)*size, (y-1)*size, null);
		}
		else if (id == ID.Void) {
			g.setColor(Color.GRAY);
			g.drawImage(image, (x-1)*size, (y-1)*size, null);
		}
		//g.fillRect((x - 1)  * size , (y - 1) * size, size, size);
	}


	public BufferedImage getImage() {
		return image;
	}


	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	

}
