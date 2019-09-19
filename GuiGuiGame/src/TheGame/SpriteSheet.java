package TheGame;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private BufferedImage sprite;
	
	public SpriteSheet (BufferedImage ss) {
		this.sprite = ss;
	}
	
	public BufferedImage grabImage() {
		BufferedImage img = sprite.getSubimage( 0, 0, 128, 128);
		return img;
	}

}