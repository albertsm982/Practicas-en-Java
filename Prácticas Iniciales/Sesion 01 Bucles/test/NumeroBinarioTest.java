import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Tests sobre la conversión de números de binario a decimal.
 *
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @version 1.0
 */
@DisplayName("Tests para comprobación de número binarios.")
public class NumeroBinarioTest {

	/** Valor devuelto si no es un valor binario. */
	private static final long NO_BINARIO = -1L;

	/**
	 * Comprueba la detección de números binarios en un long conteniendo solo unos y
	 * ceros.
	 * 
	 * @param entrada conjunto de valores numéricos conteniendo solo unos y ceros
	 */
	@ParameterizedTest
	@ValueSource(longs = { 0, 1, 10, 11, 100, 101, 110, 111, 1000, 1001, 1010, 1011, 1100, 1101, 1110, 1111, 10000 })
	@DisplayName("Probando la deteccion de número largo expresado en binario con 1s y 0s")
	void probarEsBinario(long entrada) {
		assertTrue(NumeroBinario.esBinario(entrada), "Debería ser un número binario");
	}

	/**
	 * Comprueba la detección de números NO binarios en un long que NO contienen
	 * solo unos y ceros.
	 * 
	 * @param entrada conjunto de valores numéricos que NO contienen unos y ceros
	 */
	@ParameterizedTest
	@ValueSource(longs = { 2, 3, 4, 5, 6, 7, 8, 9, 12, 21, 22, 30, 31, 400, 407, 511, 609, 1002, 1020, 1031, 2001 })
	@DisplayName("Probando la deteccion de número largo NO expresado en binario con 1s y 0s")
	void probarNoEsBinario(long entrada) {
		assertFalse(NumeroBinario.esBinario(entrada), "NO debería ser un número binario");
	}

	/**
	 * Comprueba la correcta conversión de números long con valores binarios a su
	 * correspondiente valor en decimal.
	 * 
	 * @param entrada valores conteniendos solo unos y ceros
	 * @param salida correspondiente conversión a valor decimal
	 */
	@ParameterizedTest
	@CsvSource({ "0, 0", "1, 1", "10, 2", "11, 3", "100, 4", "101, 5", "110, 6", "111, 7", "1000, 8", "1001, 9",
			"1010, 10", "1011, 11", "1100, 12", "1101, 13", "1110, 14", "1111, 15", "10000, 16", "110001100, 396" })
	@DisplayName("Probando la conversión de números largos solo con 1s y 0s a valores en decimal")
	void convertirBinarioADecimal(long entrada, long salida) {
		assertThat("Valor erróneo en conversión de binario a decimal", NumeroBinario.obtenerValorDecimal(entrada),
				is(salida));
	}

	/**
	 * Comprueba la correcta conversión de números long que NO son valores binarios a su
	 * correspondiente valor por defecto que es {@value #NO_BINARIO}.
	 * 
	 * @param entrada valores numéricos que no expresan números binarios
	 */
	@ParameterizedTest
	@ValueSource(longs = { 2, 3, 7, 12, 21, 33, 41, 501, 1002, 2000, 100002 })
	@DisplayName("Probando que ante valores no binarios devuelve siempre -1")
	void convertirValoresNoBinariosADecimalDevuelveMenosUno(long entrada) {
		assertThat("Valor erróneo en conversión de no-binario a decimal", NumeroBinario.obtenerValorDecimal(entrada),
				is(NO_BINARIO));
	}
}
