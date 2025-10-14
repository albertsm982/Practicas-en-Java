package juego.modelo;


/**
 * Clase que modela una posición del tablero que puede contener una pieza.
 *
 * @author Alberto Santos Martínez
 */
public class Celda {

	/** Atributo que define el número de la fila del tablero. */
	private int fila;

	/** Atributo que define el número de la columna del tablero. */
	private int columna;

	/** Pieza situada en la celda. */
	private Pieza pieza;

	/**
	 * Constructor.
	 *
	 * @param fila    número de la fila del tablero
	 * @param columna número de la columna del tablero
	 */
	public Celda(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
		establecerPieza(null);
	}

	/**
	 * Obtiene la pieza
	 *
	 * @return la pieza
	 */
	public Pieza obtenerPieza() {
		return pieza;
	}

	/**
	 * Establece la pieza.
	 *
	 * @param pieza la pieza
	 */
	public void establecerPieza(Pieza pieza) {
		this.pieza = pieza;
	}

	/**
	 * Devuelve true si no está vacía y false si no está vacía.
	 *
	 * @return boolean
	 */
	public boolean estaVacia() {
		return pieza == null;
	}

	/**
	 * Devuelve la fila de la celda en el tablero.
	 *
	 * @return la fila
	 */
	public int obtenerFila() {
		return fila;
	}

	/**
	 * Devuelve la columna de la celda dentr del tablero.
	 *
	 * @return la columna
	 */
	public int obtenerColumna() {
		return columna;
	}

	/**
	 * Representación en modo texto en la celda.
	 *
	 * @return Devuelve el string de la celda, en caso de que esté vacía se indicará con un "-"
	 */
	public String toString() {
		if(estaVacia()) 
			return "-";
		else
			return pieza.toString();
		
	}
}
