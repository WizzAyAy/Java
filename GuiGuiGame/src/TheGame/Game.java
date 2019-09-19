package TheGame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;



public class Game extends Canvas implements Runnable{
	
	private Menu menu;
	private Engine engine;
	
	
	public int redVictory = 0;
	public int yellowVictory = 0;
	
	public STATE gameState = STATE.Menu;
	
	
	
	public Game() {
		AudioPlayer.load();
		AudioPlayer.getMusic("music").loop(1,0.02f);
		
		engine = new Engine(this);
		menu = new Menu(this,engine);
		this.addMouseListener(menu);
		this.addMouseListener(engine);
		
		new Window (WIDTH, HEIGHT, "No name Game", this);
	}

	private static final long serialVersionUID = 1L;
	private boolean running = false;
	public static final int WIDTH = 640, HEIGHT = 640;
	private Thread thread;
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amoutOfTicks = 60.0;
		double ns = 1000000000 / amoutOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		//int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running) render();
			//frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames);
				
				//frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		menu.tick();
		engine.tick();
		}
	
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.Win ) {
			engine.render(g);
			menu.render(g);
		}
		else if (gameState == STATE.Game) {
			engine.render(g);
			
			g.setFont(new Font("arial",1,20));
			g.setColor(Color.lightGray);
			g.fillRect(5, 5, 100, 25);
			g.setColor(Color.WHITE);
			g.drawString("Restart", 20, 25);
			
			
			g.setColor(Color.lightGray);
			g.fillRect(530, 5, 100, 25);
			g.setColor(Color.WHITE);
			g.drawString("Undo", 550, 25);
			
			
			
		}
		
		
		g.dispose();
		bs.show();
	}
	
		
	public static void main ( String agrs[] ) {
		new Game();
	}
}
