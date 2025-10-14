package quantik.modelo;

import java.util.Objects;

/**
 * Clase Celda que se inicia vacia y sin pieza, esta contendrá una pieza de un color.
 *
 * @author Alberto Santos Martínez
 */
public class Celda {

	/** La fila de la celda. */
	private int fila;
	
	/** La columna de la celda. */
	private int columna;
	
	/** La pieza que contendrá la celda. */
	private Pieza pieza;
	
	/**
	 * Método constructor de celda que inicializa la celda con una fila y columna.
	 *
	 * @param fila la fila
	 * @param columna la columna
	 */
	public Celda(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
	}
	
	/**
	 * Método Clonar que clona una celda en profundidad, en caso de que no esté vacia coloca en el clon la pieza
	 * que esté en la celda.
	 *
	 * @return la celda
	 */
	public Celda clonar() {
		
		Celda clon = new Celda(fila,columna);
		if(!estaVacia()) {
			clon.colocar(consultarPieza());
		}
		return clon;
	}
	
	/**
	 * Coloca la pieza en una celda.
	 *
	 * @param pieza la pieza
	 */
	public void colocar(Pieza pieza) {
		this.pieza = pieza;
	}
	
	/**
	 * Consultar columna.
	 *
	 * @return la columna
	 */
	public int consultarColumna() {
		return columna;
	}
	
	/**
	 * Consultar fila.
	 *
	 * @return la fila
	 */
	public int consultarFila() {
		return fila;
	}
	
	/**
	 * Consultar pieza.
	 *
	 * @return la pieza
	 */
	public Pieza consultarPieza() {
		if(estaVacia())
			return null;
		return pieza.clonar();	
	}

	/**
	 * Método equals generado automáticamente en Eclipse.
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
		Celda other = (Celda) obj;
		return columna == other.columna && fila == other.fila;
	}
	
	/**
	 * Método que comprueba si la celda contiene una figura o no, es decir, si está vacia.
	 *
	 * @return true en caso de que esté vacia, false en caso contrario
	 */
	public boolean estaVacia() {
		return pieza == null;
	}
	
	/**
	 * Método Hash Code generado automáticamente en Eclipse.
	 *
	 * @return el entero
	 */
	@Override
	public int hashCode() {
		return Objects.hash(columna, fila);
	}

	/**
	 * Método string generado automáticamente en Eclipse.
	 *
	 * @return el string
	 */
	@Override
	public String toString() {
		return "Celda [fila=" + fila + ", columna=" + columna + "]";
	}

	
	
	
	
}
