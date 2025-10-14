package es.ubu.inf.edat.pr05;

import java.util.*;

/**
 * Clase ArbolBB que contiene la implementación de un Arbol de Busqueda Binaria
 * que logra que a partir de la clase extendida AbstractSet se consiga que no
 * haya ningún elemento duplicado en el árbol.
 *
 * @param <E> tipo de los elementos del árbol
 * @author Alberto Santos Martínez
 */
public class ArbolBB<E> extends AbstractSet<E> {

	/**
	 * Clase anidada Nodo que forma parte de la clase principal permitiendo definir
	 * los elementos del árbol.
	 */
	private class Nodo {

		/** El dato a almacenar. */
		private E dato;

		/** La referencia al sub-arbol a la izquierda. */
		private Nodo izquierda;

		/** La referencia al sub-arbol a la derecha. */
		private Nodo derecha;

		/**
		 * Método constructor de la clase Nodo que define el dato pasado por parámetro.
		 *
		 * @param dato el dato
		 */
		public Nodo(E dato) {
			this.dato = dato;
		}

	} // Fin de la clase anidada Nodo

	/** Referencia al nodo raiz del árbol. */
	private Nodo raiz = null;

	/** Número de elementos. */
	protected int elementos = 0;

	/** El comparador de tipo comparator. */
	private Comparator<? super E> comparator;

	/**
	 * Constructor vació de la clase.
	 */
	public ArbolBB() {
		comparator = null;
	}

	/**
	 * Constructor con un comparator que permite definir el orden de datos
	 * internamente.
	 *
	 * @param comparator el comparator
	 */
	public ArbolBB(Comparator<? super E> comparator) {
		this.comparator = comparator;
	}

	/**
	 * Constructor con una collección que permite definir el contenido del set.
	 *
	 * @param collection la colección
	 */
	public ArbolBB(Collection<? extends E> collection) {
		comparator = null;
		this.addAll(collection);
	}

	/**
	 * Constructor que reciba un objeto de una clase que implemente Comparator (para
	 * definir el orden que se debe observar en los elementos insertados) y una
	 * Collection, para indicar el contenido del conjunto.
	 *
	 * @param coleccion  la coleccion
	 * @param comparator el comparator
	 */
	public ArbolBB(Collection<? extends E> coleccion, Comparator<? super E> comparator) {
		this.comparator = comparator;
		this.addAll(coleccion);
	}

	/**
	 * Método Add que añade el elemento especifico al arbol binario.
	 * 
	 * @param elemento el elemento que se desea añadir
	 * @return true, en caso de que se añada satisfactoriamente
	 */
	@Override
	public boolean add(E elemento) {
		if (raiz == null) { // En caso de que no haya raíz
							// el nodo a añadir será la raiz
			raiz = new Nodo(elemento);
			return true;
		} else
			return insertar(raiz, elemento);
	}

	/**
	 * Método privado insertar que añade el elemento especificado al arbol binario
	 * si es mayor se añadirá hacia la derecha, en caso contrario a la izquierda. En
	 * caso de que haya un elemento a la izquierda o derecha del nodo con el que se
	 * ha comparado, se volverá a realizar la comparación.
	 *
	 * @param nodo     el elemento por el se compara el elemento a añadir
	 * @param elemento el elemento a añadir
	 * @return true, en caso de que se añada satisfactoriamente
	 */
	private boolean insertar(Nodo nodo, E elemento) {
		if (obtenerComparacion(elemento, nodo.dato) < 0) {
			if (nodo.izquierda == null) {
				nodo.izquierda = new Nodo(elemento);
				return true;
			} else
				return insertar(nodo.izquierda, elemento);

		} else if (obtenerComparacion(elemento, nodo.dato) > 0) {
			if (nodo.derecha == null) {
				nodo.derecha = new Nodo(elemento);
				return true;
			} else
				return insertar(nodo.derecha, elemento);
		}

		return false;
	}

	/**
	 * Método contains que analiza si el árbol binario contiene un elemento pasado
	 * por parámetro.
	 *
	 * @param objeto el elemento a buscar
	 * @return true, en caso de que el objeto se encuentre en el árbol binario
	 */
	@Override
	public boolean contains(Object objeto) {
		if (raiz == null)
			return false;

		@SuppressWarnings("unchecked")
		E elemento = (E) objeto;

		return buscar(raiz, elemento);
	}

	/**
	 * Método privado buscar que es recursivo debido a que llama al método hasta que
	 * acabe de buscar en el elemento pasado por parámetro.
	 *
	 * @param nodo     el nodo a comparar
	 * @param elemento el elemento a buscar
	 * @return true, en caso de que se encuentre el elemento en el árbol
	 */
	private boolean buscar(Nodo nodo, E elemento) {
		if (obtenerComparacion(elemento, nodo.dato) < 0)
			return buscar(nodo.izquierda, elemento);
		else if (obtenerComparacion(elemento, nodo.dato) > 0)
			return buscar(nodo.derecha, elemento);

		return true;
	}

	/**
	 * Método remove que elimina un elemento pasado por parámetro del árbol binario.
	 * Busca el elemento hasta encontrar a uno con el mismo valor y lo elimina.
	 * 
	 * @param objeto el elemento a borrar
	 * @return true, en caso de que se borre
	 */
	@Override
	public boolean remove(Object objeto) {
		if (raiz == null)
			return false;
		@SuppressWarnings("unchecked")
		E elemento = (E) objeto;
		Nodo padre = null;
		Nodo actual = raiz;

		while (actual != null) {
			if (obtenerComparacion(elemento, actual.dato) == 0) {
				eliminarNodo(actual, padre);
				return true;
			}
			padre = actual;
			actual = (obtenerComparacion(elemento, actual.dato) < 0) ? actual.izquierda : actual.derecha;
		}
		return false;
	}

	/**
	 * Método eliminarNodo auxiliar de remove que consigue eliminar el nodo y
	 * recolocar el árbol binario.
	 *
	 * @param nodo  el nodo a borrar
	 * @param padre el nodo padre del nodo a borrar
	 */
	private void eliminarNodo(Nodo nodo, Nodo padre) {
		if (nodo.derecha == null)
			reemplazarNodo(padre, nodo, nodo.izquierda);
		else if (nodo.izquierda == null)
			reemplazarNodo(padre, nodo, nodo.derecha);
		else {
			Nodo sucesor = encontrarSucesor(nodo);
			reemplazarNodo(sucesor, sucesor, sucesor.derecha);
			sucesor.izquierda = nodo.izquierda;
			if (sucesor != nodo.derecha)
				sucesor.derecha = nodo.derecha;
			reemplazarNodo(padre, nodo, sucesor);
		}
	}

	/**
	 * Método encontrarSucesor auxiliar de eliminarNodo que ecuentra el sucesor del
	 * nodo padre del borrado en el árbol binario.
	 *
	 * @param nodo el nodo del que buscamos un sucesor
	 * @return el nodo sucesor
	 */
	private Nodo encontrarSucesor(Nodo nodo) {
		Nodo sucesor = nodo;
		Nodo actual = nodo.derecha;

		while (actual != null) {
			sucesor = actual;
			actual = actual.izquierda;
		}

		return sucesor;
	}

	/**
	 * Método reemplazarNodo auxilar de eliminarNodo que reemplaza el nodo viejo por
	 * uno nuevo.
	 *
	 * @param padre     el nodo padre
	 * @param nodoViejo el nodo viejo a ser reemplazado
	 * @param nodoNuevo el nuevo nodo que reemplaza al anterior
	 */
	private void reemplazarNodo(Nodo padre, Nodo nodoViejo, Nodo nodoNuevo) {
		if (padre == null)
			raiz = nodoNuevo;
		else if (padre.izquierda == nodoViejo)
			padre.izquierda = nodoNuevo;
		else
			padre.derecha = nodoNuevo;
	}

	/**
	 * Método Iterator que redifine el método iterator de la clase.
	 *
	 * @return el iterator
	 */
	@Override
	public Iterator<E> iterator() {
		List<E> elementos = new ArrayList<>();
		inOrden(raiz, elementos);
		return elementos.iterator();
	}

	/**
	 * Método recursivo inOrden que añade a una lista los elementos en orden uno por
	 * uno.
	 *
	 * @param nodo      el nodo a ser visitado
	 * @param elementos la lista de los elementos en orden
	 */
	private void inOrden(Nodo nodo, List<E> elementos) {
		if (nodo != null) {
			inOrden(nodo.izquierda, elementos);
			elementos.add(nodo.dato);
			inOrden(nodo.derecha, elementos);
		}
	}

	/**
	 * Redifiniación del método size.
	 *
	 * @return el tamaño de la lista.
	 */
	@Override
	public int size() {
		List<E> elementos = new ArrayList<>();
		inOrden(raiz, elementos);
		return elementos.size();
	}

	/**
	 * Método altura que devuelve la altura del elemento pasado por parámetro en el
	 * árbol binario.
	 *
	 * @param elemento el elemento del que se quiere su altura
	 * @return la alutra del elemento en el árbol binario
	 */
	public int altura(E elemento) {
		if (raiz == null)
			return -1;
		return calcularAltura(raiz, elemento, 0);
	}

	/**
	 * Método recursivo calcularAtura auxiliar de altura que calcula la altura del
	 * elemento en la lista binaria.
	 *
	 * @param nodo         el nodo a ser visitado
	 * @param elemento     el elemento del que queremos conocer su altura
	 * @param alturaActual la altura actual del elemento
	 * @return la altura del elemento
	 */
	private int calcularAltura(Nodo nodo, E elemento, int alturaActual) {
		if (obtenerComparacion(elemento, nodo.dato) < 0) 
			return calcularAltura(nodo.izquierda, elemento, alturaActual + 1);
		else if (obtenerComparacion(elemento, nodo.dato) > 0) 
			return calcularAltura(nodo.derecha, elemento, alturaActual + 1);
		else 
			return alturaActual;
	}
	
	/**
	 * Método profundida que devuelve la profundida del elemento en el árbol binario.
	 *
	 * @param elemento el elemento del que queremos saber su profundidad
	 * @return la profuncidad del elemento pasado por parámetro
	 */
	public int profundidad(E elemento) {
		if (raiz == null)
			return -1;
		return calcularProfundidad(raiz, elemento, 0);
	}

	/**
	 * Método recursivo calcularProfundidad de profundidad que calcula la profundidad del árbol binario.
	 *
	 * @param nodo              el nodo a ser visitado
	 * @param elemento          el elemento del que queremos conocer su profundidad
	 * @param profundidadActual el calculo de la profundidad que llevamos.
	 * @return la profundidad del elemento pasado por parámetro.
	 */
	private int calcularProfundidad(Nodo nodo, E elemento, int profundidadActual) {
		if (obtenerComparacion(elemento, nodo.dato) < 0) 
			return calcularProfundidad(nodo.izquierda, elemento, profundidadActual + 1);
		else if (obtenerComparacion(elemento, nodo.dato) > 0) 
			return calcularProfundidad(nodo.derecha, elemento, profundidadActual + 1);
		else
			return profundidadActual;
	}

	/**
	 * Método obtenerComparación el cual compara dos elementos pasados por parámetro
	 * y da un resultado dependiendo de si es mayor, menor o iguales ambos.
	 *
	 * @param elemento1 el primer elemento a comparar
	 * @param elemento2 el segundo elemento a comparar
	 * @return el resultado de la comparación, mayor que uno en caso de que el
	 *         primer elemento sea mayor que el segundo, cero en caso de que ambos
	 *         sean iguales y menor que cero en caso de que el primer elemento sea
	 *         menor que el segundo
	 */
	@SuppressWarnings("unchecked")
	private int obtenerComparacion(E elemento1, E elemento2) {
		if (comparator != null)
			return comparator.compare(elemento1, elemento2);
		else 
			return ((Comparable<? super E>) elemento1).compareTo(elemento2);
	}
}