
import java.util.*;

public class JuegoPinguinos {

	private static int[][] peces         = new int[8][8];
	private static int casillasTresPeces = 0;
	private static int casillasDosPeces  = 0;
	private static int casillasUnPez     = 0;
	

	public static void main(String[] args) {
		JugadorA jugadorA = new JugadorA();
		JugadorB jugadorB = new JugadorB();
		System.out.println("¡Bienvenido al juego de los pinguinos!");
		System.out.println("Este es el tablero inicial");
		generaPecesIniciales();
		coordenadasInicialesJugadorA(jugadorA);
		coordenadasInicialesJugadorB(jugadorB);
		System.out.println("Que empiece el juego...");
		Scanner scanner = new Scanner(System.in);
		boolean fin = false;
		// el bucle principal del juego
		gameloop: while (fin != true) {
			dibujaPantalla(jugadorA, jugadorB);
			turnoJugadorA(jugadorA, scanner);
			dibujaPantalla(jugadorA, jugadorB);
			turnoJugadorB(jugadorB, scanner);
	        int contadorCasillasEnBlanco = 0;
	        // comprobamos si aun quedan peces
	        doubleloop: for(int i=0; i<(peces.length); i++) {
				for(int j=0; j<(peces.length); j++) {
					if(peces[i][j] == 0){
						contadorCasillasEnBlanco++;
					}
					else {
						break doubleloop;
					}
				}
			}
	        // si ya no quedan peces:
	        if(contadorCasillasEnBlanco == 64) {
	        	compruebaGanador(jugadorA, jugadorB);
	        	break gameloop;
	        }
		}
		scanner.close();
		System.out.println("FIN DEL JUEGO");
	}
	
	public static void generaPecesIniciales() {
		for(int i=0; i<(peces.length); i++) {
			for(int j=0; j<(peces.length); j++) {
				boolean completado = false;
				while(completado != true) {
					int casilla = (int) Math.floor(Math.random() * 4);
					if(casilla == 3) {
						if(casillasTresPeces < 22) {
							casillasTresPeces++;
							peces[i][j] = 3;
							completado = true;
							System.out.print(" 3");
						}
					}
					else if(casilla == 2) {
						if(casillasDosPeces < 21) {
							casillasDosPeces++;
							peces[i][j] = 2;
							completado = true;
							System.out.print(" 2");
						}
					
					}
					else if(casilla == 1) {
						if(casillasUnPez < 21) {
							casillasUnPez++;
							peces[i][j] = 1;
							completado = true;
							System.out.print(" 1");
						}
					}
				}
			}
			System.out.println();
		}
	}

	public static void dibujaPantalla(JugadorA jugadorA, JugadorB jugadorB) {
		for(int i=0; i<(peces.length); i++){ 
			for(int j=0; j<(peces.length); j++){
				// si hay jugador y peces
				if (jugadorA.tieneCoordenadas(i, j) && peces[i][j] == 1) {
					System.out.print(" A1 ");
					// el pinguino se come al pez y aumentan los puntos del jugador
					jugadorA.masPeces(1);
					peces[i][j] = 0;
				}
				else if (jugadorB.tieneCoordenadas(i, j) && peces[i][j] == 1) {
					System.out.print(" B1 ");
					jugadorB.masPeces(1);
					peces[i][j] = 0;
				}
				else if (jugadorA.tieneCoordenadas(i, j) && peces[i][j] == 2) {
					System.out.print(" A2 ");
					jugadorA.masPeces(2);
					peces[i][j] = 0;
				}
				else if (jugadorB.tieneCoordenadas(i, j) && peces[i][j] == 2) {
					System.out.print(" B2 ");
					jugadorB.masPeces(2);
					peces[i][j] = 0;
				}
				else if (jugadorA.tieneCoordenadas(i, j) && peces[i][j] == 3) {
					System.out.print(" A3 ");
					jugadorA.masPeces(3);
					peces[i][j] = 0;
				}
				else if (jugadorB.tieneCoordenadas(i, j) && peces[i][j] == 3) {
					System.out.print(" B3 ");
					jugadorA.masPeces(3);
					peces[i][j] = 0;
				}
				else if (jugadorA.tieneCoordenadas(i, j) && peces[i][j] == 0) {
					System.out.print(" A  ");
				}
				else if (jugadorB.tieneCoordenadas(i, j) && peces[i][j] == 0) {
					System.out.print(" B  ");
				}
				// si solo hay peces
				else if (peces[i][j] == 0) {
					System.out.print("    ");
				}
				else if (peces[i][j] == 1) {
					System.out.print("  1 ");
				}
				else if (peces[i][j] == 2) {
					System.out.print("  2 ");
				}
				else if (peces[i][j] == 3) {
					System.out.print("  3 ");
				}
	    	}
			// cambio de linea del tablero
			System.out.println();
		}
	}
	
	public static void coordenadasInicialesJugadorA(JugadorA jugador){
		int i = (int) Math.floor(Math.random() * 8);
		int j = (int) Math.floor(Math.random() * 8);
		//Coordenadas coordAleatorias = new Coordenadas(i, j);
		if(peces[i][j] == 1)
			jugador.setCoordenadas(i, j);
		else
			coordenadasInicialesJugadorA(jugador);
	}
	
	public static void coordenadasInicialesJugadorB(JugadorB jugador){
		int i = (int) Math.floor(Math.random() * 8);
		int j = (int) Math.floor(Math.random() * 8);
		//Coordenadas coordAleatorias = new Coordenadas(i, j);
		if(peces[i][j] == 1)
			jugador.setCoordenadas(i, j);
		else
			coordenadasInicialesJugadorB(jugador);
	}
	
	public static void turnoJugadorA(JugadorA jugadorA, Scanner scanner) {
		System.out.println("Turno jugador A");
        System.out.println("Mueve tu jugador: ");
        System.out.println("W: arriba; A: izquierda; S: abajo; D: derecha");
        String resp = scanner.nextLine();
        resp = resp.toUpperCase();
        if (resp.equals("D")) {
        	// comprobamos para no salirnos del tablero
        	if(jugadorA.getCoordenadaY() < 8) {
        		int n = jugadorA.getCoordenadaY() + 1;
        		jugadorA.setCoordenadaY(n);
        	}
        }
        else if (resp.equals("W")) {
        	if(jugadorA.getCoordenadaX() > 0) {
	        	int n = jugadorA.getCoordenadaX() - 1;
	        	jugadorA.setCoordenadaX(n);        		
        	}
        }
        else if (resp.equals("A")) {
        	if(jugadorA.getCoordenadaY() > 0) {
        		int n = jugadorA.getCoordenadaY() - 1;
        		jugadorA.setCoordenadaY(n);
        	}
        }
        else if (resp.equals("S")) {
        	if(jugadorA.getCoordenadaX() < 8) {
        		int n = jugadorA.getCoordenadaX() + 1;
	        	jugadorA.setCoordenadaX(n);  
        	}
        }
        else {
        	System.out.println("Lo sentimos, pero pierdes tu turno. " 
        			+ "Tienes que escribir: W, A, S, D o: w, a, s, d"
        			+ "No puedes salirte del tablero.");
        }
	}
	
	public static void turnoJugadorB(JugadorB jugadorB, Scanner scanner) {
		System.out.println("Turno jugador B");
        System.out.println("Mueve tu jugador: ");
        System.out.println("W: arriba; A: izquierda; S: abajo; D: derecha");
        String resp1 = scanner.nextLine();
        resp1 = resp1.toUpperCase();
        if (resp1.equals("D")) {
        	if(jugadorB.getCoordenadaY() < 8) {
        		int n = jugadorB.getCoordenadaY() + 1;
        		jugadorB.setCoordenadaY(n);
        	}
        }
        else if (resp1.equals("W")) {
        	if(jugadorB.getCoordenadaX() > 0) {
	        	int n = jugadorB.getCoordenadaX() - 1;
	        	jugadorB.setCoordenadaX(n);        		
        	}
        }
        else if (resp1.equals("A")) {
        	if(jugadorB.getCoordenadaY() > 0) {
        		int n = jugadorB.getCoordenadaY() - 1;
        		jugadorB.setCoordenadaY(n);
        	}
        }
        else if (resp1.equals("S")) {
        	if(jugadorB.getCoordenadaX() < 8) {
        		int n = jugadorB.getCoordenadaX() + 1;
	        	jugadorB.setCoordenadaX(n);  
        	}
        }
        else {
        	System.out.println("Lo sentimos, pero pierdes tu turno. " 
        			+ "Tienes que escribir W, A, S, D o: w, a, s, d"
        			+ "No puedes salirte del tablero.");
        }
	}
	
	public static void compruebaGanador(JugadorA jugadorA, JugadorB jugadorB) {
		if(jugadorA.getPeces() > jugadorB.getPeces()){
			System.out.println("El Jugador A ha ganado la partida");
		}
		else if(jugadorA.getPeces() < jugadorB.getPeces()){
			System.out.println("El Jugador B ha ganado la partida");
		}
		else if(jugadorA.getPeces() == jugadorB.getPeces()){
			System.out.println("El juego ha quedado en empate");
		}
	}

}