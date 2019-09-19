package TheGame;

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
			
			if(key == KeyEvent.VK_ESCAPE) {
				System.exit(1);
			}
			
			for (int i = 0; i <handler.object.size(); ++i) {
				GameObject tempObject = handler.object.get(i);
				if (tempObject.getId() == ID.Player1) {
					if (key == KeyEvent.VK_Z) {keyDown[0] = true;tempObject.setVelY(-10);}
					if (key == KeyEvent.VK_S) {keyDown[1] = true;tempObject.setVelY(10);}
				}
			}
			
			for (int i = 0; i <handler.object.size(); ++i) {
				GameObject tempObject = handler.object.get(i);
				if (tempObject.getId() == ID.Player2) {
					if (key == KeyEvent.VK_UP) {keyDown[2] = true;tempObject.setVelY(-10);}
					if (key == KeyEvent.VK_DOWN) {keyDown[3] = true;tempObject.setVelY(10);}
				}
			}
		}
		
		
		
		public void keyReleased(KeyEvent e) {
			
			int key = e.getKeyCode();
			
			for (int i = 0; i <handler.object.size(); ++i) {
				GameObject tempObject = handler.object.get(i);
				
				if (tempObject.getId() == ID.Player1) {
					if (key == KeyEvent.VK_Z) keyDown[0]=false;
					if (key == KeyEvent.VK_S) keyDown[1]=false;
					
					
					//pour avoir des inputs fuilde !
					if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
					
					
				}
				
				for (int i1 = 0; i1 <handler.object.size(); ++i1) {
					GameObject tempObject1 = handler.object.get(i1);
					if (tempObject1.getId() == ID.Player2) {
						if (key == KeyEvent.VK_UP) keyDown[2]=false;
						if (key == KeyEvent.VK_DOWN) keyDown[3]=false;
						
						//pour avoir des inputs fuilde !
						if(!keyDown[2] && !keyDown[3]) tempObject1.setVelY(0);
						
					}			
				}
			}
		}
}		


