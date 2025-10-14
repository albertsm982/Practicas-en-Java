/*
 * 
 */
package juego;


import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

/**
 * Suite ejecutando los tests del modelo del Tres en Raya.
 * 
 * Se deja como ejemplo de suite alternativa solo ejecutando los tests de un paquete.
 * Se puede ejecutar desde Eclipse con el botón derecho activando el menú contextual, opción Run ass... y JUnit Test.
 *
 * @author <a href="rmartico@ubu.es">Raúl Marticorena</a>
 * @version 1.0
 * @since 1.0
 */
@SelectPackages({
	"juego.modelo"})
@Suite
@SuiteDisplayName("Tests unitarios del paquete juego.modelo del Tres en Raya")
public class SuiteModeloTests {

}
