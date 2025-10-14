package quantik.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import quantik.util.Color;
import quantik.util.Figura;

/**
 * Clase Caja que inicia y almacena ocho piezas con un color pasado por parámetro.
 *
 * @author Alberto Santos Martínez
 */
public class Caja {

	/** Tamaño de la caja. */
	private static final int NUM_PIEZAS = 8;

	/** Lista de las piezas de la caja. */
	private List<Pieza> caja;
	
	/** Las figuras que hay en la clase Figuras. */
	private List<Figura> figuras = new ArrayList<>();
	
	/** El color. */
	private Color color;

	/**
	 * Método constructor de caja que crea una nueva lista de caja a partir
	 * de un color que le pasan por parámetro.
	 *
	 * @param color el color
	 */
	public Caja(Color color) {
		caja = new ArrayList<>(NUM_PIEZAS);
		this.color = color;
		int j = 0;
		int tamañoFiguras = Figura.values().length;
		for(int i = 0; i < tamañoFiguras; i++) {
			figuras.add(i, Figura.values()[i]);
		}
		for (int i = 0; i < NUM_PIEZAS; i++, j++) {
			caja.add(i, new Pieza(figuras.get(j), this.color));
			i++;
			caja.add(i, new Pieza(figuras.get(j), this.color));
		}
	}

	/**
	 * Método clonar que devuelve un clon en profundidad de la caja actual.
	 *
	 * @return la caja
	 */
	public Caja clonar() {
		Caja clon = new Caja(color);
		clon.caja = new ArrayList<Pieza>(NUM_PIEZAS);
		for (int i = 0; i < caja.size(); i++) {
			if (caja.get(i) != null) {
				clon.caja.add(i, caja.get(i).clonar()) ;
			}else {
				clon.caja.add(i, null);
			}
		}
		return clon;
	}

	/**
	 * Método consultar color.
	 *
	 * @return the color
	 */
	public Color consultarColor() {
		return color;
	}

	/**
	 * Método que devuelve un array con clones en profundidad de la piezas disponibles en la
	 * caja.
	 *
	 * @return lista de las piezas clonadas
	 */
	public List<Pieza> consultarPiezasDisponibles() { 
		List<Pieza> clon = null;
		int tamaño = contarPiezasActuales(), j = 0;
		if (tamaño == 0) {
			clon = new ArrayList<>(tamaño);
			return clon;
		}
		clon = new ArrayList<>(tamaño);
		for (int i = 0; i < caja.size(); i++) {
			if (caja.get(i) != null) {
				clon.add(j, caja.get(i).clonar());
				j++;
			}
		}
		return clon;
	}

	/**
	 * Método que cuente las piezas actuales que hay en la caja.
	 *
	 * @return el numero de piezas de la caja
	 */
	public int contarPiezasActuales() {
		int contador = 0;
		for (int i = 0; i < caja.size(); i++) {
			if (caja.get(i) != null)
				contador++;
		}
		return contador;
	}

	/**
	 * Método Equals generado automáticamente.
	 *
	 * @param obj el objeto
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
		Caja other = (Caja) obj;
		return Objects.equals(caja, other.caja) && color == other.color && Objects.equals(figuras, other.figuras);
	}


	
	/**
	 * Método que consulta si una figura pasada por parámetro está disponible en la caja.
	 *
	 * @param figura la figura
	 * @return true, en caso de que si que esté disponible, false, en caso contrario
	 */
	public boolean estaDisponible(Figura figura) {
		int contador = 0;
		for (int i = 0; i < caja.size(); i++) {
			if (caja.get(i) != null) {
				Figura comparador = caja.get(i).consultarFigura();
				if (comparador == figura)
					contador++;
			}
		}
		if (contador > 0)
			return true;
		return false;
	}

	/**
	 * Método Hash code generado automáticamente.
	 *
	 * @return el int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(caja, color, figuras);
	}

	/**
	 * Método que extrae una pieza con dicha figura en la caja. Si no hay una pieza disponible
	 * devuelve null.
	 *
	 * @param figura la figura pasada por parámetro
	 * @return la pieza extraida
	 */
	public Pieza retirar(Figura figura) {
		Pieza pieza = null;
		if (figura != null) {
			for (int i = 0; i < caja.size(); i++) {
				if (caja.get(i) != null && figura == caja.get(i).consultarFigura()) {
					pieza = caja.get(i);
					caja.remove(i);
					return pieza;
				}
			}
		}
		return pieza;
	}

	

	/**
	 * Método To string generado automáticamente.
	 *
	 * @return el string
	 */
	@Override
	public String toString() {
		return "Caja [caja=" + caja + ", figuras=" + figuras + ", color=" + color + "]";
	}
	

}
