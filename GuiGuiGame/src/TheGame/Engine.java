package TheGame;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;



public class Engine extends MouseAdapter{

	private int size = Game.HEIGHT/5;
	
	private Game game;
	
	private Handler whiteHandler;
	private Handler yellowHandler;
	private Handler redHandler;
	private Handler voidHandler;
	
	private MoveHandler moveHanlder;
	
	private Pion theChosenPion = null;
	private Pion theChosenCase = null;
	
	private BufferedImage spriteVoid;
	private BufferedImage spriteRed;
	private BufferedImage spriteYellow;
	private BufferedImage spriteWhite;
	
	// 0 = nobody 1 = red 2 = yellow
	public int win = 0;
	// 1 = red 2 = yellow
	public int lastWin = 0;
	
	private boolean firstRound = true;
	private boolean whiteMouve = false;
	private boolean colorMouve = false;
	private boolean yellowTurn = true;
	
	private float timer = 5f;
	
	public Engine (Game game) {
		this.game = game;
		
		whiteHandler = new Handler();
		yellowHandler = new Handler();
		redHandler = new Handler();
		voidHandler = new Handler();
		moveHanlder = new MoveHandler();
		
		BufferedImageLoader loader = new BufferedImageLoader();
		BufferedImageLoader loader1 = new BufferedImageLoader();
		BufferedImageLoader loader2 = new BufferedImageLoader();
		BufferedImageLoader loader3 = new BufferedImageLoader();
		
		spriteVoid = loader.loadImage("/case_vide.png");
		SpriteSheet ssVoid = new SpriteSheet(spriteVoid);
		spriteVoid = ssVoid.grabImage();
		
		spriteWhite = loader1.loadImage("/pion_blanc.png");
		SpriteSheet ssWhite = new SpriteSheet(spriteWhite);
		spriteWhite = ssWhite.grabImage();
		
		spriteRed = loader2.loadImage("/pion_rouge.png");
		SpriteSheet ssRed = new SpriteSheet(spriteRed);
		spriteRed = ssRed.grabImage();
			
		spriteYellow = loader3.loadImage("/pion_jaune.png");
		SpriteSheet ssYellow = new SpriteSheet(spriteYellow);
		spriteYellow = ssYellow.grabImage();
		
		make();
	}
	
	public void mousePressed(MouseEvent e) {
		if (game.gameState == STATE.Game) {
		
		int mx = e.getX();
		int my = e.getY();
		int posX = mx / size + 1;
		int posY = my / size + 1;
		
		
				
			for (int i = 0; i < whiteHandler.object.size(); i++) {
				GameObject tempObject =  whiteHandler.object.get(i);
				if(tempObject.x == posX && tempObject.y == posY )
					theChosenPion = (Pion) tempObject;
			}
			
			for (int i = 0; i < redHandler.object.size(); i++) {
				GameObject tempObject =  redHandler.object.get(i);
				if(tempObject.x == posX && tempObject.y == posY )
					theChosenPion = (Pion) tempObject;
			}
			
			for (int i = 0; i < yellowHandler.object.size(); i++) {
				GameObject tempObject =  yellowHandler.object.get(i);
				if(tempObject.x == posX && tempObject.y == posY )
					theChosenPion = (Pion) tempObject;
			}
			
			for (int i = 0; i < voidHandler.object.size(); i++) {
				GameObject tempObject =  voidHandler.object.get(i);
				if(tempObject.x == posX && tempObject.y == posY )
					theChosenPion = (Pion) tempObject;
			}
			System.out.println("souris push: X("+posX+") Y("+posY+") "+theChosenPion.id);
			
			
			
			if (mouseOver(mx, my, 530, 5, 100, 25)) {
				//Bouton Undo
				
				Pion p1Undo = moveHanlder.list.getLast().getDepart();
				Pion p2Undo = moveHanlder.list.getLast().getArrive();
				
				Pion p1Real = null;
				Pion p2Real = null;
							
				for (int i = 0; i < whiteHandler.object.size(); i++) {
					GameObject tempObject =  whiteHandler.object.get(i);
					if(tempObject.x == p1Undo.getX() && tempObject.y == p1Undo.getY() )
						p1Real = (Pion) tempObject;
					if(tempObject.x == p2Undo.getX() && tempObject.y == p2Undo.getY() )
						p2Real = (Pion) tempObject;
				}
				
				for (int i = 0; i < redHandler.object.size(); i++) {
					GameObject tempObject =  redHandler.object.get(i);
					if(tempObject.x == p1Undo.getX() && tempObject.y == p1Undo.getY() )
						p1Real = (Pion) tempObject;
					if(tempObject.x == p2Undo.getX() && tempObject.y == p2Undo.getY() )
						p2Real = (Pion) tempObject;
				}
				
				for (int i = 0; i < yellowHandler.object.size(); i++) {
					GameObject tempObject =  yellowHandler.object.get(i);
					if(tempObject.x == p1Undo.getX() && tempObject.y == p1Undo.getY() )
						p1Real = (Pion) tempObject;
					if(tempObject.x == p2Undo.getX() && tempObject.y == p2Undo.getY() )
						p2Real = (Pion) tempObject;
				}
				
				for (int i = 0; i < voidHandler.object.size(); i++) {
					GameObject tempObject =  voidHandler.object.get(i);
					if(tempObject.x == p1Undo.getX() && tempObject.y == p1Undo.getY() )
						p1Real = (Pion) tempObject;
					if(tempObject.x == p2Undo.getX() && tempObject.y == p2Undo.getY() )
						p2Real = (Pion) tempObject;
				}
				
				if (colorMouve == true || whiteMouve == true) {
				if (p1Real != null && p2Real != null) {
					System.out.println("------------------------------------------");
					System.out.println("p1 "+p1Real.getX()+"=X  "+p1Real.getY()+"=Y id ="+p1Real.getId() );
					System.out.println("p2 "+p2Real.getX()+"=X  "+p2Real.getY()+"=Y id ="+p2Real.getId() );
					
					System.out.println("WhiteMove = "+ whiteMouve );
					System.out.println("ColorMove = "+ colorMouve);
					
					if (whiteMouve == false && colorMouve == false) {
						if (yellowTurn) yellowTurn = false;
						else yellowTurn = true;
					}
					
					if(p2Real.getId() == ID.Red || p2Real.getId() == ID.Yellow){
						if (colorMouve) colorMouve = false;
					}
					
					else if(p2Real.getId() == ID.White) {
						if (whiteMouve) whiteMouve = false;
					}
					
					if (p2Real.getId() == ID.Yellow) yellowTurn = true;
					if (p2Real.getId() == ID.Red) yellowTurn = false;
					
					
					

					swapCase(p2Real, p1Real);
					System.out.println("------------------------------------------");
					System.out.println("p1 "+p1Real.getX()+"=X  "+p1Real.getY()+"=Y id ="+p1Real.getId() );
					System.out.println("p2 "+p2Real.getX()+"=X  "+p2Real.getY()+"=Y id ="+p2Real.getId() );
					
					if(p1Real.getId() == ID.Red || p1Real.getId() == ID.Yellow){
						if (colorMouve) colorMouve = false;
					}
					
					else if(p1Real.getId() == ID.White) {
						if (whiteMouve) whiteMouve = false;
					}
					
					
					System.out.println("WhiteMove = "+ whiteMouve);
					System.out.println("ColorMove = "+ colorMouve);
					System.out.println("------------------------------------------");
				}
						
				}	
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		if (game.gameState == STATE.Game) {
		
		int mx = e.getX();
		int my = e.getY();
		int posX = mx / size + 1;
		int posY = my / size + 1;
		
		
		
		
			for (int i = 0; i < whiteHandler.object.size(); i++) {
				GameObject tempObject =  whiteHandler.object.get(i);
				if(tempObject.x == posX && tempObject.y == posY )
					theChosenCase = (Pion) tempObject;
			}
			
			for (int i = 0; i < redHandler.object.size(); i++) {
				GameObject tempObject =  redHandler.object.get(i);
				if(tempObject.x == posX && tempObject.y == posY )
					theChosenCase = (Pion) tempObject;
			}
			
			for (int i = 0; i < yellowHandler.object.size(); i++) {
				GameObject tempObject =  yellowHandler.object.get(i);
				if(tempObject.x == posX && tempObject.y == posY )
					theChosenCase = (Pion) tempObject;
			}
			
			for (int i = 0; i < voidHandler.object.size(); i++) {
				GameObject tempObject =  voidHandler.object.get(i);
				if(tempObject.x == posX && tempObject.y == posY )
					theChosenCase = (Pion) tempObject;
			}
			
			swapCase(theChosenPion, theChosenCase);
			System.out.println(wellplaced(theChosenPion, theChosenCase));
		}
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			}else return false;
		}else return false;	
	}
	
	
	public void swapCase (Pion p1, Pion p2) {
		
		if (!whiteMouve) {
			if (!firstRound) {
				if (p1.id == ID.White) {
					if (p2.id == ID.Void) {
						if(distance(p1.x, p1.y, p2.x, p2.y) <= 1.5) {
						
						//on change les pion de Hanlder et on change les id pour qu il match 
							moveHanlder.addObject(new Move(p1, p2, p1.getX(), p1.getY(), p2.getX(), p2.getY(), p1.getId(), p2.getId()));
							p1.setId(ID.Void);
							p2.setId(ID.White);
							p1.setImage(spriteVoid);
							p2.setImage(spriteWhite);
							whiteHandler.addObject(p2);
							whiteHandler.removeObject(p1);									
							voidHandler.addObject(p1);	
							voidHandler.removeObject(p2);
							AudioPlayer.getSound("move").play(1,0.1f);
							whiteMouve = true;
						}
					}
				}
			}
		}
		
			if (!colorMouve) {
				 if (p1.id == ID.Red || p1.id == ID.Yellow) {
					if (p2.id == ID.Void) {
						if (isPossible(p1, p2) && wellplaced(p1, p2)) {
							
							if (p1.id == ID.Red) {
								if (!yellowTurn) {
									moveHanlder.addObject(new Move(p1, p2, p1.getX(), p1.getY(), p2.getX(), p2.getY(), p1.getId(), p2.getId()));
									p1.setId(ID.Void);
									p2.setId(ID.Red);
									p1.setImage(spriteVoid);
									p2.setImage(spriteRed);
									redHandler.addObject(p2);
									redHandler.removeObject(p1);									
									voidHandler.addObject(p1);	
									voidHandler.removeObject(p2);
									AudioPlayer.getSound("move").play(1,0.1f);
									colorMouve = true;
								}
							}
						
							if (p1.id == ID.Yellow ) {
								if(yellowTurn) {
									moveHanlder.addObject(new Move(p1, p2, p1.getX(), p1.getY(), p2.getX(), p2.getY(), p1.getId(), p2.getId()));
									p1.setId(ID.Void);
									p2.setId(ID.Yellow);
									p1.setImage(spriteVoid);
									p2.setImage(spriteYellow);
									yellowHandler.addObject(p2);
									yellowHandler.removeObject(p1);									
									voidHandler.addObject(p1);	
									voidHandler.removeObject(p2);
									AudioPlayer.getSound("move").play(1,0.1f);	
									colorMouve = true;
								}
							}				
						}
					}
				}
			}
			
		
	
		else if (p1.id == ID.Void) {
			return;
		}
	}
	
	public double distance (int x1, int x2, int y1, int y2) {
		//distance entre le point x et y
		return  Math.sqrt((x1-y1)*(x1-y1) + (x2-y2)*(x2-y2));
	}
	
	public boolean isPossible (Pion p1, Pion p2) {
		int a = p1.x;
		int b = p1.y;
		
		int x = p2.x;
		int y = p2.y;
		
		//on check les lignes si les 2 coordonées des ordonnées sont les meme;
		if( a == x ) return true;
		//on check les colones si les 2 coordoneés des abscises sont les meme;
		if( b == y ) return true;
		
		//on check les diags si les 2 coordoneés des ordonnées +- n et des abscisses sont les meme;
		
		for (int i = 0; i < 4; i++) {
			if (a-i == x && b-i == y) return true;
			if (a+i == x && b+i == y) return true;
			if (a+i == x && b-i == y) return true;
			if (a-i == x && b+i == y) return true;
		}
		
		return false;
	}
	
	public boolean wellplaced (Pion p1, Pion p2) {
		// p1 = pion vers p2 = case
		int p2X = p2.getX();
		int p2Y = p2.getY();
		int dir = direction(p1, p2);
		int nextX = -1;
		int nextY = -1;
		
		if (p2Y == 1 && ((dir == 8) || (dir == 1) || (dir == 2)) ) return true;
		if (p2Y == 5 && ((dir == 4) || (dir == 5) || (dir == 6)) ) return true;
		
		if (p2X == 1 && ((dir == 6) || (dir == 7) || (dir == 8)) ) return true;
		if (p2X == 5 && ((dir == 2) || (dir == 3) || (dir == 4)) ) return true;
		
		if (dir == 1) {
			nextX = p2X;
			nextY = p2Y - 1;
		}
		if (dir == 2) {
			nextX = p2X + 1;
			nextY = p2Y - 1;
		}
		if (dir == 3) {
			nextX = p2X + 1;
			nextY = p2Y;
		}
		if (dir == 4) {
			nextX = p2X + 1;
			nextY = p2Y + 1;
		}
		if (dir == 5) {
			nextX = p2X;
			nextY = p2Y + 1;
		}
		if (dir == 6) {
			nextX = p2X - 1;
			nextY = p2Y + 1;
		}
		if (dir == 7) {
			nextX = p2X - 1;
			nextY = p2Y;
		}
		if (dir == 8) { 
			nextX = p2X - 1;
			nextY = p2Y - 1;
		}
		
		for (int i = 0; i < voidHandler.object.size(); i++) {
			GameObject temp = voidHandler.object.get(i);
			if (temp.getX() == nextX && temp.getY() == nextY) {
				return false;
			}
		}
		
		return true;
	}
	
	public int direction (Pion p1, Pion p2) {
		int x1 = p1.x;
		int y1 = p1.y;
		
		int x2 = p2.x;
		int y2 = p2.y;
		
		
		
		if (x1 == x2 && y1 > y2) return 1;
		if (x1 < x2 && y1 > y2) return 2;
		if (x1 < x2 && y1 == y2) return 3;
		if (x1 < x2 && y1 < y2) return 4;
		
		if (x1 == x2 && y1 < y2) return 5;
		if (x1 > x2 && y1 < y2) return 6;
		if (x1 > x2 && y1 == y2) return 7;
		if (x1 > x2 && y1 > y2) return 8;
		return 0;		
	}
	
	public int isWin () {
		int y = whiteHandler.object.get(0).y;
		if (y == 1) {
			win = 2;
		}
		else if (y == 5) {
			win = 1;
		}
		else win = 0;
		return win;
	}
	
	public void tick () {
		if (whiteBlocked()) {
			AudioPlayer.getSound("aplause").play(1,0.005f);
			firstRound = true;
			whiteMouve = false;
			yellowTurn = false;
			colorMouve = false;
			clearALL();
			make();
			win = 0;
			game.gameState = STATE.Win;
		}
		if (isWin() == 1 || isWin() == 2) {
			game.gameState = STATE.Win;
			if (isWin() == 1) {
				lastWin = 1;
				game.redVictory++;
				AudioPlayer.getSound("aplause").play(1,0.005f);
				firstRound = true;
				whiteMouve = false;
				yellowTurn = false;
				colorMouve = false;
			}
			else {
				lastWin = 2;
				game.yellowVictory++;
				AudioPlayer.getSound("aplause").play(1,0.005f);
				firstRound = true;
				whiteMouve = false;
				colorMouve = false;
				yellowTurn = false;
			}
			clearALL();
			make();
			win = 0;
		}
		
		if (firstRound) {
			if (colorMouve) {
				if (yellowTurn) yellowTurn = false;
				else yellowTurn = true;
				timer = 5;
				firstRound = false;
				colorMouve = false;
			}
		}
		
		if (!firstRound) {
			if(colorMouve && whiteMouve) {
				if (yellowTurn) yellowTurn = false;
				else yellowTurn = true;
				colorMouve = false;
				whiteMouve = false;
				timer = 5;
				}
		}
		
		if (game.gameState == STATE.Game) {
			if (timer >= 0) {
				timer -= 0.1f;
			}
		}
		
	}
	
	public void render (Graphics g) {
		
		for (int i = 0; i < whiteHandler.object.size(); i++) {
			whiteHandler.object.get(i).render(g);
		}
		
		for (int i = 0; i < yellowHandler.object.size(); i++) {
			yellowHandler.object.get(i).render(g);
		}
		
		for (int i = 0; i < redHandler.object.size(); i++) {
			redHandler.object.get(i).render(g);
		}
		
		for (int i = 0; i < voidHandler.object.size(); i++) {
			voidHandler.object.get(i).render(g);
		}
		
		if (game.gameState == STATE.Game) {
		if (timer > 0) {
			g.setFont(new Font("TimesRoman",Font.ITALIC,50));
			g.setColor(Color.LIGHT_GRAY);
			g.fillOval(50, 275, 525, 100);
		
			
				if (yellowTurn) {
					g.setColor(Color.YELLOW);
					g.drawString("Au tour du joueur Jaune", 80, 340);
				}
				else {
					g.setColor(Color.RED);
					g.drawString("Au tour du joueur Rouge", 80, 340);
				}	
			}
		}
		
		
		
		
		/*g.setColor(Color.BLUE);
		for (int i = 0; i < 6; i++) {
			g.drawLine(size*i, 0, size*i, Game.HEIGHT);
			g.drawLine(0, size*i, Game.WIDTH, size*i);
		}*/
		
	}
	
	public void make () {
		whiteHandler.addObject(new Pion(3, 3, ID.White,spriteWhite));
		
		for (int i = 1; i < 6; i++) {
			yellowHandler.addObject(new Pion(i, 1, ID.Yellow,spriteYellow));
		}
		
		for (int i = 1; i < 6; i++) {
			redHandler.addObject(new Pion(i, 5, ID.Red,spriteRed));
		}
		for (int i = 1; i < 6; i++) {
			for (int j = 2; j < 5; j++) {
				if (i != 3 || j != 3) voidHandler.addObject(new Pion(i, j, ID.Void,spriteVoid));
			}
		}
	}
	
	public void clearALL() {
		
		whiteHandler.object.clear();
		voidHandler.object.clear();
		yellowHandler.object.clear();	
		redHandler.object.clear();
	}
	
	public boolean whiteBlocked() {
		int x = whiteHandler.object.get(0).getX();
		int y = whiteHandler.object.get(0).getY();
		int tempX;
		int tempY;
		
		// le pion n'est pas blocker si au moins une case vide est adjacente a lui 
		
		// si x == 1 => le pion est sur le bord gauche 
		if(x == 1) {
			for (int i = 0; i < voidHandler.object.size(); i++) {
				GameObject temp = voidHandler.object.get(i);
				tempX = temp.getX();
				tempY = temp.getY();
				if (tempX == x && tempY == y-1) return false;
				if (tempX == x && tempY == y+1) return false;
					
				if (tempX == x+1 && tempY == y-1) return false;
				if (tempX == x+1 && tempY == y) return false;
				if (tempX == x+1 && tempY == y+1) return false;
			}
		}
		
		// si x == 5 => le pion est sur le bord droit 
		
		else if(x == 5) {
			for (int i = 0; i < voidHandler.object.size(); i++) {
				GameObject temp = voidHandler.object.get(i);
				tempX = temp.getX();
				tempY = temp.getY();
				if (tempX == x && tempY == y-1) return false;
				if (tempX == x && tempY == y+1) return false;
					
				if (tempX == x-1 && tempY == y-1) return false;
				if (tempX == x-1 && tempY == y) return false;
				if (tempX == x-1 && tempY == y+1) return false;
			}
		}
		else 
			for (int i = 0; i < voidHandler.object.size(); i++) {
				GameObject temp = voidHandler.object.get(i);
				tempX = temp.getX();
				tempY = temp.getY();
				if (tempX == x && tempY == y-1) return false;
				if (tempX == x && tempY == y+1) return false;
					
				if (tempX == x-1 && tempY == y-1) return false;
				if (tempX == x-1 && tempY == y) return false;
				if (tempX == x-1 && tempY == y+1) return false;
					
				if (tempX == x-1 && tempY == y-1) return false;
				if (tempX == x-1 && tempY == y) return false;
				if (tempX == x-1 && tempY == y+1) return false;
			}
		
		// sinon => le pion est sur une case au milieu du plateau
		
		return true;
	}

	public void setFirstRound(boolean firstRound) {
		this.firstRound = firstRound;
	}

	public void setColorMouve(boolean colorMouve) {
		this.colorMouve = colorMouve;
	}

	public void setYellowTurn(boolean yellowTurn) {
		this.yellowTurn = yellowTurn;
	}
	public void setWhiteMouve(boolean whiteMouve) {
		this.whiteMouve = whiteMouve;
	}
	
	
	
	
}
