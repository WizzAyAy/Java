package TheGame;

import java.util.LinkedList;

public class MoveHandler {
	LinkedList<Move> list = new LinkedList<Move>();
			
	public void addObject(Move piece) {
		this.list.add(piece);
	}
		
	public void removeObject(Move object) {
		this.list.remove(object);
	}
}