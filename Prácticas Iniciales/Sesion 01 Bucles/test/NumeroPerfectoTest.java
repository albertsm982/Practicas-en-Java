import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests sobre la implementación de la condición de número perfecto.
 * 
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @version 1.0
 */
@DisplayName("Tests para determinar si un número es perfecto.")
public class NumeroPerfectoTest {
	
	/** Lista de números perfectors entre 1 a 1000. */
	private static final List<Integer> NUMEROS_PERFECTOS_ENTRE_1_A_1000 = Arrays.asList(6, 28, 496, 8128);

	/**
	 * Test sobre la condición de número perfecto.
	 */
	@Test
	@DisplayName("Probando detección de números perfectos entre 1 a 10000.")
	void probarEsBisiesto() {
		// en este rango solo hay cuatro números perfectos: 6, 28, 496 y 8128
		for (int valor = 1; valor < 10001; valor++) {
			if (NUMEROS_PERFECTOS_ENTRE_1_A_1000.contains(valor)) { 
				assertTrue(NumeroPerfecto.esPerfecto(valor), valor + " no se detecta como número perfecto.");
			} else {
				assertFalse(NumeroPerfecto.esPerfecto(valor), valor + " no se debería detectar como número perfecto.");
			}
		}
	}
}
