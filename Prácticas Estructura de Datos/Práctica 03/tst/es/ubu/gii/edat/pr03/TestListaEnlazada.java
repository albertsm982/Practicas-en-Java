/**
 * Programa que prueba la clase {@code ListaEnlazada}.
 * 
 * Al ejecutarse con las aserciones habilitadas (opci�n -ea o -enableassertions
 * de la m�quina virtual), no deber�a saltar ninguna.
 */

package es.ubu.gii.edat.pr03;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import junit.framework.JUnit4TestAdapter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.ubu.inf.edat.pr03.ListaEnlazada;

public class TestListaEnlazada {

	ListaEnlazada<Integer> lEnlazada = null;
	List<Integer> control = null;

	/**
	 * 
	 */
	@Before
	public void runBeforeEveryTest() {
		lEnlazada = new ListaEnlazada<Integer>();
		control = new ArrayList<Integer>(5);
	}

	/**
	 * 
	 */
	@After
	public void runAfterEveryTest() {
		lEnlazada.clear();
		control.clear();
	} 

	/**
	 * M�todo para comprobar que la insercion de elementos individuales
	 * tiene como resultado una lista correctamente creada.
	 * Deber�a ser igual que una lista creada con un array. 
	 */
	@Test
	public void insertaElementos(){

		lEnlazada.add(5); control.add(5);  
		lEnlazada.add(3); control.add(3);
		lEnlazada.add(8); control.add(8);
		lEnlazada.add(10); control.add(10);
		lEnlazada.add(5); control.add(5);

		System.out.println(lEnlazada.toString());

		assertEquals("No se han insertado correctamente", control, lEnlazada);

	}

	@Test
	public void recuperaElementos(){

		lEnlazada.add(5); control.add(5);  
		lEnlazada.add(3); control.add(3);
		lEnlazada.add(8); control.add(8);
		lEnlazada.add(10); control.add(10);
		lEnlazada.add(5); control.add(5);

		assertEquals("No se han insertado correctamente", control, lEnlazada);

		assertEquals("No se han recuperado correctamente",control.get(0),lEnlazada.get(0));
		assertEquals("No se han recuperado correctamente",control.get(4),lEnlazada.get(4));
		assertEquals("No se han recuperado correctamente",control.get(2),lEnlazada.get(2));

	}


	/**
	 * M�todo que permite comprobar si se insertan correctamente elementos en una posici�n dada,
	 * distinta de la posici�n final. Solo se necesita comprobar en caso de querer comprobar el
	 * trabajo que se propone como opcional.
	 */


	/**
	 * Metodo para comprobar que insertando una colecci�n ya generada anteriormente, 
	 * el resultado es una lista equivalente.
	 */
	@Test
	public void insertaUnaLista(){

		List<Integer> auxiliar = Arrays.asList(1,5,10,2,7,5,3,9);

		lEnlazada.addAll(auxiliar);

		System.out.println(lEnlazada.toString());

		assertEquals("No se ha insertado correctamente una sublista dentro de otra", auxiliar, lEnlazada);

	}

	/**
	 * Metodo para comprobar el borrado de elementos en funcion de la posicion que ocupan.
	 * Tras realizarlo, debe contener el mismo listado de elementos sin el que se acaba de eliminar.
	 */
	@Test
	public void borraElementosIndice(){

		ListaEnlazada<Character> lEnlazada = new ListaEnlazada<Character>();
		List<Character> control = new ArrayList<Character>(5);

		lEnlazada.add('b'); control.add('b');    
		lEnlazada.add('d'); control.add('d');
		lEnlazada.add('a'); control.add('a');
		lEnlazada.add('z'); control.add('z');
		lEnlazada.add('i'); control.add('i');

		System.out.println(lEnlazada.toString());

		// First element
		lEnlazada.remove(0); control.remove(0);
		assertEquals ("Al borrar el primer elemento, el tama�o no se actualiza correctamente", control.size(), lEnlazada.size());
		assertEquals ("Al borrar el primer elemento, la lista no se actualiza correctamente", control, lEnlazada);

		System.out.println(lEnlazada.toString());

		// Last element
		lEnlazada.remove(3); control.remove(3);
		assertEquals ("Al borrar el ultimo elemento, el tama�o no se actualiza correctamente", control.size(), lEnlazada.size());
		assertEquals ("Al borrar el ultimo elemento, la lista no se actualiza correctamente", control, lEnlazada);

		System.out.println(lEnlazada.toString());

		// Center element
		lEnlazada.remove(1); control.remove(1);
		assertEquals ("Al borrar el ultimo elemento, el tama�o no se actualiza correctamente", control.size(), lEnlazada.size());
		assertEquals ("Al borrar el ultimo elemento, la lista no se actualiza correctamente", control, lEnlazada);

		System.out.println(lEnlazada.toString());

	}

	@Test
	public void vaciaCabeza(){

		ListaEnlazada<Character> lEnlazada = new ListaEnlazada<Character>();
		List<Character> control = new ArrayList<Character>(5);

		lEnlazada.add('b'); control.add('b');    
		lEnlazada.add('d'); control.add('d');
		lEnlazada.add('a'); control.add('a');
		lEnlazada.add('z'); control.add('z');
		lEnlazada.add('i'); control.add('i');

		while(!lEnlazada.isEmpty()){
			Character e = lEnlazada.remove(0);
			Character c = control.remove(0);
			assertEquals ("No se recupera el primer elemento correctamente al borrarlo", c, e);
			assertEquals ("No se borra el primer elemento correctamente", control, lEnlazada);
		}

	}

	/**
	 * M�todo que permite comprobar si se realizan los recorridos de forma correcta. 
	 * Se comprueba tanto la iteracion ascendente como la descendente
	 */
	@Test
	public void recorridos(){

		ListaEnlazada<Integer> lEnlazada = new ListaEnlazada<Integer>();
		List<Integer> control = new ArrayList<Integer>(5);
		int cont = 0;		

		lEnlazada.add(5); control.add(5);  
		lEnlazada.add(3); control.add(3);
		lEnlazada.add(8); control.add(8);
		lEnlazada.add(10); control.add(10);
		lEnlazada.add(5); control.add(5);

		ListIterator<Integer> it = lEnlazada.listIterator();
		ListIterator<Integer> itC = control.listIterator();

		while (it.hasNext()){
			cont ++;
			Integer c = itC.next();
			Integer le = it.next();
			assertEquals ("No se realiza el recorrido hacia adelante correctamente", c, le);
		}

		assertEquals (5, cont);

		while (it.hasPrevious()){
			cont --;
			Integer c = itC.previous();
			Integer le = it.previous();
			assertEquals ("No se realiza el recorrido hacia atras correctamente", c, le);
		}

		assertEquals (0, cont);

	}

}
