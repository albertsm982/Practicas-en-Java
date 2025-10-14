
package juego.modelo;

/**
 * Empleado para dar color al jugador.
 *
 * @author Alberto Santos Martínez
 */
public enum Color {

	/** Enumeración del jugador de color blanco. */
	BLANCO('O'),
	/** Enumeración del jugador de color negro. */
	NEGRO('X');

	/** El caracter. */
	private char caracter;

	/**
	 * Constructor que recibe el carácter vinculado al color.
	 *
	 * @param paramcaracter el parametro del caracter
	 */
	private Color(char paramcaracter) {
		this.caracter = paramcaracter;
	}

	/**
	 * Método ToChar de Color.
	 *
	 * @return el color
	 */
	public char toChar() {
		return caracter;
	}
}
