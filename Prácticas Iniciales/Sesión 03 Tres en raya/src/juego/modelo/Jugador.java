
package juego.modelo;

/**
 * Clase que modela un jugador del juego.
 *
 * @author Alberto Santos Martínez
 */	
public class Jugador {
	
	/** Nombre del jugador. */
	private String nombre;
	
	/** Color del jugador. */
	private Color color;
	
	/**
	 * Clase constructor de la clase Jugador.
	 *
	 * @param nombre nombre del jugador
	 * @param color color del jugador
	 */
	public Jugador(String nombre, Color color) {
		this.nombre = nombre;
		this.color = color;
	}
	
	/**
	 * Devuelve el color del jugador.
	 *
	 * @return el color
	 */
	public Color obtenerColor() {
		return color;
	}
	
	/**
	 * Devuelve el nombre del jugador.
	 *
	 * @return el nombre
	 */
	public String obtenerNombre() {
		return nombre;
	}
	
	/**
	 *  Devuelve un nuevo objeto Pieza, del color correspondiente al jugador actual sobre
	 *  el que se invoca.
	 *
	 * @return la pieza
	 */
	public Pieza generarPieza() {
		
		Pieza pieza = new Pieza(obtenerColor());
		return pieza;
	}
	
	
	
	/**
	 * Método ToString de la clase Jugador.
	 *
	 * @return el string
	 */
	@Override
	public String toString(){
		return color.toString();
	}

}
