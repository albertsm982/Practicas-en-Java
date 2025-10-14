package quantik.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import quantik.excepcion.CoordenadasIncorrectasException;
import quantik.util.Color;
import quantik.util.Figura;

/**
 * Clase GestorGrupos que agrupa todos los grupos en un mismo array.
 * 
 * @author Alberto Santos Martínez
 */
public class GestorGrupos {

	/** Lista de los grupos. */
	private List<Grupo> grupo;

	/** Tamaño de la lista. */
	private final static int TAMAÑO = 12;

	/**
	 * Constructor de GestorGrupos que en una llamada usa varios metodos privados
	 * para llenar la lista de grupo.
	 *
	 * @param tablero el tablero empezado
	 */
	public GestorGrupos(Tablero tablero) {
		grupo = new ArrayList<>(TAMAÑO);
		int counter = 0;

		counter = gestorGrupoFilas(counter, grupo, tablero);
		counter = gestorGrupoColumnas(counter, grupo, tablero);
		counter = gestorGrupoZonasCuadradasIzquierda(counter, grupo, tablero);
		counter = gestorGrupoZonasCuadradasDerecha(counter, grupo, tablero);
	}

	/**
	 * Añade a la lista grupo las celdas que crean 4 grupos con sus filas.
	 *
	 * @param counter contador para guardar los distintos grupos en distintas
	 *                posiciones
	 * @param grupo  la lista del grupo
	 * @param tablero el tablero iniciado
	 * @return el contador para que no se pierda el progreso
	 */
	private int gestorGrupoFilas(int counter, List<Grupo> grupo, Tablero tablero) {
		for (int i = 0; i < tablero.consultarNumeroFilas(); i++) { // Filas
			List<Celda> celdas = new ArrayList<>(4);
			for (int o = 0; o < tablero.consultarNumeroColumnas(); o++) {
					try {
						celdas.add(o, tablero.obtenerCelda(i, o));
					} catch (CoordenadasIncorrectasException e) {
						throw new RuntimeException("Error grave en el código.", e);
					}
			}
			grupo.add(counter, new Grupo(celdas));
			counter++;
		}
		return counter;
	}

	/**
	 * Añade a la lista grupo las celdas que crean 4 grupos con sus columnas.
	 *
	 * @param counter contador para guardar los distintos grupos en distintas
	 *                posiciones
	 * @param grupo  la lista del grupo
	 * @param tablero el tablero iniciado
	 * @return el contador para que no se pierda el progreso
	 */
	private int gestorGrupoColumnas(int counter, List<Grupo> grupo, Tablero tablero) {
		for (int i = 0; i < tablero.consultarNumeroFilas(); i++) { // Columnas
			List<Celda> celdas = new ArrayList<>(4);
			for (int j = 0; j < tablero.consultarNumeroColumnas(); j++) {
					try {
						celdas.add(j, tablero.obtenerCelda(j, i));
					} catch (CoordenadasIncorrectasException e) {
						throw new RuntimeException("Error grave en el código.", e);
					}
			}
			grupo.add(counter, new Grupo(celdas));
			counter++;
		}
		return counter;
	}

	/**
	 * Añade a la lista grupo las celdas que crean dos grupos con los cuadrados de la izquierda.
	 *
	 * @param counter contador para guardar los distintos grupos en distintas
	 *                posiciones
	 * @param grupo  la lista del grupo
	 * @param tablero el tablero iniciado
	 * @return el contador para que no se pierda el progreso
	 */
	private int gestorGrupoZonasCuadradasIzquierda(int counter, List<Grupo> grupo, Tablero tablero){
		int o = -1, l = -1;
		List<Celda> celdas = new ArrayList<>(4);
		List<Celda> lasCeldas = new ArrayList<>(4);
		for (int i = 0; i < tablero.consultarNumeroFilas() / 2; i++) { // Tabla superior izquierda
			for (int j = 0; j < tablero.consultarNumeroColumnas() / 2; j++) {
				o++;
					try {
						celdas.add(o, tablero.obtenerCelda(i, j));
					} catch (CoordenadasIncorrectasException e) {
						throw new RuntimeException("Error grave en el código.", e);
					}
			}
			for (int k = tablero.consultarNumeroColumnas() / 2; k < tablero.consultarNumeroColumnas(); k++) { // Tabla
																												// inferior
																												// izquierda
				l++;
					try {
						lasCeldas.add(l, tablero.obtenerCelda(i, k));
					} catch (CoordenadasIncorrectasException e) {
						throw new RuntimeException("Error grave en el código.", e);
					}
			}
		}
		grupo.add(counter, new Grupo(celdas));
		counter++;
		grupo.add(counter, new Grupo(lasCeldas));
		counter++;
		return counter;
	}

	/**
	 * Añade a la lista grupo las celdas que crean dos grupos con los cuadrados de la derecha.
	 *
	 * @param counter contador para guardar los distintos grupos en distintas
	 *                posiciones
	 * @param grupo  la lista del grupo
	 * @param tablero el tablero iniciado
	 * @return el contador para que no se pierda el progreso
	 */
	private int gestorGrupoZonasCuadradasDerecha(int counter, List<Grupo> grupo, Tablero tablero) {
		int m = -1, n = -1;
		List<Celda> celdas = new ArrayList<>(4);
		List<Celda> lasCeldas = new ArrayList<>(4);
		for (int i = tablero.consultarNumeroFilas() / 2; i < tablero.consultarNumeroFilas(); i++) { // Tabla superior
																									// derecha
			for (int j = 0; j < tablero.consultarNumeroColumnas() / 2; j++) {
				m++;
				try {
					celdas.add(m, tablero.obtenerCelda(i, j));
				} catch (CoordenadasIncorrectasException e) {
					throw new RuntimeException("Error grave en el código.", e);
				}
			}
			for (int k = tablero.consultarNumeroColumnas() / 2; k < tablero.consultarNumeroColumnas(); k++) {// Tabla
																												// inferior
																												// derecha
				n++;
				try {
					lasCeldas.add(n, tablero.obtenerCelda(i, k));
				} catch (CoordenadasIncorrectasException e) {
					throw new RuntimeException("Error grave en el código.", e);
				}
			}
		}
		grupo.add(counter, new Grupo(celdas));
		counter++;
		grupo.add(counter, new Grupo(lasCeldas));
		counter++;
		return counter;
	}

	/**
	 * Método que comprueba si hay algún posible conflico en alguno de los grupos a
	 * los que pertece la celda con relación a la figura y el color pasado por
	 * parámetro.
	 *
	 * @param celda  la celda
	 * @param figura la figura
	 * @param turno  el turno
	 * @return true, en caso de que la celda no estuviera vacía, estuviera ya
	 *         completa con figuras diferentes o en caso de que exista ya una misma
	 *         pieza del color contrario en ese grupo
	 */
	public boolean hayConflictoEnGruposDeCelda(Celda celda, Figura figura, Color turno) {
		for (int i = 0; i < TAMAÑO; i++) {
			if (grupo.get(i).contieneCelda(celda)) {
				if (!celda.estaVacia()) {
					return true;
				} else if (grupo.get(i).estaCompletoConFigurasDiferentes()) {
					return true;
				} else if (grupo.get(i).existeMismaPiezaDelColorContrario(figura, turno)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Método que comprueba si hay un ganador en la partida.
	 *
	 * @return true, en caso de que haya un grupo lleno con figuras diferentes
	 */
	public boolean hayGrupoGanador() {
		for (int i = 0; i < TAMAÑO; i++) {
			if (grupo.get(i).estaCompletoConFigurasDiferentes())
				return true;
		}
		return false;
	}

	/**
	 * Método que obtiene los grupos en los que está la celda pasada por parámetro.
	 *
	 * @param celda la celda
	 * @return el grupo con el listado de los grupos en los que está la celda
	 */
	public List<Grupo> obtenerGruposConteniendoCelda(Celda celda) {
		List<Grupo> listadoGruposCelda = new ArrayList(3);
		int contador = 0;
		for (int i = 0; i < TAMAÑO; i++) {
			if (grupo.get(i) != null && grupo.get(i).contieneCelda(celda)) {
				listadoGruposCelda.add(contador, grupo.get(i));
				contador++;
			}
		}
		return listadoGruposCelda;
	}

	/**
	 * Método Hash code generado automáticamente en Eclipse.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(grupo);
	}

	/**
	 * Método Equals generado automáticamente en Eclipse.
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
		GestorGrupos other = (GestorGrupos) obj;
		return Objects.equals(grupo, other.grupo);
	}

	/**
	 * Método To string generado automáticamente en Eclipse.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "GestorGrupos [grupo=" + grupo + "]";
	}


	
}
