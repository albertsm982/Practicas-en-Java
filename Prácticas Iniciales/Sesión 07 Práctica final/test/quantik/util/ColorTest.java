package quantik.util;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Pruebas unitarias sobre el color.
 * 
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena Sánchez</a>
 * @version 1.0 20220703
 * 
 */
@DisplayName("Tests unitarios sobre color")
@Tag("UnitTest")
public class ColorTest {
	
	/**
	 * Comprobar el correcto número de valores.
	 */
	@DisplayName("Probar número de valores definidos.")
	@Test
	public void probarNumeroValores() {
		assertThat("La enumeración Color debe tener exactamente dos valores definidos.", Color.values().length, is(2));
	}
		
	/**
	 * Correctos textos para cada color.
	 */
	@DisplayName("Probar textos para cada valor de color.")
	@Test
	public void probarTextos() {
		assertAll("comprobando textos correctos para cada valor del tipo enumerado ",
			() -> assertThat("Texto mal definido para BLANCO.", 
					Color.BLANCO.toChar(), is('B')),
			
			() -> assertThat("Texto mal definido para NEGRO.",
					Color.NEGRO.toChar(), is('N'))

			);			
	} 
	
}
