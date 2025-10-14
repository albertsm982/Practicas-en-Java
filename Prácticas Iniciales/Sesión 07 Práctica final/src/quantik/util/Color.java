package quantik.util;

/**
 * Enumeracion Color que contiene los dos posibles turnos del juego.
 *
 * @author Alberto Santos Martínez
 */
public enum Color {
	
	/** Color Blanco de la figura. */
	BLANCO ('B'),
	
	/** Color Negro de la figura. */
	NEGRO ('N'); 
	
	/** Caracter letra en el que se guarda el caracter de una de las dos enumeraciones. */
	private char letra;
	
	/**
	 * Método que instancia el color.
	 *
	 * @param letra la letra del color
	 */
	private Color(char letra) {
		this.letra = letra;
	}
	
	/**
	 * Método que devuelve el color contrario al que hay actualmente, si el color es BLANCO, devuelve NEGRO y viceversa.
	 *
	 * @return El color blanco o negro dependiendo del color actual
	 */
	public Color obtenerContrario() {
		if(this.equals(BLANCO)) 
			return NEGRO;
		else 
			return BLANCO;
	}
	
	/**
	 * Metodo toChar que devuelve el caracter de la enumeración.
	 *
	 * @return the char
	 */
	public char toChar() {
		return this.letra;
	}
	
}
