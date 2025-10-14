package quantik.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import quantik.excepcion.CoordenadasIncorrectasException;

/**
 * Clase Tablero que crea un tablero que es un array de dos dimensiones debido a
 * que tiene una fila y comlumna por cada posición. En cada posición del tablero
 * habrá una celda que puede contener una figura.
 *
 * @author Alberto Santos Martínez
 */
public class Tablero {

	/** Número de filas y columnas. */
	private static final int NUM_FILAS_COLUMNAS = 4;

	/** La lista de la matriz. */
	private List<List<Celda>> matriz;

	/**
	 * Constructor de tablero rellena el tablero con celdas.
	 */
	public Tablero() {
		matriz = new ArrayList<>(NUM_FILAS_COLUMNAS);
		for (int i = 0; i < NUM_FILAS_COLUMNAS; i++) {
			matriz.add(new ArrayList<>(NUM_FILAS_COLUMNAS));
		}
		for (int i = 0; i < NUM_FILAS_COLUMNAS; i++) {
			for (int j = 0; j < NUM_FILAS_COLUMNAS; j++) {
				matriz.get(i).add( new Celda(i, j));
			}
		}
	}

	/**
	 * Método aTexto que trasforma el tablero junto con su contenido en un string
	 * de texto.
	 *
	 * @return tablero en formato texto.
	 */
	public String aTexto() {
		String texto = "\t";
		for (int i = 0; i < NUM_FILAS_COLUMNAS; i++) {
			texto += i + "\t";
		}
		texto += "\n";

		for (int i = 0; i < NUM_FILAS_COLUMNAS; i++) {
			texto += i + "\t";
			for (int j = 0; j < NUM_FILAS_COLUMNAS; j++) {
				if (matriz.get(i).get(j).estaVacia())
					texto += "-----\t";
				else
					texto += "-" + matriz.get(i).get(j).consultarPieza().aTexto() + "-\t";
			}
			texto += "\n";
		}
		return texto;
	}

	/**
	 * Método clonar que devuelve un clon del tablero en profundidad.
	 *
	 * @return clon clon del tablero
	 */
	public Tablero clonar()  {
		Tablero clon = new Tablero();
		for (int i = 0; i < NUM_FILAS_COLUMNAS; i++) {
			for (int j = 0; j < NUM_FILAS_COLUMNAS; j++) {
				if (!matriz.get(i).get(j).estaVacia()) {
						try {
							clon.colocar(i, j, matriz.get(i).get(j).consultarPieza());
						} catch (CoordenadasIncorrectasException e) {
							throw new RuntimeException("Error grave en el código.", e);
						}
						
				}
			}
		}
		return clon;
	}

	/**
	 * Método que coloca una pieza en la posición del tablero pasada por parámetro.
	 *
	 * @param fila    la fila
	 * @param columna la columna
	 * @param pieza   la pieza a colocar
	 * @throws CoordenadasIncorrectasException Excepcion CoordenadasIncorrectasException
	 */
	public void colocar(int fila, int columna, Pieza pieza) throws CoordenadasIncorrectasException {
		if (estaEnTablero(fila, columna)) {
			if (matriz.get(fila).get(columna).estaVacia()) {
				matriz.get(fila).get(columna).colocar(pieza);
			}
		} else {
			throw new CoordenadasIncorrectasException();
		}
	}

	/**
	 * Consultar celda.
	 *
	 * @param fila    la fila
	 * @param columna la columna
	 * @return la celda
	 * @throws CoordenadasIncorrectasException Excepcion CoordenadasIncorrectasException
	 */
	public Celda consultarCelda(int fila, int columna) throws CoordenadasIncorrectasException {

		if (estaEnTablero(fila, columna)) {
			return matriz.get(fila).get(columna).clonar();
		} else {
			throw new CoordenadasIncorrectasException();
		}

	}

	/**
	 * Consultar numero columnas.
	 *
	 * @return el numero de columnas
	 */
	public int consultarNumeroColumnas() {
		return NUM_FILAS_COLUMNAS;

	}

	/**
	 * Consultar numero filas.
	 *
	 * @return el numero de filas
	 */
	public int consultarNumeroFilas() {
		return NUM_FILAS_COLUMNAS;

	}

	/**
	 * Método Equals generado automáticamente en Eclipse.
	 *
	 * @param obj el obj
	 * @return true, en caso satisfactorio
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tablero other = (Tablero) obj;
		return Objects.equals(matriz, other.matriz);
	}

	/**
	 * Método que comprueba si las filas y columnas pasadas por parámetros
	 * pertenecen al tablero.
	 *
	 * @param fila    la fila
	 * @param columna la columna
	 * @return true, en caso de que tanto la fila como la columna pertenezcan al
	 *         tablero
	 */
	public boolean estaEnTablero(int fila, int columna) {
		if (fila >= 0 && columna >= 0 && fila <= NUM_FILAS_COLUMNAS - 1 && columna <= NUM_FILAS_COLUMNAS - 1)
			return true;
		return false;
	}

	/**
	 * Método Hash Code generado automáticamente en Eclipse.
	 *
	 * @return el int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(matriz);
	}

	/**
	 * Devuelve la referencia a la celda con las coordenadas indicadas, en caso de
	 * que no esté en el tablero, se devolverá un valor null.
	 *
	 * @param fila    la fila
	 * @param columna la columna
	 * @return la celda contenida en dichas filas y columnas, en caso de que no
	 *         pertenezca al tablero, se devolverá un valor nulo
	 * @throws CoordenadasIncorrectasException Excepcion CoordenadasIncorrectasException
	 */
	Celda obtenerCelda(int fila, int columna) throws CoordenadasIncorrectasException {
		if (estaEnTablero(fila, columna)) {
			Celda laCelda = matriz.get(fila).get(columna);
			return laCelda;
		} else {
			throw new CoordenadasIncorrectasException();
		}
	}

	/**
	 * Método To string generado automáticamente en Eclipse.
	 *
	 * @return el string
	 */
	@Override
	public String toString() {
		return "Tablero [matriz=" + matriz + "]";
	}

}
