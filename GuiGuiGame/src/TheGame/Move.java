package TheGame;

public class Move {
	
	private Pion depart;
	private Pion arrive;
	
	protected int xD, yD, xA, yA;
	protected ID idD, idA;
	

	
	public Move(Pion depart, Pion arrive, int xD, int yD, int xA, int yA, ID idD, ID idA) {
		super();
		this.depart = depart;
		this.arrive = arrive;
		this.xD = xD;
		this.yD = yD;
		this.xA = xA;
		this.yA = yA;
		this.idD = idD;
		this.idA = idA;
	}

	public int getxD() {
		return xD;
	}

	public void setxD(int xD) {
		this.xD = xD;
	}

	public int getyD() {
		return yD;
	}

	public void setyD(int yD) {
		this.yD = yD;
	}

	public int getxA() {
		return xA;
	}

	public void setxA(int xA) {
		this.xA = xA;
	}

	public int getyA() {
		return yA;
	}

	public void setyA(int yA) {
		this.yA = yA;
	}

	public ID getIdD() {
		return idD;
	}

	public void setIdD(ID idD) {
		this.idD = idD;
	}

	public ID getIdA() {
		return idA;
	}

	public void setIdA(ID idA) {
		this.idA = idA;
	}

	

	public Pion getDepart() {
		return depart;
	}

	public void setDepart(Pion depart) {
		this.depart = depart;
	}

	public Pion getArrive() {
		return arrive;
	}

	public void setArrive(Pion arrivé) {
		this.arrive = arrivé;
	}
}
