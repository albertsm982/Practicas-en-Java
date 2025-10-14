

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests ejecicio eliminar elemento en array.
 *
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @version 1.0
 */
public class EliminarElementoTest {
	
	/**
	 * Comprueba casos donde no se encuentra el elemento.
	 */
	@DisplayName("Casos donde no se encuentra")
	@Test
	void sinEncontrar() {
		assertAll("Comprobar casos donde no se encuentra el elemento.",
				() -> assertNull(EliminarElemento.eliminar(new int[] { 1 }, 4), "Sin encontrar, con un solo elemento en el array."),
				() -> assertNull(EliminarElemento.eliminar(new int[] { 1, 2, 3 }, 4), "Sin encontrar 4"),
				() -> assertNull(EliminarElemento.eliminar(new int[] { 0, 1 }, 2), "Sin encontrar 2"));
	}
	
	/**
	 * Comprueba casos límite.
	 */
	@DisplayName("Casos límite")
	@Test
	void casosLímites() {
		assertAll("Casos límites.",
		() -> assertArrayEquals(new int[] { },  
				EliminarElemento.eliminar(new int[] { 1 }, 1), "Eliminado el único elemento"),
		() -> assertArrayEquals(new int[] { 0 },  
				EliminarElemento.eliminar(new int[] { 1, 0 }, 1), "Eliminado el primer elemento"),
		() -> assertArrayEquals(new int[] { 0 },  
				EliminarElemento.eliminar(new int[] { 0, 1}, 1), "Eliminado el segundo elemento"));
	}

	/**
	 * Comprueba casos básicos.
	 */
	@DisplayName("Casos básicos")
	@Test
	void casosBásicos() {
		assertAll("Comprobar casos básicos",				
				() -> assertArrayEquals(new int[] { 1, 3 },  
						EliminarElemento.eliminar(new int[] { 1, 2 ,3 }, 2), "Eliminado valor 1"),
				() -> assertArrayEquals(new int[] { 1, 3, 4, 5 },  
						EliminarElemento.eliminar(new int[] { 1, 2, 3, 4, 5}, 2), "Eliminado valor 2"),
				() -> assertArrayEquals(new int[] { 2, 3, 4, 5 },  
						EliminarElemento.eliminar(new int[] { 1, 2, 3, 4, 5}, 1), "Eliminado primer valor 1"),
				() -> assertArrayEquals(new int[] { 1, 2, 3, 4 },  
						EliminarElemento.eliminar(new int[] { 1, 2, 3, 4, 5}, 5), "Eliminado valor último 5"),
				() -> assertArrayEquals(new int[] { 0, 1, 2, 4 },  
						EliminarElemento.eliminar(new int[] { 0, 1, 2, 3, 4}, 3), "Eliminado valor 3"));
	}
}
