

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests ejercicio de multiplicación de matrices multidimensionales.
 *
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @version 1.0
 */
public class MultiplicarMatricesTest {
	
	/**
	 * Comprueba casos sin solución que devuelve nulo.
	 */
	@DisplayName("Casos sin solución")
	@Test
	void sinSolución() {
		assertAll("Comprobar casos sin solución", 
				() -> assertNull(
						MultiplicarMatrices.multiplicar(new int[][] { { 1, 2, 3 }, { 4, 5, 6 } },
								new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, {3, 4} }),
						"Caso 1. [2x3] x [4x2] = null"),
				() -> assertNull(
						MultiplicarMatrices.multiplicar(new int[][] { { 1, 2 }, { 4, 5 } },
								new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 } }),
						"Caso 2. [2x2] x [3x2] = null"),
				() -> assertNull(
						MultiplicarMatrices.multiplicar(new int[][] { { 1, 2, 3 } },
								new int[][] { { 0, 1, 2 } }),
						"Caso 3. [1x3] x [1x3] = null"),
				() -> assertNull(
						MultiplicarMatrices.multiplicar(new int[][] { { 1 }, { 2 } },
								new int[][] { { 0 }, { 1 } }),
						"Caso 4. [2x1] x [2x1] = null")
				);
	}

	/**
	 * Comprueba casos básicos.
	 */
	@DisplayName("Casos básicos")
	@Test
	void casosBásicos() {
		assertAll("Comprobar casos básicos",
				() -> assertArrayEquals(new int[][] { {1, 2, 3}, { 4, 5, 6 } , {7, 8, 9} },
						MultiplicarMatrices.multiplicar(new int[][] { { 1, 0, 0 }, { 0, 1, 0 }, {0, 0, 1} },
								new int[][] { { 1, 2, 3 }, {4, 5, 6 }, { 7, 8, 9 } }),
						"Caso 0 (con identidad). [3x3] x [3x3] = [3x3]"),				
				() -> assertArrayEquals(new int[][] { {14, 20}, {32, 47} },
						MultiplicarMatrices.multiplicar(new int[][] { { 1, 2, 3 }, { 4, 5, 6 } },
								new int[][] { { 1, 2 }, { 2, 3 }, { 3, 4 } }),
						"Caso 1. [2x3] x [3x2] = [2x2]"),
				() -> assertArrayEquals(new int[][] { {8, 14}, {17, 32} },
						MultiplicarMatrices.multiplicar(new int[][] { { 1, 2, 3 }, { 4, 5, 6 } },
								new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 } }),
						"Caso 2. [2x3] x [3x2] = [2x2]"),
				() -> assertArrayEquals(new int[][] { {17, 23}, {38, 53}, {59, 83} },
						MultiplicarMatrices.multiplicar(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, {7, 8, 9} },
								new int[][] { { 1, 2 }, { 2, 3 }, { 4, 5} }),
						"Caso 3. [3x3] x [3x2] = [3x2]"),
				() -> assertArrayEquals(new int[][] { {1, 4, 2}, {-2, 0, -4} },
						MultiplicarMatrices.multiplicar(new int[][] { { 1, 2 }, { -2, 0 } },
								new int[][] { { 1, 0, 2 }, { 0, 2, 0 } }),
						"Caso 4 (con negativos). [2x2] x [2x3] = [2x3]"),
				() -> assertArrayEquals(new int[][] { {3, 1, 2}, {3, 0, 3}, {7, 3, 6} },
						MultiplicarMatrices.multiplicar(new int[][] { { 2, 0, 1 }, { 3, 0, 0 }, {5, 1, 1} },
								new int[][] { { 1, 0, 1 }, { 1, 2, 1 }, { 1, 1, 0} }),
						"Caso 5. [3x3] x [3x3] = [3x3]"));
	}
}
