package quantik.undo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import quantik.control.Partida;
import quantik.excepcion.CoordenadasIncorrectasException;
import quantik.modelo.Caja;
import quantik.modelo.Tablero;
import quantik.util.Color;
import quantik.util.Figura;

/**
 * Clase MaquinaDelTiempoConJugadas que almacena el historico de las jugadas realizadas
 * en una lista genérica según se vayan realizando.
 * 
 * @author Alberto Santos Martínez
 */
public class MaquinaDelTiempoConJugadas extends MaquinaDelTiempoAbstracta {

	/** Lista de las jugadas. */
	private List<Jugada> jugadas;
	
	/** Las filas. */
	private int filas;
	
	/** Las columnas. */
	private int columnas;
	
	/**
	 * Constructor de MaquinaDelTiempoConJugadas que hereda los atributos e inicializa la lista
	 * de las jugadas, las filas y las columnas.
	 *
	 * @param date la fecha
	 * @param filas las filas
	 * @param columnas las columnas
	 */
	public MaquinaDelTiempoConJugadas(Date date, int filas, int columnas) {
		super(date, filas, columnas);
		jugadas = new ArrayList<>();
		
		this.filas = filas;
		this.columnas = columnas;
	}

	@Override
	public int consultarNumeroJugadasEnHistorico() {
		return jugadas.size();
	}

	@Override
	public void hacerJugada(int fila, int columna, Figura figura, Color color) throws CoordenadasIncorrectasException {
		if(fila >= filas || columna >= columnas || fila < 0 || columna < 0)
			throw new CoordenadasIncorrectasException();
		else
			jugadas.add(new Jugada(fila, columna, figura, color));
	}

	@Override
	public void deshacerJugada() {
		if(consultarNumeroJugadasEnHistorico() != 0)
			jugadas.remove(consultarNumeroJugadasEnHistorico() - 1);
	}

	@Override
	public Partida consultarPartidaActual() {
		Tablero tablero = new Tablero();
		Caja cajaBlanca = new Caja(Color.BLANCO);
		Caja cajaNegras = new Caja(Color.NEGRO);
		Partida partida = new Partida(tablero, cajaBlanca, cajaNegras);
		
		for(int i = 0; i < jugadas.size() ;i++ ) {
			partida.colocarPiezaEnTurnoActual(jugadas.get(i).consultarFila(), jugadas.get(i).consultarColumna(), jugadas.get(i).consultarFigura());
			partida.cambiarTurno();
		}
		return partida;
	}
	
}
