package quantik.excepcion;

/**
 * Clase CoordenadasIncorrectasException que extiende de java.lang.Exception con
 * cuatro constructores diferentes.
 * 
 * @author Alberto Santos Martínez
 */
public class CoordenadasIncorrectasException extends Exception {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor que instancia una nueva excepción con un mensaje y un throwable.
	 *
	 * @param message mensaje pasado por parámetro
	 * @param th throw pasado por parámetro
	 */
	public CoordenadasIncorrectasException(String message, Throwable th) {
		super(message, th);
	}

	/**
	 * Constructor que instancia una nueva excepción sin parámetros.
	 */
	public CoordenadasIncorrectasException() {
		super();
	}

	/**
	 * Constructor que instancia una nueva excepción con un mensaje.
	 * 
	 * @param message mensaje pasado por parámetro
	 */
	public CoordenadasIncorrectasException(String message) {
		super(message);
	}

	/**
	 * Constructor que instancia una nueva excepción con un throwable.
	 *
	 * @param cause causa de la excepción
	 */
	public CoordenadasIncorrectasException(Throwable cause) {
		super(cause);
	}

}
