package TheGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Hud {
	
	private int score1;
	private int score2;
	private Ball ball;
		
	private RecHandler handler1; 
	private RecHandler handler2;
	
	public Hud (Ball ball) {
		this.handler1 = new RecHandler();
		this.handler2 = new RecHandler();
		this.ball = ball;
		
	}
	
	public RecHandler digit (int num, RecHandler handler) {
		
		
		if (num == 1) {
			handler.addObject(digitBar(2));
			handler.addObject(digitBar(3));
		}
		if (num == 2) {
			handler.addObject(digitBar(1));
			handler.addObject(digitBar(2));
			handler.addObject(digitBar(7));
			handler.addObject(digitBar(5));
			handler.addObject(digitBar(4));
		}
		if (num == 3) {
			handler.addObject(digitBar(1));
			handler.addObject(digitBar(2));
			handler.addObject(digitBar(7));
			handler.addObject(digitBar(3));
			handler.addObject(digitBar(4));
		}
		if (num == 4) {
			handler.addObject(digitBar(6));
			handler.addObject(digitBar(7));
			handler.addObject(digitBar(2));
			handler.addObject(digitBar(3));
		}
		if (num == 5) {
			handler.addObject(digitBar(1));
			handler.addObject(digitBar(6));
			handler.addObject(digitBar(7));
			handler.addObject(digitBar(3));
			handler.addObject(digitBar(4));
		}
		if (num == 6) {
			handler.addObject(digitBar(1));
			handler.addObject(digitBar(6));
			handler.addObject(digitBar(7));
			handler.addObject(digitBar(5));
			handler.addObject(digitBar(3));
			handler.addObject(digitBar(4));
		}
		if (num == 7) {
			handler.addObject(digitBar(1));
			handler.addObject(digitBar(2));
			handler.addObject(digitBar(3));
		}
		if (num == 8) {
			handler.addObject(digitBar(1));
			handler.addObject(digitBar(2));
			handler.addObject(digitBar(3));
			handler.addObject(digitBar(4));
			handler.addObject(digitBar(5));
			handler.addObject(digitBar(6));
			handler.addObject(digitBar(7));
		}
		if (num == 9) {
			handler.addObject(digitBar(1));
			handler.addObject(digitBar(2));
			handler.addObject(digitBar(3));
			handler.addObject(digitBar(4));
			handler.addObject(digitBar(6));
			handler.addObject(digitBar(7));
		}
		if (num == 0) {
			handler.addObject(digitBar(1));
			handler.addObject(digitBar(2));
			handler.addObject(digitBar(3));
			handler.addObject(digitBar(4));
			handler.addObject(digitBar(5));
			handler.addObject(digitBar(6));
		}
		
		return handler;
		
	}
	
	public void updateDigit() {
		handler1.clearHandler();
		handler2.clearHandler();
		
		System.out.println(ball.Score1+" a "+ball.Score2);
		
		handler1 = digit(ball.Score1, handler1);
		handler2 = digit(ball.Score2, handler2);
		
		
		
		System.out.println(handler1.object.size());
		System.out.println(handler2.object.size());
	}
	
	public void tick () {
			
		
		
	}
	
	public void render (Graphics g) {
		for (int i = 0; i < handler1.object.size(); i++) {
			Rectangle tempObject = handler1.object.get(i);
			
			g.fillRect((int)tempObject.getX() + 50 , (int)tempObject.getY() + 40,(int)tempObject.getWidth(),(int) tempObject.getHeight());
		}
		
		
		for (int i = 0; i < handler2.object.size(); i++) {
			Rectangle tempObject = handler2.object.get(i);
			
			g.fillRect((int)tempObject.getX() + Game.WIDTH - 150 , (int)tempObject.getY() + 40,(int)tempObject.getWidth(),(int) tempObject.getHeight());
		}
		g.setFont(new Font("arial",0,50));
		g.setColor(Color.WHITE);
		g.drawString(ball.Score1+"", 30, 100);
		g.drawString(ball.Score2+"", Game.WIDTH-100, 100);
		
	}
	
	public int getScore1 () {
		return score1;
	}
	
	public int getScore2 () {
		return score2;
	}
	
	public void setScore1 (int score) {
		score1 = score;
	}
	
	public void setScore2 (int score) {
		score2 = score;
	}
	public Rectangle horizontalRectangle (int x, int y) {
		return new Rectangle(x, y, 40, 15);
	}
	
	public Rectangle vertivalRectangle (int x, int y) {
		return new Rectangle(x, y, 15, 40);
	}
	
	public Rectangle digitBar (int numDigit) {
		if (numDigit == 1) {
			return horizontalRectangle(20, 0);
		}
		if (numDigit == 2) {
			return vertivalRectangle(65, 20);
		}
		if (numDigit == 3) {
			return vertivalRectangle(65, 85);
		}
		if (numDigit == 4) {
			return horizontalRectangle(20, 130);
		}
		if (numDigit == 5) {
			return vertivalRectangle(0, 85);
		}
		if (numDigit == 6) {
			return vertivalRectangle(0, 20);
		}
		if (numDigit == 7) {
			return horizontalRectangle(20, 65);
		}
		return null;		
	}
}
