import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Tests sobre las condiciones y clasificación de triángulos.
 *
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @version 1.0
 */
@DisplayName("Tests para determinar condiciones y tipo de los tres lados de un triángulo.")
public class TrianguloTest {

	/**
	 * Test sobre valores que sí forman un triángulo.
	 * 
	 * @param lado1 lado
	 * @param lado2 lado
	 * @param lado3 lado
	 */
	@ParameterizedTest
	@CsvSource({ "1, 1, 1", "2, 2, 1", "3, 4, 2", "9, 9, 8" })
	@DisplayName("Probando que los tres valores forman triángulo")
	void probarEsTriangulo(float lado1, float lado2, float lado3) {
		assertTrue(Triangulo.esTriangulo(lado1, lado2, lado3),
				() -> "Sí debería formar un triángulo con lados: " + lado1 + "," + lado2 + "," + lado3);
	}

	/**
	 * Test sobre valores que NO forman un triángulo.
	 * 
	 * @param lado1 lado
	 * @param lado2 lado
	 * @param lado3 lado
	 */
	@ParameterizedTest
	@CsvSource({ "1, 2, 4", "1, 1, 4", "2, 3, 6" })
	@DisplayName("Probando que los tres valores NO forman triángulo")
	void probarNoEsTriangulo(float lado1, float lado2, float lado3) {
		assertFalse(Triangulo.esTriangulo(lado1, lado2, lado3),
				() -> "No debería formar un triángulo con lados: " + lado1 + "," + lado2 + "," + lado3);
	}

	/**
	 * Test sobre la clasificación del tipo de triángulo.
	 * 
	 * @param lado1 lado
	 * @param lado2 lado
	 * @param lado3 lado
	 * @param salida texto con el tipo de triángulo esperado
	 */
	@ParameterizedTest
	@CsvSource({ "1, 1, 1, equilatero", "2, 2, 1, isosceles", "2, 3, 4, escaleno" }) // se omiten tildes
	@DisplayName("Probando que clasifica bien el triángulo")
	void probarClasificacionTriangulo(float lado1, float lado2, float lado3, String salida) {
		assertThat("Valor erróneo en clasificación", convertir(Triangulo.consultarTipoTriangulo(lado1, lado2, lado3)), is(salida));
	}
	
	/**
	 * Convierte la cadena, eliminando caracteres que puedan impedir la comparación positiva en el test.
	 * 
	 * @param entrada texto inicial
	 * @return texto sin espacios en blanco, en minúsculas y sin tildes
	 */
	private static String convertir(String entrada) {
		// eliminamos espacios en blanco al principio y al final
		// convertimos a minúsculas
		// reemplaza tilde en á si existe
		// reemplaza tilde en ó si existe
		entrada = entrada.strip().toLowerCase().replaceAll("á","a").replaceAll("ó","o"); 	
		return entrada; // 
	}
}
