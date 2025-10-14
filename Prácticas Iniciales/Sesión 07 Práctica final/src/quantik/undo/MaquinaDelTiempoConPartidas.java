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
 * Clase MaquinaDelTiempoConPartidas que alamacena el historico de partidas en una lista genérica
 * la cual se va modificando cada vez que se van haciendo jugadas a partir de una partida
 * inicial sin piezas colocadas.
 * 
 * @author Alberto Santos Martínez
 */
public class MaquinaDelTiempoConPartidas extends MaquinaDelTiempoAbstracta {

	/** Lista de partidas. */
	private List<Partida> partidas;

	/**
	 * Constructor de MaquinaDelTiempoConPartidas que hereda los atributos e inicializa la lista
	 * de las partidas, un tablero y las dos acajas con sus respectivos colores y las añade a la lista
	 * de partidas.
	 *
	 * @param date la fecha
	 * @param filas las filas
	 * @param columnas las columnas
	 */
	public MaquinaDelTiempoConPartidas(Date date, int filas, int columnas) {
		super(date, filas, columnas);
		partidas = new ArrayList<>();

		Tablero tablero = new Tablero();
		Caja cajaBlanca = new Caja(Color.BLANCO);
		Caja cajaNegras = new Caja(Color.NEGRO);

		partidas.add(new Partida(tablero, cajaBlanca, cajaNegras));
	}

	@Override
	public int consultarNumeroJugadasEnHistorico() {
		return partidas.size() - 1;
	}
	
	@Override
	public void hacerJugada(int fila, int columna, Figura figura, Color color) throws CoordenadasIncorrectasException {
		Partida clon = consultarPartidaActual().clonar();
		
		if (!clon.estaBloqueadoTurnoActual() || !clon.estaAcabadaPartida()) {
			if (clon.consultarTablero().estaEnTablero(fila, columna)) {
				if (clon.esJugadaLegalEnTurnoActual(fila, columna, figura)) {
					clon.colocarPiezaEnTurnoActual(fila, columna, figura);
					partidas.add(clon);
					clon.cambiarTurno();
				}
			} else {
				throw new CoordenadasIncorrectasException();
			}
		}
	}

	@Override
	public void deshacerJugada() {
		if (consultarNumeroJugadasEnHistorico() != 0) {
			partidas.remove(consultarPartidaActual());
		}
	}
	
	@Override
	public Partida consultarPartidaActual() {
		return partidas.get(consultarNumeroJugadasEnHistorico());
	}
}
