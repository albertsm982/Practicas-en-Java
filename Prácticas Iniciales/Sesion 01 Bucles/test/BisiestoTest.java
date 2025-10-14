import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Tests sobre la implementación de la condición de bisiesto en un año.
 *
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @version 1.0
 */
@DisplayName("Tests para determinar si un año es bisiesto o no.")
public class BisiestoTest {

	/**
	 * Test sobre la condición de años realmente bisiestos.
	 * 
	 * @param año año bisiesto
	 */
	@ParameterizedTest
	@ValueSource(ints = { 1904, 1940, 1972, 1992, 2000, 2004, 2012, 2020, 2060, 2088, 2096, 2104, 2144, 2168, 2196 })
	@DisplayName("Probando condición en años bisiestos.")
	void probarEsBisiesto(int año) {
		assertTrue(Bisiesto.esBisiesto(año), () -> año + " debería ser bisiesto.");
	}

	/**
	 * Test sobre la condición de años que NO son bisiestos.
	 * 
	 * @param año año NO bisiesto
	 */
	@ParameterizedTest
	@ValueSource(ints = { 1906, 1934, 1985, 2001, 2005, 2013, 2054, 2100, 2174, 2122, 2123, 2142, 2186, 2200 })
	@DisplayName("Probando condición en años NO bisiestos.")
	void probarNoEsBisiesto(int año) {
		assertFalse(Bisiesto.esBisiesto(año), () -> año + " no debería ser bisiesto.");
	}
}
