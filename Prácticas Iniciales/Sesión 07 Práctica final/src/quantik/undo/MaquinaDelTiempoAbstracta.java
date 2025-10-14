package quantik.undo;

import java.util.Date;

/**
 * Clase MaquinaDelTiempoAbstracta donde se colocan los atributos y métodos comunes para 
 * los descendientes de la interfaz MecanismoDeshacer.
 * 
 * @author Alberto Santos Martínez
 */
public abstract class MaquinaDelTiempoAbstracta implements MecanismoDeDeshacer {

	/** La fecha. */
	private Date date;
	
	/**
	 * Constructor que inicaliza la fecha actual junto con las filas y columnas
	 * del tablero utilizado.
	 *
	 * @param date la fecha
	 * @param filas las filas
	 * @param columnas las columnas
	 */
	public MaquinaDelTiempoAbstracta(Date date, int filas, int columnas) {
		this.date = date;
	}
	
	@Override
	public Date obtenerFechaInicio() {
		return date;
	}
}
