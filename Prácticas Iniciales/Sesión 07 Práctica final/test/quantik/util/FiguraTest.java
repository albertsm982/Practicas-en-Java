package quantik.util;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Pruebas unitarias sobre la figura.
 * 
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena Sánchez</a>
 * @version 1.0 20220703
 * 
 */
@DisplayName("Tests unitarios sobre figura")
@Tag("UnitTest")
public class FiguraTest {
	
	/**
	 * Comprobar el correcto número de valores.
	 */
	@DisplayName("Probar número de valores definidos.")
	@Test
	public void probarNumeroValores() {
		assertThat("La enumeración Figura debe tener exactamente cuatro valores definidos.", Figura.values().length, is(4));
	}
		
	/**
	 * Correctos textos para cada figura.
	 */
	@DisplayName("Probar textos para cada valor de figura.")
	@Test
	public void probarTextos() {
		assertAll("comprobando textos correctos para cada valor del tipo enumerado ",
			() -> assertThat("Texto mal definido para CILINDRO.", 
					Figura.CILINDRO.aTexto(), is("CL")),
			
			() -> assertThat("Texto mal definido para CONO.",
					Figura.CONO.aTexto(), is("CN")),
			
			() -> assertThat("Texto mal definido para CUBO.", 
					Figura.CUBO.aTexto(), is("CB")),			
			
			() -> assertThat("Texto mal definido para ESFERA.", 
					Figura.ESFERA.aTexto(), is("ES"))	

			);			
	} 
	
}
