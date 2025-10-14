package es.ubu.gii.edat.pr02;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;


/**
 * Clase lista ordenada a la que se la añaden objetos y esta les ordena 
 * en el orden que se les presuponga. Para ello, se han modificado los métodos
 * add y addAll y se genera un algoritmo de busqueda secuencial que busca y ordena
 * los elementos que queremos añadir a la lista.
 *
 * @author Alberto Santos Martínez
 * @param <E> the element type
 */
public class ListaOrdenada<E> extends AbstractList<E> {
	
	/** La lista a la que se la añaden los elementos. */
	private AbstractList<E> lista;
	
	/** El comparador que comparará los elementos. */
	private Comparator<E> comparador;

	/**
	 * Constructor de ListaOrdenada sin parámetros y con un comparador nulo
	 * (sin comparador).
	 */
	public ListaOrdenada(){
		super();
		lista = new ArrayList<E>();
		comparador = null;
	}
	
	/**
	 * Constructor de ListaOrdenada con parámetros y con un contructor.
	 *
	 * @param comparador the comparador
	 */
	public ListaOrdenada(Comparator<E> comparador){
		super();
		lista = new ArrayList<E>();
		this.comparador = comparador;
	}
	
	/**
	 * Método size que devuelve el tamaño de la lista.
	 *
	 * @return el tamaño de la lista
	 */
	@Override
	public int size() {
		return lista.size();
	}
	
	
	/**
	 * Método add que añade el elemento a la lista.
	 *
	 * @param elemento el elemento
	 * @return true, cuando se añade el elemento
	 */
	public boolean add(E elemento) {
		//Se llama al método busqueda para obtener una posición del elemento
		int busqueda = busqueda(elemento); 
		if(busqueda == -1) // En caso de que no haya encontrado al elemento, 
						   //añadirá el elemento a la lista sin mirar ningún indice
			lista.add(elemento);
		else 			   // En caso contrario, se añadirá en la posición devuelta por el método
			lista.add(busqueda,elemento);
		return true;
	}

	/**
	 * Método addAll que añade una colección a la lista.
	 *
	 * @param c la coleción
	 * @return true, en caso de que se haya terminado
	 */
	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean modificado = false;
		for (E elemento : c) {
	        if (add(elemento)) 
	            modificado = true;
	    }
	    return modificado;
	}

	
	/**
	 * Método get que devuelve el valor que se encuentra en la posición del
	 * índice que se pasa por parámetro.
	 *
	 * @param index el índice
	 * @return el elemento en el índice
	 */
	@Override
	public E get(int index) {

		if (index < 0 || index >= lista.size())   // En caso de que la posición del índice no se encuentre
												  // en la lista, salta una excepción de que se ha salido
												  // de la lista.
			throw new IndexOutOfBoundsException();

		return lista.get(index);
	}

	/**
	 * Método busqueda que realiza una busqueda secuencial en la que busca un dato en una lista
	 * en caso de que no le encuentre devolverá una posición fuera de la lista, en caso de que sí
	 * le encuentre, devolverá su posición.
	 *
	 * @param dato el dato
	 * @return la posición
	 */
	private int busqueda(E dato) {
		for(int posicion = 0; posicion < lista.size(); posicion++) {
			int comparador = tipoConstructor(dato, lista.get(posicion)); // vamos comparando los elementos de la lista
			if(comparador <= 0) // si la comparación devuelve 0, es que se ha encontrado la posición de un elemento igual en la lista.
				return posicion;
		}
		return -1;
	}
	
	
	/**
	 *  Esta es una prueba de busqueda binaria con la cual no llegaba a 
	 *  ordenar bien la lista.
	 *
	 * @param dato1 the dato 1
	 * @param dato2 the dato 2
	 * @return the int
	 */
	/*
	private int busqueda(E dato) {
		int inicio = 0;
		int fin = lista.size() - 1;
		
		while (inicio <= fin) {
			int mitad = inicio + (fin - inicio) / 2;
			int comparacion = comparar(dato, lista.get(mitad));
			if (comparacion <= 0) 
				return mitad; // El elemento ya está en la lista, no se inserta

			if (comparacion > 0) // El elemento es mayor que el elemento en la posición media
				inicio = mitad + 1;
			else // El elemento es menor que el elemento en la posición media
				fin = mitad - 1; 
		}

		return -1; // Si no se encuentra el elemento, retornar -1
	}
	*/
	
	
	
	/**
	 * Método tipoConstructor que elige que técnica de comparación se va a utilizar,
	 * si se va a utilizar un comparador o no.
	 *
	 * @param dato1 el primer dato
	 * @param dato2 el dato en la segunda posición
	 * @return la forma de comparación
	 */
	private int tipoConstructor(E dato1, E dato2) {
		
		if (comparador == null) 
			// En caso de que no tengamos comparador, utilizaremos el método compareTo
			return ((Comparable<? super E>) dato1).compareTo(dato2);
		else 
			// En caso contrario, utilizaremos el método compare del comparador.
			return comparador.compare(dato1, dato2);
	}
}

