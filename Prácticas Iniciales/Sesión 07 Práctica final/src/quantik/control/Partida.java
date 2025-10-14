package quantik.control;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import quantik.excepcion.CoordenadasIncorrectasException;
import quantik.modelo.Caja;
import quantik.modelo.GestorGrupos;
import quantik.modelo.Pieza;
import quantik.modelo.Tablero;
import quantik.util.Color;
import quantik.util.Figura;

/**
 * Clase Partida que contiene las reglas del juego y que inicia con un tablero y dos cajas ya creadas
 * e iniciadas.
 * 
 * @author Alberto Santos Martínez
 */
public class Partida {

	/** El tablero. */
	private Tablero tablero;
	
	/** La caja blanca. */
	private Caja cajaBlancas;
	
	/** La caja negra. */
	private Caja cajaNegras;
	
	/** El gestor de gestor de grupos. */
	private GestorGrupos gestor;
	
	/** Turno de la partida iniciado en nulo para que no haya ganado al inicio. */
	private Color turno = null;
	
	/** Contador de los turnos. */
	private int contadorTurno = 0;

	/**
	 * Constructor que guarda el tablero, cajablanca y cajanegra que le pasan por parámetro al igual que
	 * inicia el gestor de grupos y pone el turno a blanco.
	 *
	 * @param tablero el tablero inciciado
	 * @param cajaBlancas la caja blanca
	 * @param cajaNegras la caja negra
	 */
	public Partida(Tablero tablero, Caja cajaBlancas, Caja cajaNegras) {
		this.tablero = tablero;
		this.cajaBlancas = cajaBlancas;
		this.cajaNegras = cajaNegras;

		gestor = new GestorGrupos(tablero);
		turno = Color.BLANCO;
	}

	/**
	 * Método que cambia el turno actual a otro jugador.
	 */
	public void cambiarTurno() {
		if (turno == Color.BLANCO) {
			turno = Color.NEGRO;
		} else {
			turno = Color.BLANCO;
		}
	}
	
	/**
	 * Método que genera un clon en profundidad de la partida actual.
	 *
	 * @return the partida
	 */
	public Partida clonar() {
		Partida clon = new Partida(tablero.clonar(), consultarCajaBlancas().clonar(), consultarCajaNegras().clonar());
		clon.turno = this.turno;
		clon.contadorTurno = this.contadorTurno;
		return clon;
	}

	/**
	 * Método que coloca una pieza en el turno actual con las coordenadas pasadas por parámetro.
	 *
	 * @param fila la fila
	 * @param columna la columna
	 * @param figura la figura
	 */
	public void colocarPiezaEnTurnoActual(int fila, int columna, Figura figura) {
		if (tablero.estaEnTablero(fila, columna)) {
			try {
				if (tablero.consultarCelda(fila, columna).estaVacia()) {
					tablero.colocar(fila, columna, new Pieza(figura, turno));
					if (turno == Color.BLANCO)
						cajaBlancas.retirar(figura);
					if (turno == Color.NEGRO)
						cajaNegras.retirar(figura);
					contadorTurno++;
				}
			} catch (CoordenadasIncorrectasException e) {
				throw new RuntimeException("Error grave en el código.", e);
			}
		}
	}
	
	
	/**
	 * Consultar caja blancas.
	 *
	 * @return la caja blanca
	 */
	public Caja consultarCajaBlancas() {
		return cajaBlancas.clonar();
	}
	
	/**
	 * Consultar caja negras.
	 *
	 * @return la caja blanca
	 */
	public Caja consultarCajaNegras() {
		return cajaNegras.clonar();
	}

	/**
	 * Método que consulta el turno en el que se ha ganado la partida para averiguar quien es el ganador,
	 * tambien se comprueba si hay ganador o no, en caso de que no lo haya será nulo.
	 *
	 * @return el turno
	 */
	public Color consultarGanador() {

		if (hayAlgunGrupoCompleto()) 
			return turno;
		if (estaBloqueadoTurnoActual()) 
			return turno.obtenerContrario();

		return null;
	}

	/**
	 * Método que consulta en que turno se llega la partida.
	 *
	 * @return el contador de turno
	 */
	public int consultarNumeroJugada() {
		if (contadorTurno == 0)
			return contadorTurno++;
		return contadorTurno;
	}

	/**
	 * Consultar tablero.
	 *
	 * @return el tablero
	 */
	public Tablero consultarTablero() {
		return tablero.clonar();
	}

	/**
	 * Consultar turno.
	 *
	 * @return el turno
	 */
	public Color consultarTurno() {
		return turno;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partida other = (Partida) obj;
		return Objects.equals(cajaBlancas, other.cajaBlancas) && Objects.equals(cajaNegras, other.cajaNegras)
				&& contadorTurno == other.contadorTurno && Objects.equals(gestor, other.gestor)
				&& Objects.equals(tablero, other.tablero) && turno == other.turno;
	}

	/**
	 * Método que comprueba si una jugada es legal, para ello comprobamos que las coordenadas están dentro del tablero,
	 * si la celda en la que queremos colocar una figura está vacia, que la figura esté disponible en la caja del turno
	 * en el que estamos y si existe algún tipo de conflicto en esa celda.
	 *
	 * @param fila la fila
	 * @param columna la columna
	 * @param figura la figura
	 * @return true, en caso de que sea legal
	 */
	public boolean esJugadaLegalEnTurnoActual(int fila, int columna, Figura figura) {

		if (tablero.estaEnTablero(fila, columna) && tablero != null) {
			try {
				if (tablero.consultarCelda(fila, columna).estaVacia()) {
					if (cajaBlancas != null && cajaNegras != null) {
						if (turno == Color.BLANCO && cajaBlancas.estaDisponible(figura)
								|| turno == Color.NEGRO && cajaNegras.estaDisponible(figura)) {
							if (!gestor.hayConflictoEnGruposDeCelda(tablero.consultarCelda(fila, columna), figura, turno)) {
								return true;
							}
						}
					}
				}
			} catch (CoordenadasIncorrectasException e) {
				throw new RuntimeException("Error grave en el código.", e);
			}
		}
		return false;

	}

	/**
	 * Método que comprueba si la partida está acabada.
	 *
	 * @return true, en caso de que si que esté acabada la partida
	 */
	public boolean estaAcabadaPartida() {
		if (hayAlgunGrupoCompleto()) 
			return true;
		if (estaBloqueadoTurnoActual()) 
			return true;
		
		return false;
	}
	
	/**
	 * Método que comprueba si en el turno actual existe un bloqueo.
	 *
	 * @return true, en caso de que la pieza no se pueda colocar en ningún lado.
	 */
	public boolean estaBloqueadoTurnoActual() {
		List<Pieza> pieza;
		pieza = cajaNegras.consultarPiezasDisponibles();
		
		boolean flag = true;
		if (pieza != null) {
			for (int i = 0; i < pieza.size(); i++) {
				for (int j = 0; j < this.tablero.consultarNumeroFilas(); j++) {
					for (int k = 0; k < this.tablero.consultarNumeroColumnas(); k++) {
						if (esJugadaLegalEnTurnoActual(j, k, pieza.get(i).consultarFigura())) {
							flag = false;
						}
					}
				}
			}
		}
		return flag;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(cajaBlancas, cajaNegras, contadorTurno, gestor, tablero, turno);
	}
	
	/**
	 * Método que comprueba si existe algún grupo de los 12 que esté completo.
	 *
	 * @return true, en caso de que si lo haya
	 */
	public boolean hayAlgunGrupoCompleto() {
		if (gestor.hayGrupoGanador())
			return true;
		return false;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Partida [tablero=" + tablero + ", cajaBlancas=" + cajaBlancas + ", cajaNegras=" + cajaNegras
				+ ", gestor=" + gestor + ", turno=" + turno + ", contadorTurno=" + contadorTurno + "]";
	}
	
	
}
