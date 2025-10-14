package quantik;


import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

/**
 * Suite ejecutando TODOS los tests de la práctica Quantik 2.0.
 * 
 * @author <a href="rmartico@ubu.es">Raúl Marticorena</a>
 * @since 2.0
 * @version 1.0
 */
@SelectPackages({
	"quantik.control",
	"quantik.modelo",
	"quantik.util",
	"quantik.undo"})
@Suite
@SuiteDisplayName("Ejecución de todos los tests de la práctica Quantik 2.0.")
public class SuiteAllTests {
}
