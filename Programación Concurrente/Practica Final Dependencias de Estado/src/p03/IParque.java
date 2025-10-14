package p03;

/**
 * 
 * Interfaz de parque que define dos métodos de entrada y salida del parque
 * 
 * @author Alberto Santos Martínez
 * @author Jon Ander Incera Moreno
 */
public interface IParque {
	/**
	 * entrarAlParque y salirDelParque. Ambos métodos reciben un parámetro de tipo
	 * String llamado puerta, que representa la puerta por la que un visitante entra
	 * o sale del parque.
	 * 
	 * @param puerta
	 */
	public abstract void entrarAlParque(String puerta);

	public abstract void salirDelParque(String puerta);
}
