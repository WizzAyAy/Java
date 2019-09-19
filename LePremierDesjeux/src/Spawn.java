import java.util.Random;

public class Spawn {
	private Handler handler;
	private HUD hud;
	private int scoreKeep = 0;
	private Random r = new Random();
	private Game game;

	public Spawn(Handler handler,HUD hud, Game game) {
		super();
		this.handler = handler;
		this.hud = hud;
		this.game = game;
		
	}
	
	public void tick () {
		scoreKeep++;
		
		if (scoreKeep >= 100) {
			scoreKeep = 0;
			hud.setLevel(hud.getLevel()+1);
			
			//mode difficile
			/*
			handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.fastEnemy, handler));
			handler.addObject(new SmartEnemy(20,20, ID.SmartEnemy, handler));
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
			handler.addObject(new BossEnemy((Game.WIDTH/2)-48,-120, ID.BossEnemy, handler));
			handler.clearEnemys();
			 */
			
			if(game.hardDifficulty) {
					if(hud.getLevel() == 2) {
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.fastEnemy, handler));
						handler.addObject(new SmartEnemy(20,20, ID.SmartEnemy, handler));
						}
					else if(hud.getLevel() == 3){
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.fastEnemy, handler));
					}
					else if(hud.getLevel() == 4){
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
						handler.addObject(new SmartEnemy(20,20, ID.SmartEnemy, handler));
					}
					else if(hud.getLevel() == 5){
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.fastEnemy, handler));
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.fastEnemy, handler));
					}
					else if(hud.getLevel() == 6){
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.fastEnemy, handler));
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.fastEnemy, handler));
						handler.addObject(new SmartEnemy(20,20, ID.SmartEnemy, handler));
					}
					else if(hud.getLevel() == 7){
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.fastEnemy, handler));
						handler.addObject(new SmartEnemy(20,20, ID.SmartEnemy, handler));
					}
					else if (hud.getLevel() == 10) {
						handler.clearEnemys();
						handler.addObject(new BossEnemy((Game.WIDTH/2)-48,-120, ID.BossEnemy, handler));
						handler.addObject(new SmartEnemy(20,20, ID.SmartEnemy, handler));
						
					}
					if(hud.getLevel() == 20) {
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.fastEnemy, handler));
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.fastEnemy, handler));
						handler.addObject(new SmartEnemy(20,20, ID.SmartEnemy, handler));
					}
					else if(hud.getLevel() == 22){
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.fastEnemy, handler));
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.fastEnemy, handler));
						handler.addObject(new SmartEnemy(20,20, ID.SmartEnemy, handler));
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.fastEnemy, handler));
					}
					else if(hud.getLevel() == 24){
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
						handler.addObject(new SmartEnemy(20,20, ID.SmartEnemy, handler));
					}
					else if(hud.getLevel() == 25){
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
						handler.addObject(new SmartEnemy(20,20, ID.SmartEnemy, handler));
					}
					else if(hud.getLevel() == 26){
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
						
					}
					else if(hud.getLevel() == 27){
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.fastEnemy, handler));
					}
					else if (hud.getLevel() == 30) {
						handler.addObject(new BossEnemy((Game.WIDTH/2)-48,-120, ID.BossEnemy, handler));
					}
				}
			
			//mode facile
			else if(game.hardDifficulty == false) {
				if(hud.getLevel() == 2) {
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				}
				else if(hud.getLevel() == 3){
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.fastEnemy, handler));
				}
				else if(hud.getLevel() == 4){
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				}
				else if(hud.getLevel() == 5){
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				}
				else if(hud.getLevel() == 6){
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				}
				else if(hud.getLevel() == 7){
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.fastEnemy, handler));
				}
				else if (hud.getLevel() == 10) {
					handler.clearEnemys();
					handler.addObject(new BossEnemy((Game.WIDTH/2)-48,-120, ID.BossEnemy, handler));
				}
				if(hud.getLevel() == 20) {
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				}
				else if(hud.getLevel() == 22){
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.fastEnemy, handler));
				}
				else if(hud.getLevel() == 24){
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				}
				else if(hud.getLevel() == 25){
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				}
				else if(hud.getLevel() == 26){
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				}
				else if(hud.getLevel() == 27){
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.fastEnemy, handler));
				}
				else if (hud.getLevel() == 30) {
					handler.addObject(new BossEnemy((Game.WIDTH/2)-48,-120, ID.BossEnemy, handler));
				}
			}
			
			
			
		}
	}
	
}
