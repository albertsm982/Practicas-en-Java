package juego.control;

import juego.modelo.Color;
import juego.modelo.Jugador;
import juego.modelo.Tablero;

/**
 * Coordinador que ejecuta las acciones sobre el tablero.
 *
 * @author Alberto Santos Martínez
 */
public class ArbitroTresEnRaya {

	/** La constante NUM_GANADOR, indica cuantas veces tiene que ganar el jugador para ganar. */
	private static final int NUM_GANADOR = 3;
	
	/** EL juego ha acabado. */
	private boolean jugadorAcabado = false;
	
	/** jugador 1. */
	private Jugador jugador1;
	
	/** jugador 2. */
	private Jugador jugador2;
	
	/** turno del jugador. */
	private Jugador turno;
	
	/** el ganador. */
	private Jugador ganador;
	
	/** el tablero. */
	private Tablero tablero;
	
	/**
	 * Constructor.
	 */
	public ArbitroTresEnRaya() {
		tablero = new Tablero(NUM_GANADOR, NUM_GANADOR);
	}
	
	/**
	 * Método que instancia en orden los jugadores con el nombre
	 * que se recibe por parámetro.
	 *
	 * @param nombre el nombre del jugador
	 */
	public void registrarJugador(String nombre) {
		
		if(jugador1 == null) {
			jugador1 = new Jugador(nombre, Color.BLANCO);
		}else if(jugador2 == null) {
			jugador2 = new Jugador(nombre, Color.NEGRO);
			turno = jugador2;
		}
	}
	
	/**
	 * Método que devuelve el jugador al que le toca jugar.
	 *
	 * @return el turno del jugador
	 */
	public Jugador obtenerTurno() {
		return turno;
	}
	
	/**
	 * Método que devuelve el jugador ganador ua vez ha acabado el juego
	 * Si devuelve null y el juego ha acabado significa que el juego ha quedado en tablas.
	 *
	 * @return el jugador ganador
	 */
	public Jugador obtenerGanador() {
		return ganador;
	}
	
	/**
	 * Método que intenta poner una pieza del color del jugador turno, en la celda iniciada.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public void jugar (int x, int y) {
		
		if(esMovimientoLegal(x, y)) {
			obtenerTurno();
			//TODO
		}
		
	}
	
	/**
	 * Método que devuelve true si la celda a la que hacen referencia los indices pasados por
	 * parámetro está vacía.
	 *
	 * @param x the x
	 * @param y the y
	 * @return true, if successful
	 */
	public boolean esMovimientoLegal(int x, int y) {
		return tablero.estaEnTablero(x, y) && tablero.obtenerCelda(x, y).estaVacia();
	}
	
	/**
	 * Obtener tablero.
	 *
	 * @return the tablero
	 */
	public Tablero obtenerTablero() {
		return tablero;
		
	}
	
	/**
	 * Esta acabado.
	 *
	 * @return true, if successful
	 */
	public boolean estaAcabado() {
		return estaAcabado();
		
	}
	
}
