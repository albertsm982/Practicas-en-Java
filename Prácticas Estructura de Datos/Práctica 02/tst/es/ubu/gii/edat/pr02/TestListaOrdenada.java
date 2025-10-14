package es.ubu.gii.edat.pr02;

import static org.junit.Assert.*;




import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import es.ubu.gii.edat.pr02.ListaOrdenada;
import es.ubu.gii.edat.datos.coches.Coche;
import es.ubu.gii.edat.datos.coches.ComparadorCoches;
import es.ubu.gii.edat.datos.coches.GeneradorCoches;

public class TestListaOrdenada{

	private static int puntos = 0;
	
	/**
	 * M�todo para comprobar que la insercion de elementos individuales
	 * tiene como resultado una lista correctamente ordenada. 
	 */
	@Test
	public void ordenaElementos(){

		List<Integer> listaOrdenada = new ListaOrdenada<Integer>();

		listaOrdenada.add(5);
		listaOrdenada.add(3);
		listaOrdenada.add(8);
		listaOrdenada.add(10);
		listaOrdenada.add(5);

//		System.out.println(listaOrdenada);

		List<Integer> esperada = new ArrayList<Integer>(5);
		esperada.add(5);
		esperada.add(3);
		esperada.add(8);
		esperada.add(10);
		esperada.add(5);
		Collections.sort(esperada);

//		System.out.println(esperada);

		assertEquals("Lista ordenada. Incluye repetidos.", esperada, listaOrdenada);

		puntos = puntos + 1;
		
	}

	/**
	 * Metodo para comprobar que insertando una colecci�n desordenada, el resultado final
	 * es una lista correctamente ordenada.
	 */
	@Test
	public void ordenaUnaLista(){

		List<Integer> listaOrdenada = new ListaOrdenada<Integer>();

		List<Integer> desordenada = Arrays.asList(1,5,10,2,7,5,3,9);

		listaOrdenada.addAll(desordenada);

//		System.out.println(listaOrdenada.toString());

		List<Integer> esperada = new ArrayList<Integer>(12);
		esperada.addAll(desordenada);

		Collections.sort(esperada);

		assertEquals(esperada, listaOrdenada);

		puntos = puntos + 1;
		
	}

	/**
	 * Metodo para comprobar que insertando 2 colecciones diferentes, el resultado final
	 * est� correctamente ordenado
	 */
	@Test
	public void ordenaDosListas(){

		List<Character> listaOrdenada = new ListaOrdenada<Character>();

		List<Character> desordenada1 = Arrays.asList('o','a','h','c','b');

		listaOrdenada.addAll(desordenada1);

		List<Character> desordenada2 = Arrays.asList('b','g','d','f','k','i','j');

		listaOrdenada.addAll(desordenada2);

//		System.out.println(listaOrdenada.toString());

		List<Character> esperada = new ArrayList<Character>(12);
		esperada.addAll(desordenada1);
		esperada.addAll(desordenada2);

		Collections.sort(esperada);

		assertEquals(esperada, listaOrdenada);

		puntos = puntos + 2;
		
	}

	@Test
	public void ordenaElementosNoComp(){

		List<Coche> listNoComp = GeneradorCoches.listaAleatoria(25);
		List<Coche> listaOrdenada = new ListaOrdenada<Coche>(new ComparadorCoches<Coche>());

		for(int i=0; i < listNoComp.size(); i++)
			listaOrdenada.add(listNoComp.get(i));

		Collections.sort(listNoComp,new ComparadorCoches<Coche>());
		
//		System.out.println(listaOrdenada);
//		System.out.println(listNoComp);

		for(int i=0; i < listNoComp.size(); i++)
		assertEquals(listNoComp, listaOrdenada);
		
		puntos = puntos + 3;
		
	}

	
	@Test
	public void puntuacion(){
		System.out.println("Puntuacion: "+puntos);
	}
	
} //TestListaOrdenada
