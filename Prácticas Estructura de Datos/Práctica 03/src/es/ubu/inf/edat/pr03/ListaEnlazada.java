package es.ubu.inf.edat.pr03;

import java.util.AbstractList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Clase ListaEnlazada que extiende de AbstractList la cual es genérica por lo
 * que puede almacenar cualquier tipo de dato.
 *
 * @author Alberto Santos Martínez
 * @param <E> tipo del elemento
 */
public class ListaEnlazada<E> extends AbstractList<E> {

	/** Varible primero que tiene referencia al primer dato de la lista. */
	private Nodo<E> primero;

	/** Varible último que tiene referencia al último dato de la lista. */
	private Nodo<E> último;

	/**
	 * Variable tamaño que indica el número de elementos almacenados en la lista.
	 */
	private int tamano;

	/**
	 * Clase privada Nodo la cual es inner class. Esta clase llega a crear todos los
	 * nodos de la lista. Dando un tipo genérico y una referencia al nodo siguiente
	 * de la lista.
	 *
	 * @param <E> the element type
	 */
	private static class Nodo<E> {

		/** Dato del nodo. */
		E dato;

		/** Referencia al siguiente nodo de la lista. */
		Nodo<E> siguiente;

		/**
		 * Método constructor de la clase nodo.
		 *
		 * @param dato      el dato del nodo
		 * @param siguiente la referencia al nodo siguiente.
		 */
		public Nodo(E dato, Nodo<E> siguiente) {
			this.dato = dato;
			this.siguiente = siguiente;
		}
	}

	/**
	 * Redefinición del método add de la clase abstracta AbstractList el cual
	 * introduce un elemento al final de la lista enlazada. En caso de que esté
	 * vacía añadiremos el nodo a añadir en la primera posición y en caso contrario
	 * se añadirá al final actualizando la referencia siguiente del último nodo al
	 * nuevo nodo añadido. Luego se actualizarán tanto el tamaño como el valor del
	 * último nodo.
	 *
	 * @param dato el valor del dato que queremos añadir a la lista
	 * @return true, en caso de que se haya añadido.
	 */
	@Override
	public boolean add(E dato) {
		Nodo<E> nodoIntroducido = new Nodo<>(dato, null); // Creamos un nodo con un puntero nulo

		if (último == null)
			primero = nodoIntroducido;
		else
			último.siguiente = nodoIntroducido;

		último = nodoIntroducido;
		tamano++;
		return true;
	}

	/**
	 * Redefinición del método remove de la clase abstracta AbstractList que pasando
	 * por parámetro un índice elimina el elemento de la lista posicionado en la
	 * posición del índice. Primero comprueba de que el índice no esté dentro del
	 * tamaño de la lista, en caso correcto lanza una excepción indicando de que
	 * está fuera de los límites de la lista y en caso correcto, continua la
	 * ejecución. El método va avanzando con dos nodos, el nodo índice el cual
	 * avanzará por la lista hasta llegar al índice pasado por parámetro y el nodo
	 * anterior el cual guardará la posición anterior a este. En caso de que el
	 * índice sea la primera posición, el nodo primero se quedará con la referencia
	 * del siguiente nodo. En caso de que sea el último, entonces último nodo pasará
	 * a ser el nodo anterior y en el caso de que el índice se encuentre en un nodo
	 * que esté en el medio de la lista, entonces nos quedaremos que el nodo
	 * siguiente del nodo indice será el del nodo anterior.
	 *
	 * @param index el índice
	 * @return el nodo eliminado
	 */
	@Override
	public E remove(int index) {
		if (index < 0 || index >= tamano)
			throw new IndexOutOfBoundsException();

		Nodo<E> nodoIndex = primero;
		Nodo<E> nodoAnterior = null;

		for (int i = 0; i < index; i++) {
			nodoAnterior = nodoIndex;
			nodoIndex = nodoIndex.siguiente;
		}

		if (nodoIndex == primero)
			primero = nodoIndex.siguiente;
		else if (nodoIndex == último) {
			último = nodoAnterior;
			último.siguiente = null;
		} else
			nodoAnterior.siguiente = nodoIndex.siguiente;

		tamano--;
		return nodoIndex.dato;
	}

	/**
	 * Redefinición del método get de la clase abstracta AbstractList que pasando
	 * por parámetro un índice obtiene el elemento de la lista posicionado en la
	 * posición del índice.
	 *
	 * @param index el índice
	 * @return el nodo obtenido por el índice
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= tamano)
			throw new IndexOutOfBoundsException();

		Nodo<E> nodoIndice = primero;
		for (int i = 0; i < index; i++) {
			nodoIndice = nodoIndice.siguiente;
		}

		return nodoIndice.dato;
	}

	/**
	 * Refefinición del método size de la clase abstracta Abstract que devuelve el
	 * tamaño de la lista.
	 *
	 * @return el tamaño
	 */
	@Override
	public int size() {
		return tamano;
	}

	/**
	 * Refefinición del método Iterator de la clase abstracta AbstractList que devuelve una
	 * instancia de la inner class ListaEnlazadaIterator.
	 *
	 * @return la instancia de la clase interna ListaEnlazadaIterator
	 */
	@Override
	public ListIterator<E> iterator() {
		return new ListaEnlazadaIterator();
	}

	/**
	 * Refefinición del método clear de la clase abstracta AbstractList que restablece los
	 * valores de los objetos de la clase ListaEnlazada a nulos o ceros.
	 */
	@Override
	public void clear() {
		primero = null;
		último = null;
		tamano = 0;
	}

	/**
	 * Clase interna ListaEnlazadaIterator que implementa la interfaz ListIterator
	 * que crea un iterador para la lista enlazada.
	 */
	private class ListaEnlazadaIterator implements ListIterator<E> {

		/**
		 * Variable que tiene como referencia el último nodo devuelto por el iterador
		 */
		private Nodo<E> últimoIt;

		/** Variable que tiene como referencia al siguiente nodo de la lista */
		private Nodo<E> siguienteIt;

		/** Variable que indica el índice del siguiente nodo de la lista */
		private int indiceSiguiente;

		/**
		 * Constructor de la clase interna listaEnlazadaIterator que inicializa las
		 * variables
		 */
		public ListaEnlazadaIterator() {
			siguienteIt = primero;
			indiceSiguiente = 0;
		}

		/**
		 * Método hasNext que verifica si hay un elemento más después del índice.
		 *
		 * @return true, en caso de que haya un elemento siguiente en la lista. false,
		 *         en caso contrario
		 */
		@Override
		public boolean hasNext() {
			return indiceSiguiente < tamano;
		}

		/**
		 * Método next que devuelve el siguiente elemento de la lista aumentando el
		 * índice un valor. En caso de que no haya un valor, lanzará una excepción que
		 * significará que no hay un valor.
		 *
		 * @return el siguiente elemento de la lista.
		 */
		@Override
		public E next() {
			if (!hasNext())
				throw new NoSuchElementException();

			últimoIt = siguienteIt; // Se redefine el último valor devuelto por el iterador
			siguienteIt = siguienteIt.siguiente;
			indiceSiguiente++;
			return últimoIt.dato;
		}

		/**
		 * Método hasPrevious que verifica si hay un elemento anterior al índice en la
		 * lista.
		 *
		 * @return true, en caso de que haya un elemento anterior en la lista. false, en
		 *         caso contrario
		 */
		@Override
		public boolean hasPrevious() {
			return indiceSiguiente > 0;
		}

		/**
		 * Método previous que no se ha implementado en la práctica
		 */
		@Override
		public E previous() {
			throw new UnsupportedOperationException();
		}

		/**
		 * Método nextIndex que devuelve el índice del siguiente elemento.
		 *
		 * @return el índice del siguiente elemento
		 */
		@Override
		public int nextIndex() {
			return indiceSiguiente;
		}

		/**
		 * Método previousIndex que devuelve el índice del anterior elemento.
		 *
		 * @return el índice del anterior elemento
		 */
		@Override
		public int previousIndex() {
			return indiceSiguiente - 1;
		}

		/**
		 * Método remove que no se ha implementado en la práctica
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		/**
		 * Método set que no se ha implementado en la práctica
		 *
		 * @param dato el dato
		 */
		@Override
		public void set(E dato) {
			throw new UnsupportedOperationException();
		}

		/**
		 * Método add que no se ha implementado en la práctica
		 *
		 * @param dato el dato
		 */
		@Override
		public void add(E dato) {
			throw new UnsupportedOperationException();
		}
	}
}
