package TheGame;


import java.awt.Rectangle;
import java.util.LinkedList;

public class RecHandler {
	
		LinkedList<Rectangle> object = new LinkedList<Rectangle>();
		
		
			
		public void addObject(Rectangle object) {
			this.object.add(object);
		}
		
		public void removeObject(Rectangle object) {
			this.object.remove(object);
		}
		public void clearHandler() {
			for (int i = 0; i < object.size(); i++) {
				removeObject(object.get(i));
				i--;
			}
		}

		
}
