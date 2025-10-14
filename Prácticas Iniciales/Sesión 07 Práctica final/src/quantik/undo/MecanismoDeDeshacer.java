package quantik.undo;

import java.util.Date;

import quantik.control.Partida;
import quantik.excepcion.CoordenadasIncorrectasException;
import quantik.util.Color;
import quantik.util.Figura;

/**
 * Interfaz del MecanismoDeDeshacer, la cual contiene todos los métodos que se heredarán a las clases
 * del paquete.
 * 
 * @author Alberto Santos Martínez
 */
public interface MecanismoDeDeshacer {
	
	/**
	 * Método que consulta el numero jugadas que se han realizado.
	 *
	 * @return el numero de jugadas
	 */
	public int consultarNumeroJugadasEnHistorico();
	
	/**
	 * Método que obtiene la fecha inicio.
	 *
	 * @return la fecha
	 */
	public Date obtenerFechaInicio();
	
	/**
	 * Recibe la última jugada realizada y guarda sus efectos.
	 *
	 * @param fila la fila
	 * @param columna la columna
	 * @param figura la figura
	 * @param color el color
	 * @throws CoordenadasIncorrectasException Excepcion CoordenadasIncorrectasException
	 */
	public void hacerJugada(int fila, int columna, Figura figura, Color color) 
			throws CoordenadasIncorrectasException;
	
	/**
	 * Deshace la última jugada.
	 */
	public void deshacerJugada();
	
	/**
	 * Método que devuelve un clon de la partida actual.
	 *
	 * @return la partida actual
	 */
	public Partida consultarPartidaActual();
	

}
