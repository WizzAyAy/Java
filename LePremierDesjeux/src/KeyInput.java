import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	private Game game;
	private boolean[] keyDown = new boolean[4];
			
		public KeyInput(Handler handler, Game game) {
			
			for (int i=0; i < keyDown.length; i++){
				keyDown[i] = false;
			}
		
			this.handler = handler;
			this.game = game;
		}

		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			
			for (int i = 0; i <handler.object.size(); ++i) {
				GameObject tempObject = handler.object.get(i);
				if (tempObject.getId() == ID.Player) {
					if (key == KeyEvent.VK_Z) {keyDown[0] = true;tempObject.setVelY(-handler.speed);}
					if (key == KeyEvent.VK_S) {keyDown[1] = true;tempObject.setVelY(handler.speed);}
					if (key == KeyEvent.VK_D) {keyDown[2] = true;tempObject.setVelX(handler.speed);}
					if (key == KeyEvent.VK_Q) {keyDown[3] = true;tempObject.setVelX(-handler.speed);}
				}
				
					
			}
			
			if(key == KeyEvent.VK_P) {
				
				if(game.gameState == STATE.Game) {
				if (Game.paused) Game.paused = false;
				else Game.paused = true;
				}
			}
			
			if(key == KeyEvent.VK_ESCAPE) {
				AudioPlayer.getMusic("music").stop();
				System.exit(1);
			}
			
			if(key == KeyEvent.VK_SPACE) {
				if(game.gameState == STATE.Game) game.gameState = STATE.Shop;
				else if(game.gameState == STATE.Shop) game.gameState = STATE.Game;
			}
		}
		
		public void keyReleased(KeyEvent e) {
			
			int key = e.getKeyCode();
		
			for (int i = 0; i <handler.object.size(); ++i) {
				GameObject tempObject = handler.object.get(i);
				if (tempObject.getId() == ID.Player) {
					if (key == KeyEvent.VK_Z) keyDown[0]=false;
					if (key == KeyEvent.VK_S) keyDown[1]=false;
					if (key == KeyEvent.VK_D) keyDown[2]=false;
					if (key == KeyEvent.VK_Q) keyDown[3]=false;
					
					//pour avoir des inputs fuilde !
					if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
					if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
					
				}
		
					
			
		}
		}
}
