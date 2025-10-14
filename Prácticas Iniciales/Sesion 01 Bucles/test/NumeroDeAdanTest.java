import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Tests sobre la implementación de la condición de número de Adán.
 * 
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @version 1.0
 */
@DisplayName("Tests para determinar si un número es de Adán.")
public class NumeroDeAdanTest {

	/** Lista de números de adán entre 0 a 1000. */
	private static final List<Integer> NUMEROS_DE_ADÁN_0_A_1000 = Arrays.asList(0, 1, 2, 3, 11, 12, 13, 21, 22, 31, 101,
			102, 103, 111, 112, 113, 121, 122, 201, 202, 211, 212, 221, 301, 311);

	
	/**
	 * Test sobre los números de Adán entre 0 a 100.
	 * 
	 * @param valor valor
	 */
	@DisplayName("Probando sobre los números de Adán entre 0 a 100")
	@ParameterizedTest
	@ValueSource(ints = { 0, 1, 2, 3, 11, 12, 13, 21, 22, 31})
	void probarEsNúmeroDeAdán(int valor) {
		assertTrue(NumeroDeAdan.esNumeroDeAdan(valor), valor + " no se detecta como número de Adán.");
	}
	
	/**
	 * Test sobre los números que no son de Adán entre 0 a 50.
	 * 
	 * @param valor valor
	 */
	@DisplayName("Probando sobre los números no de Adán entre 0 a 25")
	@ParameterizedTest
	@ValueSource(ints = { 4, 5, 6, 7, 8, 9, 10, 14, 15, 16, 17, 18, 19, 23,
			24, 25})
	void probarNoEsNúmeroDeAdán(int valor) {
		assertFalse(NumeroDeAdan.esNumeroDeAdan(valor), valor + " se detecta como número de Adán.");
	}
	
	/**
	 * Test sobre la condición de número de Adán sobre
	 * los números del 0 al 1000.
	 */
	@Test
	@DisplayName("Probando detección de números de Adán entre 0 a 1000.")
	void probarEsNúmeroDeAdán() {
		for (int valor = 0; valor < 1000; valor++) {
			if (NUMEROS_DE_ADÁN_0_A_1000.contains(valor)) {
				assertTrue(NumeroDeAdan.esNumeroDeAdan(valor), valor + " no se detecta como número de Adán.");
			} else {
				assertFalse(NumeroDeAdan.esNumeroDeAdan(valor),
						valor + " no se debería detectar como número de Adán.");
			}
		}
	}
}
