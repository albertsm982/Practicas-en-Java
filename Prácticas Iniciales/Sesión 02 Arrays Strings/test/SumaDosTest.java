

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/**
 * Tests ejercicio suma dos.
 *
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @version 1.0
 */
public class SumaDosTest {	
	
	/**
	 * Comprueba casos sin solución que devuelve nulo.
	 */
	@DisplayName("Casos sin solución")
	@Test
	void sinSolución() {
		assertAll("Comprobar casos sin solución", 
				() -> assertNull(SumaDos.ejecutar(new int[] { }, 0), "Sin solucion 0 con array vacío"),
				() -> assertNull(SumaDos.ejecutar(new int[] { 0, 0 }, 1), "Sin solucion 1"),
				() -> assertNull(SumaDos.ejecutar(new int[] { 0, 0 }, -1), "Sin solucion 2"),
				() -> assertNull(SumaDos.ejecutar(new int[] { 1, 0 }, 0), "Sin solucion 3"),
				() -> assertNull(SumaDos.ejecutar(new int[] { 0 }, 0), "Sin solucion 4"),
				() -> assertNull(SumaDos.ejecutar(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9}, 18), "Sin solucion 5")
				);
	}
	
	/**
	 * Comprueba casos límite con pocos elementos.
	 */
	@DisplayName("Casos límite")
	@Test
	void limites() {
		assertAll("Comprobar casos límite",				
				() -> assertArrayEquals(new int[] { 0, 1 },  
						SumaDos.ejecutar(new int[] { 0, 0 }, 0), "Todo ceros"),
				() -> assertArrayEquals(new int[] { 0, 1 },  
						SumaDos.ejecutar(new int[] { 0, 1 }, 1), "Suma 1"));
	}

	/**
	 * Comprueba casos básicos.
	 */
	@DisplayName("Comprobaciones básicas")
	@Test
	void basico() {
		assertAll("Comprobar varios", 
				() -> assertArrayEquals(new int[] { 0, 1 },  
						SumaDos.ejecutar(new int[] { 12, 17, 21, 25 }, 29)),
				() -> assertArrayEquals(new int[] {0, 3}, 
						SumaDos.ejecutar(new int[]{2, 3, 4, 5}, 7)),
				() -> assertArrayEquals(new int[] {1, 2}, 
						SumaDos.ejecutar(new int[]{1, 3, 4, 5}, 7)),				
				() -> assertArrayEquals(new int[] { 0, 1 },  
						SumaDos.ejecutar(new int[] { 1, 2, 3, 4 }, 3)));
	}
	
	/**
	 * Comprueba casos más avanzados.
	 */
	@DisplayName("Comprobaciones complejas")
	@Test
	void complejas() {
		assertAll("Comprobar varios", 
				() -> assertArrayEquals(new int[] { 0, 3 },  
						SumaDos.ejecutar(new int[] { 12, 17, 21, 25 }, 37), "Caso 1"),
				() -> assertArrayEquals(new int[] { 1, 3 },  
						SumaDos.ejecutar(new int[] { 12, 17, 21, 25 }, 42), "Caso 2"),
				() -> assertArrayEquals(new int[] { 2, 3 },  
						SumaDos.ejecutar(new int[] { 12, 17, 21, 25 }, 46), "Caso 3"),
				() -> assertArrayEquals(new int[] { 0, 3 },  
						SumaDos.ejecutar(new int[] { 1, 2, 3, 4 }, 5), "Caso 4"));
	}
	
	/**
	 * Comprueba casos en los que hay dos posibles opciones.
	 */
	@DisplayName("Comprobaciones complejas con varios candidatos")
	@Test
	void complejasConVariosCandidatos() {
		assertAll("Comprobar con varios candidatos", 
				() -> assertArrayEquals(new int[] { 0, 3 },  
						SumaDos.ejecutar(new int[] { 12, 17, 21, 25, 25 }, 37), "Caso 1"),
				() -> assertArrayEquals(new int[] { 1, 3 },  
						SumaDos.ejecutar(new int[] { 12, 17, 21, 25, 21 }, 42), "Caso 2"),
				() -> assertArrayEquals(new int[] { 2, 3 },  
						SumaDos.ejecutar(new int[] { 12, 17, 21, 25, 23, 23 }, 46), "Caso 3"),
				() -> assertArrayEquals(new int[] { 0, 3 },  
						SumaDos.ejecutar(new int[] { 1, 2, 3, 4, 4, 1 }, 5), "Caso 4"));
	}
}
