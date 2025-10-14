package quantik.undo;

import quantik.util.Color;
import quantik.util.Figura;

/**
 * Clase Jugada auxiliar que almacena los datos de una jugada.
 * 
 * @author Alberto Santos Martínez
 */
public class Jugada {

	/** La figura. */
	private Figura figura;
	
	/** El color. */
	private Color color;
	
	/** La fila. */
	private int fila;
	
	/** La columna. */
	private int columna;
	
	/**
	 * Método constructor de jugada.
	 *
	 * @param fila la fila
	 * @param columna la columna
	 * @param figura la figura
	 * @param color el color
	 */
	public Jugada(int fila, int columna, Figura figura, Color color) {
		this.fila = fila;
		this.columna = columna;
		this.figura = figura;
		this.color = color;
	}
	
	/**
	 * Método que consulta la fila.
	 *
	 * @return la fila
	 */
	public int consultarFila() {
		return fila;
	}
	
	/**
	 * Método que consulta la columna.
	 *
	 * @return la columna
	 */
	public int consultarColumna() {
		return columna;
	}
	
	/**
	 * Método que consulta la figura.
	 *
	 * @return la figura
	 */
	public Figura consultarFigura() {
		return figura;
	}
	
	/**
	 * Método que consulta el color.
	 *
	 * @return el color
	 */
	public Color consultarColor() {
		return color;
	}
}
