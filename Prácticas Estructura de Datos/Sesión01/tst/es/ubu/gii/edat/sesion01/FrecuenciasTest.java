/**
 * Clase que prueba los dos mï¿½todos de la clase Frecuencias: frecuencia y menosFrecuente.
 */
package es.ubu.gii.edat.sesion01;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author <a href="mailto:jmlrobledo@ubu.es">Josï¿½ Miguel Lï¿½pez Robledo</a>
 * 
 */
public class FrecuenciasTest {

	/**
	 * Funciï¿½n que comprueba si cuando pasamos una colecciï¿½n vacï¿½a se la lanza
	 * la excepciï¿½n NoSuchElementException
	 */
	@Test (expected = NoSuchElementException.class)
	public void testFooThrowsNoSuchElementException() {
		
		// Creamos una colecciï¿½n nula
		Collection<Integer> col1=null;
		Frecuencias.menosFrecuente(col1, false);
		
	}



	/**
	 * Funciï¿½n que prueba {@code Frecuencias.frecuencia()}.
	 */
	@Test
	public void compruebaFrecuenciaInt() {

		// Creamos una colecciï¿½n de enteros y comprobamos frecuencias
		Collection<Integer> col1 = Arrays.asList(1, 2, 3, 4, 5, 2, 3, 4, 3);
		
		// Con equals
		assertEquals(1, Frecuencias.frecuencia(col1, 1, true));
		assertEquals(2, Frecuencias.frecuencia(col1, 2, true));
		assertEquals(3, Frecuencias.frecuencia(col1, 3, true));
		assertEquals(2, Frecuencias.frecuencia(col1, 4, true));
		assertEquals(1, Frecuencias.frecuencia(col1, 5, true));
		assertEquals(0, Frecuencias.frecuencia(col1, 6, true));
		assertEquals(0, Frecuencias.frecuencia(col1, null, true));
		assertEquals(3, Frecuencias.frecuencia(col1, new Integer(3), true));
		
		// Sería "más correcto" esta forma de evaluarlo, pero queremos forzar la prueba.
		// assertEquals(0, Frecuencias.frecuencia(col1, Integer.valueOf(3), true));

		
		// Sin equals
		assertEquals(1, Frecuencias.frecuencia(col1, 1, false));
		assertEquals(2, Frecuencias.frecuencia(col1, 2, false));
		assertEquals(3, Frecuencias.frecuencia(col1, 3, false));
		assertEquals(2, Frecuencias.frecuencia(col1, 4, false));
		assertEquals(1, Frecuencias.frecuencia(col1, 5, false));
		assertEquals(0, Frecuencias.frecuencia(col1, 6, false));
		assertEquals(0, Frecuencias.frecuencia(col1, null, false));
		assertEquals(0, Frecuencias.frecuencia(col1, new Integer(3), false)); // Para forzar la generación de un objeto nuevo
		// Sería "más correcto" esta forma de evaluarlo, pero queremos forzar la prueba.
		// assertEquals(0, Frecuencias.frecuencia(col1, Integer.valueOf(3), false));
	}
	
	@Test
	public void compruebaFrecuenciaString() {
	
		// Creamos una colecciï¿½n de cadenas y comprobamos frecuencias
		Collection<String> col2 = Arrays.asList("a", "b", null, new String("b"));
		// Con equals
		assertEquals(1, Frecuencias.frecuencia(col2, "a", true));
		assertEquals(2, Frecuencias.frecuencia(col2, "b", true));
		assertEquals(0, Frecuencias.frecuencia(col2, null, true));
		
		// Sin equals
		assertEquals(1, Frecuencias.frecuencia(col2, "a", false));
		assertEquals(1, Frecuencias.frecuencia(col2, "b", false));
		assertEquals(1, Frecuencias.frecuencia(col2, null, false));
		
	}
	
	@Test
	public void compruebaFrecuenciaSet() {
	
		Collection<Integer> col1 = Arrays.asList(1, 2, 3, 4, 5, 2, 3, 4, 3);
		Collection<Integer> col4 = new HashSet<Integer>(col1);
		// Con equals
		assertEquals(1,  Frecuencias.frecuencia(col4, 1, true));
		assertEquals(1,  Frecuencias.frecuencia(col4, 2, true));
		assertEquals(1,  Frecuencias.frecuencia(col4, 3, true));
		assertEquals(1,  Frecuencias.frecuencia(col4, 4, true));
		assertEquals(1,  Frecuencias.frecuencia(col4, 5, true));
		assertEquals(0,  Frecuencias.frecuencia(col4, 6, true));
		
		// Sin equals
		assertEquals(1, Frecuencias.frecuencia(col4, 1, false));
		assertEquals(1, Frecuencias.frecuencia(col4, 2, false));
		assertEquals(1, Frecuencias.frecuencia(col4, 3, false));
		assertEquals(1, Frecuencias.frecuencia(col4, 4, false));
		assertEquals(1, Frecuencias.frecuencia(col4, 5, false));
		assertEquals(0, Frecuencias.frecuencia(col4, 6, false));
		
	}
	
	@Test
	public void compruebaFrecuenciaMezcla() {
	
		Collection<Integer> col5 = Arrays.asList(1, Integer.valueOf(1), 2);
		// Con equals
		assertEquals(2, Frecuencias.frecuencia(col5, 1, true));
		// Sin equals
		assertEquals(2, Frecuencias.frecuencia(col5, 1, false));

		Collection<String> col6 = Arrays.asList("uno", "dos", "uno");
		// Con equals
		assertEquals(2, Frecuencias.frecuencia(col6, "uno", true));
		// Sin equals
		assertEquals(2, Frecuencias.frecuencia(col6, "uno", false));

		Collection<String> col7 = Arrays.asList("uno", "dos", new String("uno"));
		// Con equals
		assertEquals(2, Frecuencias.frecuencia(col7, "uno", true));
		// Sin equals
		assertEquals(1, Frecuencias.frecuencia(col7, "uno", false));

	}

	/**
	 * Funciï¿½n que prueba Frecuencias.masFrecuente().
	 * 
	 * @return indica si todas las comprobaciones han funcionado
	 */
	@Test
	public void compruebaMenosFrecuenteInt() {

		// Comprobamos el menos frecuente en la lista [1]
		Collection<Integer> col1 = Arrays.asList(1);
		assertEquals(Integer.valueOf(1), Frecuencias.menosFrecuente(col1, false));
		assertEquals(Integer.valueOf(1), Frecuencias.menosFrecuente(col1, true));

		// Comprobamos el menos frecuente en la lista [1,2,3]. Como todos tienen
		// la misma frecuencia debe devolver el primero
		Collection<Integer> col2 = Arrays.asList(1, 2, 3);
		assertEquals(Integer.valueOf(1), Frecuencias.menosFrecuente(col2, false));
		assertEquals(Integer.valueOf(1), Frecuencias.menosFrecuente(col2, true));

		// Comprobamos el menos frecuente en la lista [1,2,3,1,2,4,2,5]
		Collection<Integer> col3 = Arrays.asList(1, 2, 3, 1, 2, 4, 2, 5);
		assertEquals(Integer.valueOf(3), Frecuencias.menosFrecuente(col3, false));
		assertEquals(Integer.valueOf(3), Frecuencias.menosFrecuente(col3, true));

		// Comprobamos el menos frecuente en la lista [1,2,3,1,2,4,2,5]
		Collection<Integer> col4 = Arrays.asList(1, 2, 1, 2, 4, 5, new Integer(4)); // Para forzar a que sean 2 objetos diferentes
		// El "más correcto" sería este, pero queremos forzar la prueba.
		//Collection<Integer> col4 = Arrays.asList(1, 2, 1, 2, 4, 5, Integer.valueOf(4)); 
		assertEquals(Integer.valueOf(4), Frecuencias.menosFrecuente(col4, false));
		assertEquals(Integer.valueOf(5), Frecuencias.menosFrecuente(col4, true));
	}
	
	@Test
	public void compruebaMenosFrecuenteSet() {
	
		// Comprobamos el menos frecuente en el conjunto {1}
		Collection<Integer> col5 = new HashSet<Integer>(Arrays.asList(1));
		assertEquals(Integer.valueOf(1), Frecuencias.menosFrecuente(col5, false));
		assertEquals(Integer.valueOf(1), Frecuencias.menosFrecuente(col5, true));
		
		// Comprobamos el menos frecuente en el conjunto {"1"}
		Collection<String> col6 = new HashSet<String>(Arrays.asList("1"));
		assertEquals("1", Frecuencias.menosFrecuente(col6, false));
		assertEquals("1", Frecuencias.menosFrecuente(col6, true));

		// Comprobamos el menos frecuente en la lista
		// ["1","2","3","1","2","4","2","5"], pero el
		Collection<String> col7 = Arrays.asList("1", "2", "3", "1", "2", "3","1", new String("2"), "3");
		assertEquals("2",Frecuencias.menosFrecuente(col7, false));
		assertEquals("1",Frecuencias.menosFrecuente(col7, true));

	}

}
