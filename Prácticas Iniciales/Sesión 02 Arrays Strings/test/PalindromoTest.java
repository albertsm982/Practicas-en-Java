import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Tests sobre la implementación de la condición de palíndromo.
 *
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @version 1.0
 */
public class PalindromoTest {

	/**
	 * Test sobre la condición de ser palíndromo.
	 * 
	 * @param texto texto a comprobar
	 */
	@ParameterizedTest
	@ValueSource(strings = { "", "a", "aa", "ana", "abba", "abcba", "rodador", "atinobonita", "amolapacificapaloma",
			"amadaladama", "anallevaalosolaavellana", "SATORAREPOTENETOPERAROTAS" })
	@DisplayName("Probando condición de palíndromo.")
	void probarEsPalindromo(String texto) {
		char[] textoAProbar = texto.toCharArray();
		assertTrue(Palindromo.esPalindromo(textoAProbar), () -> texto + " NO es palíndromo.");
	}

	/**
	 * Test sobre la condición de textos que NO son palíndromos.
	 * 
	 * @param texto texto a comprobar
	 */
	@ParameterizedTest
	@ValueSource(strings = { "an", "na", "abca", "xav", "fsdjflsadkfj", "estocasieseiscatoes",
			"amadaledama", "anallevaalasolaavellana", "SATORAREPOTANETOPERAROTAS" })
	@DisplayName("Probando condición de NO palíndromo.")
	void probarNpEsPalindromo(String texto) {
		char[] textoAProbar = texto.toCharArray();
		assertFalse(Palindromo.esPalindromo(textoAProbar), () -> texto + " es palíndromo.");
	}
}
