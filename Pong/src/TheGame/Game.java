package TheGame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;



public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	private boolean running = false;
	
	private Thread thread;
	private Handler handler;
	private Menu menu;
	private Hud hud;
	private Ball ball;
	
	public static final int WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static final int	HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	private Player player1;
	private Player player2;
	
	private Wall wall1, wall2;
	
	private STATE gameState = STATE.Menu;
	
		
	public Game() {
		handler = new Handler();
		menu = new Menu(this,ball);
		ball = new Ball(WIDTH/2, HEIGHT/2, ID.Ball, handler,this);
		
		this.addMouseListener(menu);
		this.addKeyListener(new KeyInput(handler,this));
		wall1 = new Wall(0, 0, ID.Wall1);
		wall2 = new Wall(WIDTH-20,0,ID.Wall2);
		
		new Window(WIDTH, HEIGHT,"No name Game", this);
		
		hud = new Hud(ball);
		handler.addObject(ball);
				
		player1 = new Player(50, HEIGHT/2 - 75, ID.Player1,handler,this);
		player2 = new Player(WIDTH - 80, HEIGHT/2 - 75, ID.Player2,handler,this);
		handler.addObject(player1);
		handler.addObject(player2);
		handler.addObject(wall1);
		handler.addObject(wall2);
		
	}
		
	
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
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running) render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		render();
		handler.tick();
		menu.tick();
		hud.tick();
		}
	
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.white);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.BLACK);
		g.fillRect(15, 15, WIDTH - 30, HEIGHT - 30);
		
		for (int i = 0; i < 15; i++) {
			g.setColor(Color.WHITE);
			g.fillRect(WIDTH/2 - 12, i * 95, 25, 50);
		}
		
		handler.render(g);
		menu.render(g);
		hud.render(g);
		
		g.dispose();
		bs.show();	
	}
	
	public static float clamp(float var, float min, float max) {
		if (var >= max) return var = max;
		else if (var <= min) return var = min;
		else return var;
	}
	
		
	public static void main ( String agrs[] ) {
		new Game();
	}
	
	public void setState (STATE st) {
		gameState = st;
	}
	
	public STATE getState () {
		return gameState;
	}
}