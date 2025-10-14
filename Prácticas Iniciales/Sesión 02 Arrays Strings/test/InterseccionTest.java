import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/**
 * Tests ejercicio de intersección de arrays.
 *
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @version 1.0
 */
public class InterseccionTest {

	/**
	 * Comprueba casos límite.
	 */
	@DisplayName("Casos límite")
	@Test
	void casosLímite() {
		assertAll("Comprobar casos límite",				
				() -> assertArrayEquals(new int[] { },  
						Interseccion.calcular(new int[] { 1, 2 ,3 }, new int[] { 4, 5 }), "Intersección vacía 1"),
				() -> assertArrayEquals(new int[] { },   
						Interseccion.calcular(new int[] { 1, 2 ,3, 4 }, new int[] { 6, 7 }), "Intersección vacía 2"),
				() -> assertArrayEquals(new int[] { 1 },  
						Interseccion.calcular(new int[] { 1 }, new int[] { 1 }), "Intersección un elemento en conjuntos de uno"),
				() -> assertArrayEquals(new int[] { 3 },  
						Interseccion.calcular(new int[] { 0, 1, 2, 3}, new int[] { 3 }), "Intersección un único elemento 3"),
				() -> assertArrayEquals(new int[] { 0, 1, 2, 3, 4 },  
						Interseccion.calcular(new int[] { 0, 1, 2, 3, 4}, new int[] { 0, 1, 2, 3, 4 }), "Intersección de todos los elementos"),
				() -> assertArrayEquals(new int[] { },  
						Interseccion.calcular(new int[] { }, new int[] { }), "Intersección de conjuntos vacíos")
				);
	}
	
	/**
	 * Comprueba casos básicos.
	 */
	@DisplayName("Casos básicos")
	@Test
	void casosBásicos() {
		assertAll("Comprobar casos básicos",				
				() -> assertArrayEquals(new int[] { 1 },  
						Interseccion.calcular(new int[] { 1, 2, 3 }, new int[] { 1 }), "Intersección un elemento 1 al principio"),
				() -> assertArrayEquals(new int[] { 1 },  
						Interseccion.calcular(new int[] { 3, 2, 1}, new int[] { 1 }), "Intersección un elemento 1 al final"),
				() -> assertArrayEquals(new int[] { 2 },  
						Interseccion.calcular(new int[] { 1, 2}, new int[] {2, 3, 4, 5}), "Intersección un elemento 2"),
				() -> assertArrayEquals(new int[] { 3 },  
						Interseccion.calcular(new int[] { 0, 1, 2, 3, 4}, new int[] { 3 }), "Intersección un elemento 3"),				
				() -> assertArrayEquals(new int[] { 4 },  
						Interseccion.calcular(new int[] { 1, 2, 3, 4}, new int[] {4, 7}), "Intersección un elemento 4"),
				() -> assertArrayEquals(new int[] { 0, 3 },  
						Interseccion.calcular(new int[] { 0, 1, 2, 3, 4}, new int[] { 0, 3 }), "Intersección dos elementos 0 y 3"),
				() -> assertArrayEquals(new int[] { 0, 4 },  
						Interseccion.calcular(new int[] { 0, 1, 2, 3, 4}, new int[] { 0, 4, 6 }), "Intersección dos elementos 0 y 4"),
				() -> assertArrayEquals(new int[] { 1, 4 },  
						Interseccion.calcular(new int[] { 0, 1, 2, 3, 4}, new int[] { 6, 5, 4, 1 }), "Intersección dos elementos 1 y 4")
				);
	}
}
