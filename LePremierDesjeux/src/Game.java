import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.jcraft.jogg.Buffer;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread;
	private boolean running = false;
	
	public static boolean paused = false;
	public boolean hardDifficulty = false;
	
	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	private Shop shop;
	
		
	public STATE gameState = STATE.Menu;
	
	public static BufferedImage spriteSheet;
	
	
	public Game() {
		
		BufferedImageLoader loader = new BufferedImageLoader();
		spriteSheet = loader.loadImage("/sprite_kant1.png");
		
		this.handler = new Handler();
		this.hud = new HUD();
		this.shop = new Shop(handler,hud,this);
		this.menu = new Menu(this,handler,hud);
		this.addKeyListener(new KeyInput(handler,this));
		this.addMouseListener(menu);
		this.addMouseListener(shop);
		
		
		AudioPlayer.load();
		AudioPlayer.getMusic("music");
		AudioPlayer.getMusic("music").loop(1,0.01f);
				
		new Window (WIDTH, HEIGHT, "CECI EST UN JEU FDP", this);
				
		spawner = new Spawn(handler, hud, this);
		r = new Random();
		
		
		if(gameState == STATE.Game) {
			handler.addObject(new Player(WIDTH/2-32,HEIGHT/2-32, ID.Player, handler));
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
		}
		if(gameState == STATE.Menu) {
			for (int i = 0; i < 25; i++) {
				handler.addObject(new MenuParticule(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT), ID.MenuParticule, handler));				
			}
		}
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
				System.out.println("Nb of entities: " + handler.object.size());
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		
		
		
		if(gameState == STATE.Game) {
		
			if(!paused) {
			
			hud.tick();
			spawner.tick();
			handler.tick();
		
				if(HUD.HEALTH <= 0) {
					AudioPlayer.getSound("gameover").play(1,0.03f);
					gameState = STATE.End;
					HUD.HEALTH = 100;
					hud.setBounds(0);
					handler.speed = 5;
					handler.clearAll();
					for (int i = 0; i < 25; i++) {
						handler.addObject(new MenuParticule(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT), ID.MenuParticule, handler));				
							}
					}
				}
			}
		
		
		else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End) {
			menu.tick();
			handler.tick();
			}
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
		
		
		if(gameState == STATE.Game) {
		hud.render(g);
		handler.render(g);
		}
		else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End ) {
			menu.render(g);
			handler.render(g);
		}
		else if (gameState == STATE.Shop) {
			shop.render(g);
		}
		
		if(paused) {
			Font font = new Font("arial",3,50);
			g.setFont(font);
			g.setColor(Color.RED);
			g.drawString("Paused", 200, HEIGHT/2);
		}
		
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
}
