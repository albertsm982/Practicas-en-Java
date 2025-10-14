import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.junit.jupiter.api.Assertions.assertAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests sobre la implementación de un estadístico sobre array de números en punto flotante.
 *
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @version 1.0
 */
public class EstadisticoTest {

	/**
	 * Test sobre los resultados en un array con cero elementos.
	 */
	@Test
	@DisplayName("Calculos sobre un array con cero elementos")
	void probarArrayVacio() {
		double[] numeros = new double[0]; // Java permite esto...
		assertAll(() -> assertThat(Estadistico.obtenerMaximo(numeros), is(Double.NaN)),
				() -> assertThat(Estadistico.obtenerMinimo(numeros), is(Double.NaN)),
				() -> assertThat(Estadistico.obtenerMedia(numeros), is(0.0d))
		);
	}
	
	/**
	 * Test sobre los resultados en un array con un único valor.
	 */
	@Test
	@DisplayName("Calculos sobre un array con un numero")
	void probarArrayConUnSoloNumero() {
		double[] numeros = { 1.0 };
		assertAll(() -> assertThat(Estadistico.obtenerMaximo(numeros), is(1.0)),
				() -> assertThat(Estadistico.obtenerMinimo(numeros), is(1.0)),
				() -> assertThat(Estadistico.obtenerMedia(numeros), is(1.0))
		);
	}
	
	/**
	 * Test sobre los resultados en un array con varios valores.
	 */
	@Test
	@DisplayName("Calculos sobre un array largo")
	void probarArrayLargo() {
		double[] numeros = { 1.0, 2.0, 1.0, 3.5, 4.5, 4.5, -1.0, 9.73 };
		assertAll(() -> assertThat(Estadistico.obtenerMaximo(numeros), is(9.73)),
				() -> assertThat(Estadistico.obtenerMinimo(numeros), is(-1.0)),
				() -> assertThat(Estadistico.obtenerMedia(numeros), is(3.15375))
		);
	}
}
