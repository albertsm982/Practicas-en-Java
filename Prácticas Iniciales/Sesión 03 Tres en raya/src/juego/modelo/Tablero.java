
package juego.modelo;
import juego.util.*;

/**
 * Clase que contiene las celdas donde se van a colocar las piezas de los jugadores.
 *
 * @author Alberto Santos Martínez
 */
public class Tablero {

	/** Matriz donde se va a mantener los estados de las celdas. */
	private Celda[][] matriz;
	
	/**
	 * Constructor.
	 *
	 * @param filas filas del tablero
	 * @param columnas columnas del tablero
	 */
	public Tablero(int filas, int columnas) {
		
		matriz = new Celda[filas][columnas];
		
		for(int i = 0; i< matriz.length;i++) {
			for(int j = 0; j < matriz[i].length; j++) {
				matriz[i][j] = new Celda(i, j);
			}
		}
	}
	
	/**
	 * Situa la pieza en la celda idicada, vincula la pieza a la
	 * celda y la celda a la pieza.
	 *
	 * @param pieza la pieza
	 * @param celda la celda
	 */
	public void colocar(Pieza pieza, Celda celda) {
		
		if(pieza != null && celda != null && celda.estaVacia()) {
			celda.establecerPieza(pieza);
			pieza.establecerCelda(celda);
		}
	}
	
	/**
	 * Metodo que obtiene una celda a partir de una fila y una columna pasada por parámetro.
	 *
	 * @param fila la fila
	 * @param columna la columna
	 * @return la celda
	 */
	public Celda obtenerCelda(int fila, int columna) {
		return matriz[fila][columna];
	}
	
	/**
	 * Método que devuelve el número de piezas de un color pasado por parámetro.
	 *
	 * @param color el color
	 * @return el contador del numero de piezas
	 */
	public int obtenerNumeroPiezas(Color color) {
		
		int contador = 0;
		for(Celda[] fila : matriz) {
			for(Celda celda : fila) {
				if(!celda.estaVacia() && celda.obtenerPieza().obtenerColor() == color ) {
					contador++;
				}
			}
		}
		return contador;
	}
	
	/**
	 * Comprueba si las coordenadas pertenecen a una celda del tablero.
	 *
	 * @param fila la fila
	 * @param columna la columna
	 * @return Deuelve true en caso de la celda esté en el tablero y false en caso contrario
	 */
	public boolean estaEnTablero(int fila, int columna) {
		if(fila <= matriz.length || columna <= matriz.length || fila > 0 || columna > 0){
			return true;
		}
		return false;
	}
	
	/**
	 * Metodo auxiliar que va a recorrer las celdas de la matriz con los incrementos 
	 * pasados por parámetro para devolver el conteo de aquellas que contengan una pieza 
	 * del color pasado por parámentro.
	 *
	 * @param celda la celda
	 * @param color el color
	 * @param incrementoFila el incremento fila
	 * @param incrementoColumna el incremento columna
	 * @return el int
	 */
	private int contadorAux (Celda celda, Color color, int incrementoFila, int incrementoColumna) {
		
		int contador = 0;
		int filaAux = celda.obtenerFila()+incrementoFila;
		int columnaAux = celda.obtenerColumna()+incrementoColumna;
		
		while(estaEnTablero(filaAux, columnaAux)) {
			Celda celdaAux = obtenerCelda(filaAux, columnaAux);
			
			if(celdaAux != null && !celdaAux.estaVacia() 
					&& celdaAux.obtenerPieza().obtenerColor() == color) {
				contador++;
				filaAux = celdaAux.obtenerFila()+incrementoFila;
				columnaAux = celdaAux.obtenerColumna()+incrementoColumna;
		
			}else {
				break;
			}
		}		
		return contador;
	}
	
	/**
	 * Dada la dirección hay que contar las piezas del mismo color.
	 *
	 * @param celda la celda
	 * @param direccion la direccion
	 * @return el contador que cuenta el numero de piezas en el tablero
	 */
	public int contarPiezas(Celda celda, Dirección direccion) {
		
		int contador = 0;
		
		if(!celda.estaVacia()) {
			contador++;
			Color color = celda.obtenerPieza().obtenerColor();
			switch(direccion) {
			case VERTICAL: 
				contador += contadorAux (celda, color, 1, 0);
				contador += contadorAux (celda, color, -1, 0);
				break;
			case HORIZONTAL: 
				contador += contadorAux (celda, color, 0, 1);
				contador += contadorAux (celda, color, 0,-1);
				break;
			case DIAGONAL_SO_NE: 
				contador += contadorAux (celda, color, -1, 1);
				contador += contadorAux (celda, color, 1, -1);
				break;
			case DIAGONAL_NO_SE: 
				contador += contadorAux (celda, color, -1, -1);
				contador += contadorAux (celda, color, 1, 1);
				break;
			}
		}
		
		return contador;
	}
	
	/**
	 * Metodo que devuelve true si todas las celdas del tablero tienen una pieza y false si no.
	 *
	 * @return true en caso de que todas las celdas estén rellenas y false en caso de que haya alguna celda que esté vacia
	 */
	public boolean estaCompleto () {
		for(Celda[] fila : matriz) {
			for(Celda celda : fila) {
				if(celda.estaVacia()) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Método que devuelve un string con el estado del tablero.
	 *
	 * @return el string
	 */
	@Override
	public String toString() {
		String tablero = "";
				for(Celda[] fila : matriz) {
					for(Celda celda : fila) {
						tablero += celda.toString();
					}
					tablero = "\n";
				}
				
		return tablero;
	}
	
}
