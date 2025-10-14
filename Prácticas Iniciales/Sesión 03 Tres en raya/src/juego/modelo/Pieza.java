
package juego.modelo;

/**
 * Clase empleada para indicar una posición en una celda por parte de un jugador.
 *
 * @author Alberto Santos Martínez
 */
public class Pieza {

	/** Atributo que define el color de la pieza. */
	private Color color;
	
	/** Atributo que define la celda donde está puesta la pieza. */
	private Celda celda;
	
	/**
	 * Constructor.
	 *
	 * @param color color de la pieza
	 */
	public Pieza(Color color) {
		establecerColor(color);
		celda = null;
	}
	
	/**
	 * Devuelve el color de la pieza.
	 *
	 * @param color el color
	 */
	private void establecerColor(Color color) {
		this.color = color;
	}
	
	/**
	 * Devuelve el color de la pieza.
	 *
	 * @return el color
	 */
	public Color obtenerColor() {
		return color;
	}
	
	/**
	 * Establece una nueva celda, significaría mover la pieza a una celda.
	 *
	 * @param celda la celda
	 */
	public void establecerCelda(Celda celda) {
		this.celda = celda;
	}
	
	/**
	 * Devuelve la celda en la que se encuentra la pieza.
	 *
	 * @return la celda
	 */
	public Celda obtenerCelda() {
		return celda;
	}
	
	/**
	 * Método ToString de la pieza.
	 *
	 * @return el string
	 */
	@Override
	public String toString() {
		return color.toChar()+"";
	}
}
