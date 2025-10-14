	package quantik.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import quantik.util.Color;
import quantik.util.Figura;

/**
 * Clase Grupo que hace referencia a cuatro celdas del tablero formando así un grupo.
 *
 * @author Alberto Santos Martínez
 */
public class Grupo {

	/** Lista de un grupo. */
	private List<Celda> grupo;

	/**
	 * Constructor de grupo que guarda unas celdas pasadas por parámetro.
	 *
	 * @param celdas las celdas
	 */
	public Grupo(List<Celda> celdas) {
		grupo = celdas;
	}

	/**
	 * Método clonar que devuelve un clon en profundidad del grupo actual.
	 *
	 * @return el clon de grupo
	 */
	public Grupo clonar() {
		Grupo clon = new Grupo(grupo);
		clon.grupo = new ArrayList<>(grupo.size());
		
		for (int i = 0; i < grupo.size(); i++) {
			if (grupo.get(i) != null) {
				clon.grupo.add(i, grupo.get(i).clonar());
			} else {
				clon.grupo.add(i, null);
			}
		}
		return clon;
	}
	
	/**
	 * Consultar número celdas.
	 *
	 * @return el número de celdas
	 */
	public int consultarNumeroCeldas() {
		return grupo.size();
	}

	/**
	 * Consultar numero piezas.
	 *
	 * @return el número de piezas
	 */
	public int consultarNumeroPiezas() {
		int contador = 0;
		for (int i = 0; i < consultarNumeroCeldas(); i++) {
			if (!grupo.get(i).estaVacia())
				contador++;
		}
		return contador;
	}

	/**
	 * Método que comprueba si una celda pasada por parámetro está contenida en el grupo actual.
	 *
	 * @param celdaABuscar celda que se busca
	 * @return true, en caso de que el grupo contenga la celda
	 */
	public boolean contieneCelda(Celda celdaABuscar) {
		for (int i = 0; i < consultarNumeroCeldas(); i++) {
			if (grupo.get(i).equals(celdaABuscar))
				return true;
		}
		return false;
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
		Grupo other = (Grupo) obj;
		return Objects.equals(grupo, other.grupo);
	}

	/**
	 * Método booleano que comprueba si las cuatro piezas que contiene el grupo son diferentes.
	 *
	 * @return true, en caso de que el grupo esté completado con figuras diferentes
	 */
	public boolean estaCompletoConFigurasDiferentes() {
		for (int i = 0; i < consultarNumeroCeldas(); i++) {
			int observador = 0;
			for (int j = 0; j < consultarNumeroCeldas(); j++) {
				if (!grupo.get(i).estaVacia() && !grupo.get(j).estaVacia()) {
					if (grupo.get(i).consultarPieza().consultarFigura() == 
							grupo.get(j).consultarPieza().consultarFigura()) {
						observador++;
						if (observador == 2) {
							return false;
						}
					}
				}else {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Método que comprueba si en las celdas del grupo existe alguna pieza que tenga la misma figura
	 * de color contrario al pasado por parámetro.
	 *
	 * @param figura la figura
	 * @param color el color
	 * @return true, en caso de que exista la misma pieza pero del color contrario
	 */
	public boolean existeMismaPiezaDelColorContrario(Figura figura, Color color) {
		for (int i = 0; i < consultarNumeroCeldas(); i++) {
			if (!grupo.get(i).estaVacia() && grupo.get(i).consultarPieza() != null) {
				if (grupo.get(i).consultarPieza().consultarColor() != color
						&& grupo.get(i).consultarPieza().consultarFigura() == figura) {
					return true;
				}
			}
		}
		return false;
	}


	/**
	 * Método Hash code generado automáticamente en Eclipse.
	 *
	 * @return el int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(grupo);
	}
	
	/**
	 * Método To string generado automáticamente en Eclipse.
	 *
	 * @return el string
	 */
	@Override
	public String toString() {
		return "Grupo [grupo=" + grupo + "]";
	}

}
