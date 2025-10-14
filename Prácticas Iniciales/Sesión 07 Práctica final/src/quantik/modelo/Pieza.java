package quantik.modelo;

import java.util.Objects;

import quantik.util.Color;
import quantik.util.Figura;

/**
 * Clase Pieza que se incia con una figura y un color.
 *
 * @author Alberto Santos Martínez
 */
public class Pieza {

	/** La figura. */
	private Figura figura;
	
	/** El color. */
	private Color color;

	/**
	 * Método constructor que inicializa una pieza con un tipo de figura y un color.
	 *
	 * @param figura el tipo de figura
	 * @param color el color de la figura
	 */
	public Pieza(Figura figura, Color color) {
		this.figura = figura;
		this.color = color;

	}

	/**
	 * Devuelve la representación de la pieza en modo texto, usado para dibujar las
	 * piezas en el tablero en modo texto.
	 *
	 * @return the string
	 */
	public String aTexto() {
		return figura.aTexto() + color.toChar();
	}

	/**
	 * Metodo clonar que devuelve un clon en profundidad de la pieza actual.
	 *
	 * @return la pieza clonada
	 */
	public Pieza clonar() {
		return new Pieza(figura, color);
	}
	
	/**
	 * Consultar color.
	 *
	 * @return el color
	 */
	public Color consultarColor() {
		return color;
	}
	
	/**
	 * Consultar figura.
	 *
	 * @return la figura
	 */
	public Figura consultarFigura() {
		return figura;
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
		Pieza other = (Pieza) obj;
		return color == other.color && figura == other.figura; //this.hashCode() == obj.hashCode();
	}
	
	/**
	 * Método Hash Code generado automáticamente en Eclipse.
	 *
	 * @return el entero
	 */
	@Override
	public int hashCode() {
		return Objects.hash(color, figura);
	}
	
	/**
	 * Método toString generado automáticamente en Eclipse.
	 *
	 * @return el string
	 */
	@Override
	public String toString() {
		return "Pieza [figura=" + figura + ", color=" + color + "]";
	}

}
