package tiempo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Tests sobre la implementación de la alarma.
 * 
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @version 1.0
 */
public class AlarmaTest {

	/** Texto informativo. */
	private static final String TEXTO_INFORMATIVO_DE_LA_EXCEPCION_INCORRECTO = "Texto informativo de la excepción incorrecto";

	/** Resultado esperado. */
	private static final String SE_ESPERABA_UNA_EXCEPCION_DE_TIPO_EXCEPTION = "Se esperaba una excepción de tipo Exception";

	/** Valor por defecto correcto para la hora. */
	private static final int HORA_CORRECTA = 0;
	
	/** Valor por defecto correcto para el minuto. */
	private static final int MINUTO_CORRECTO = 0;

	/**
	 * Comprueba el lanzamiento de excepción y texto de error generado con minutos incorrectos.
	 * 
	 * @param minuto valor incorrecto de minuto que genera la excepción
	 */
	@ParameterizedTest
	@ValueSource(ints = { -1000, -30, -1, 60, 61, 100, 1000 })
	@DisplayName("Probando el lanzamiento de excepción con minutos incorrectos")
	void comprobarMinutosIncorrectos(int minuto) {
		Exception ex = assertThrows(Exception.class, () -> new Alarma(HORA_CORRECTA, minuto),
				SE_ESPERABA_UNA_EXCEPCION_DE_TIPO_EXCEPTION);
		assertThat(TEXTO_INFORMATIVO_DE_LA_EXCEPCION_INCORRECTO, ex.getMessage(), is("Minuto incorrecto: " + minuto));
	}
	
	/**
	 * Comprueba el lanzamiento de excepción y texto de error generado con hora incorrecta.
	 * 
	 * @param hora valor incorrecto de hora que genera la excepción
	 */
	@ParameterizedTest
	@ValueSource(ints = { -23, -2, -1, 24, 25, 100, 1000 })
	@DisplayName("Probando el lanzamiento de excepción con hora incorrecta")
	void comprobarHorasIncorrectas(int hora) {
		Exception ex = assertThrows(Exception.class, () -> new Alarma(hora, MINUTO_CORRECTO),
				SE_ESPERABA_UNA_EXCEPCION_DE_TIPO_EXCEPTION);
		assertThat(TEXTO_INFORMATIVO_DE_LA_EXCEPCION_INCORRECTO, ex.getMessage(), is("Hora incorrecta: " + hora));
	}
	
	/**
	 * Comprueba el lanzamiento de excepción y texto de error generado con hora y minutos incorrectos.
	 * 
	 * @param hora valor incorrecto de hora que genera la excepción
	 * @param minuto valor incorrecto de minuto que genera la excepción
	 */
	@ParameterizedTest
	@CsvSource({ "-1, -1", "24, 60", "-10, -10", "-1, 60", "24, -1" })
	@DisplayName("Probando el lanzamiento de excepción con hora y minuto incorrectos")
	void comprobarHoraYMinutoIncorrectos(int hora, int minuto) {
		Exception ex = assertThrows(Exception.class, () -> new Alarma(hora, minuto),
				SE_ESPERABA_UNA_EXCEPCION_DE_TIPO_EXCEPTION);
		assertThat(TEXTO_INFORMATIVO_DE_LA_EXCEPCION_INCORRECTO, ex.getMessage(), is("Hora y minuto incorrectos: " + hora + ":" + minuto));
	}
	
	/**
	 * Comprueba la correcta inicialización con hora y minutos correctos.
	 * 
	 * @param hora hora correcta
	 * @param minuto minuto correcto
	 * @throws Exception si se detecta un error en la hora o minuto
	 */
	@ParameterizedTest
	@CsvSource({ "0, 0", "10, 25", "19, 33", "23, 59", "0, 58", "23, 0", "10, 55", "0, 1", "0, 59" })
	@DisplayName("Probando la inicialización con hora y minuto incorrectos")
	void comprobarHoraYMinutoCorrectos(int hora, int minuto) throws Exception {
		Alarma alarma = new Alarma(hora, minuto);
		assertAll(
				() -> assertThat("Hora mal inicializada", alarma.consultarHora(), is(hora)),
				() -> assertThat("Minuto mal inicializado", alarma.consultarMinuto(), is(minuto))
		);
	}
}
