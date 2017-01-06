
public class JugadorA implements JugadorBase {
	
	public static final String NOMBRE = "Jugador A";
	private int peces;
	private int coordenadaX;
	private int coordenadaY;

	public JugadorA() {
		this.peces = 0;
	}
	
	public JugadorA(int x, int y, int p) {
		this.coordenadaX = x;
		this.coordenadaY = y;
		this.peces = p;		
	}
	
	public void setCoordenadas(int x, int y) {
		this.coordenadaX = x;
		this.coordenadaY = y;
	}
	
	public void setCoordenadaX(int x) {
		this.coordenadaX = x; 
	}
	
	public void setCoordenadaY(int y) {
		this.coordenadaY = y; 
	}
	
	public int getCoordenadaX() {
		return this.coordenadaX;
	}
	
	public int getCoordenadaY() {
		return this.coordenadaY;
	}
	
	public int getPeces() {
		return this.peces;
	}
	
	public void setPeces(int p) {
		this.peces = p;
	}
	
	public void masPeces(int p) {
		this.peces += p;
	}
	
	public boolean tieneMasPuntosQue(JugadorB otro) {
		if(this.getPeces() > otro.getPeces()) return true;
		else return false;
	}
	
	public boolean tieneCoordenadas(int x, int y) {
		if(this.coordenadaX == x && this.coordenadaY == y) return true;
		else return false;
	}

}
