import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
* Tests sobre la implementación de la serie de fibonacci.
*
* @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a> 
* @version 1.0
*/
@DisplayName("Tests para el cálculo de fibonacci.")
public class FibonacciTest {

	/**
	 * Test sobre los primeros 13 valores de la serie de Fibonacci. Se utiliza
	 * parametrización de tipo CSV (Comma Separated Values). Se dan valores por
	 * pares, de la forma "valor-entrada, resultado-esperado".
	 * 
	 * @param entrada valor de entrada
	 * @param salida resultado de la serie de Fibonacci esperado para la entrada
	 */
	@ParameterizedTest
	@CsvSource({ "0, 0", "1, 1", "2, 1", "3, 2", "4, 3", "5, 5", "6, 8", "7, 13", "8, 21", "9, 34", "10, 55",
			"11, 89", "12, 144" })
	@DisplayName("Probando los primeros 13 valores de la serie de Fibonacci")
	void probarFibonacci(long entrada, long salida) {
		assertThat("Valor erróneo en serie de Fibonacci", Fibonacci.fibonacci(entrada), is(salida));
	}

}
