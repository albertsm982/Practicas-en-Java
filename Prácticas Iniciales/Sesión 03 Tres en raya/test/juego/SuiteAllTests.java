/*
 * 
 */
package juego;


import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

/**
 * Suite ejecutando TODOS los tests del Tres en Raya.
 *
 * @author <a href="rmartico@ubu.es">Raúl Marticorena</a>
 * @version 1.0
 * @since 1.0
 */
@SelectPackages({
	"juego.control",
	"juego.modelo"})
@Suite
@SuiteDisplayName("Ejecución de todos los tests del ejercicio Tres en Raya.")
public class SuiteAllTests {
}
