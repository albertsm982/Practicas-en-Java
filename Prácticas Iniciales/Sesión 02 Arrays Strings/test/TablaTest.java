import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests sobre el manejo de arrays de dos dimensiones en forma de tabla.
 *
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @version 1.0
 */
public class TablaTest {

	/**
	 * Comprueba la inicialización correcta de un array 2x2.
	 */
	@Test
	@DisplayName("Inicialización de array pequeño")
	public void inicializar() {
		int[][] tabla = new int[2][2];
		final int[][] tablaEsperada = { { 1, 2 }, { 3, 4 } };
		Tabla.inicializar(tabla);
		assertThat(tabla, is(tablaEsperada));
	}

	/**
	 * Comprueba la inicialización correcta de un array medio de 4x5.
	 */
	@Test
	@DisplayName("Inicialización de array medio")
	public void inicializarArrayMedio() {
		int[][] tabla = new int[4][5];
		final int[][] tablaEsperada = { { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 }, { 11, 12, 13, 14, 15 }, { 16, 17, 18, 19, 20 } };
		Tabla.inicializar(tabla);
		assertThat(tabla, is(tablaEsperada));
	}
	

	/**
	 * Comprueba que elevar al cuadrado cada uno de los elementos de un array 2x2.
	 */
	@Test
	@DisplayName("Elevar al cuadrado tabla pequeña")
	public void elevarAlCuadradoTablaPequeña() {
		int[][] tabla = { { 1, 2 }, { 3, 4 } };
		final int[][] tablaEsperada = { { 1, 4 }, { 9, 16 } };
		Tabla.elevarAlCuadrado(tabla);
		assertThat(tabla, is(tablaEsperada));
	}
	
	/**
	 * Comprueba que elevar al cuadrado cada uno de los elementos de un array 4x5.
	 */
	@Test
	@DisplayName("Elevar al cuadrado tabla media")
	public void elevarAlCuadradoTablaMedia() {
		int[][] tabla = { { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 }, { 11, 12, 13, 14, 15 }, { 16, 17, 18, 19, 20 } };
		final int[][] tablaEsperada = { { 1, 4, 9, 16, 25}, { 36, 49, 64, 81, 100 }, { 121, 144, 169, 196, 225 }, { 256, 289, 324, 361, 400 } };		
		Tabla.elevarAlCuadrado(tabla);
		assertThat(tabla, is(tablaEsperada));
	}
	
}
