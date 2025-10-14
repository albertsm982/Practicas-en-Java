package quantik.undo;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Timeout;

/**
 * Tests unitarios sobre el mecanismo de deshacer basada en jugadas.
 * 
 * @author <a href="rmartico@ubu.es">Raúl Marticorena</a>
 * @since 1.0
 * @version 2.0
 */
@Tag("IntegrationTest")
@Timeout(value = 3, unit = TimeUnit.SECONDS) // Time out global para todos los tests salvo los de ciclo de vida
public class MaquinaDelTiempoConJugadasTest extends DeshacerTest {

	/**
	 * Inicialización.
	 */
	@BeforeEach
	void iniciar() {
		deshacer = new MaquinaDelTiempoConJugadas(new Date(), NUM_FILAS, NUM_COLUMNAS);
	}

	@Override
	MecanismoDeDeshacer obtenerDeshacerConFechaPersonalizada(Date date) {
		return new MaquinaDelTiempoConJugadas(date, NUM_FILAS, NUM_COLUMNAS);
	}
}
