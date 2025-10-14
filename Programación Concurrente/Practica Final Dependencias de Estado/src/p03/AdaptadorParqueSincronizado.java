package p03;

import p03.AdaptadorParqueSincronizado;

/**
 * Adaptador parque sincronizado
 * 
 * @author Alberto Santos Martínez
 * @author Jon Ander Incera Moreno
 *
 */
public class AdaptadorParqueSincronizado implements IParque {
	// inicializacion de atributo parque
	private IParque parque;

	private static AdaptadorParqueSincronizado instancia = new AdaptadorParqueSincronizado();

	/**
	 * Constructor privado que inicializa el objeto de la clase Parque utilizado por
	 * el adaptador.
	 */
	public AdaptadorParqueSincronizado() {
		parque = new Parque(50);
	}

	/**
	 * Método estático que devuelve la instancia única del adaptador de parque
	 * sincronizado.
	 * 
	 * @return instancia del adaptador de parque sincronizado.
	 */
	public static AdaptadorParqueSincronizado getInstancia() {
		return instancia;
	}

	/**
	 * Método sincronizado que permite la entrada de un visitante al parque a través
	 * de una puerta especificada.
	 * 
	 * @param puerta La puerta por la que el visitante va a entrar al parque.
	 */
	@Override
	public synchronized void entrarAlParque(String puerta) {
		parque.entrarAlParque(puerta);
	}

	/**
	 * Método sincronizado que permite la salida de un visitante del parque a través
	 * de una puerta especificada.
	 * 
	 * @param puerta La puerta por la que el visitante va a salir del parque.
	 */
	@Override
	public synchronized void salirDelParque(String puerta) {
		parque.salirDelParque(puerta);
	}
}