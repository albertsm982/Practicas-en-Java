/*
 * 
 */
package juego.modelo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import juego.util.Direccion;

// TODO: Auto-generated Javadoc
/**
 * Tests sobre la implementación del tablero.
 *
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @version 1.0
 */
public class TableroTest {
	/** Coordenadas en un tablero de 3x3. */
	static final int[][] COORDENADAS_3x3 = { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 0 }, { 1, 1 }, { 1, 2 }, { 2, 0 },
			{ 2, 1 }, { 2, 2 } };

	/**
	 * Comprueba la correcta inicialización en tamaños de un tablero rectángular.
	 * 
	 * @param filas    filas
	 * @param columnas columnas
	 */
	@ParameterizedTest
	@CsvSource({ "1, 1", "2, 1", "3, 2", "4, 3", "5, 5", "6, 8" }) // Pares fila y columna
	@DisplayName("Constructor de tablero en tamaño correcto")
	void probarConstructor(int filas, int columnas) {
		Tablero tablero = new Tablero(filas, columnas);

		assertAll(() -> assertThat("Número de filas incorrectas", tablero.obtenerNumeroFilas(), is(filas)),
				() -> assertThat("Número de columnas incorrectas", tablero.obtenerNumeroColumnas(), is(columnas)));
	}

	/**
	 * Comprueba el correcto estado inicial vacío de un tablero, no completo y sin
	 * piezas de color blanco o negro.
	 * 
	 * @param filas    filas
	 * @param columnas columnas
	 */
	@ParameterizedTest
	@CsvSource({ "1, 1", "2, 1", "3, 2", "4, 3", "5, 5", "6, 8" }) // Pares fila y columna
	@DisplayName("Constructor de tablero con tablero no completo y sin piezas de cualquier color")
	void probarConstructorTableroNoCompletoSinPiezas(int filas, int columnas) {
		Tablero tablero = new Tablero(filas, columnas);

		assertAll(
				() -> assertFalse(tablero.estaCompleto(),
						"El tablero no puede estar completo si se acaba de instanciar"),
				() -> assertThat("Número de piezas blanco incorrecto", tablero.obtenerNumeroPiezas(Color.BLANCO),
						is(0)),
				() -> assertThat("Número de piezas negras incorrecto", tablero.obtenerNumeroPiezas(Color.NEGRO),
						is(0)));
	}

	/**
	 * Comprueba la correcta inicialización en tamaños menores que uno. En estos
	 * casos se debe acotar a tamaño 1 en la dimensión que corresponda.
	 * 
	 * @param filas             filas
	 * @param columnas          columnas
	 * @param filasEsperadas    filasEsperadas
	 * @param columnasEsperadas columnasEsperadas
	 */
	@ParameterizedTest
	@CsvSource({ "0, 1, 1, 1", "-1, 0, 1, 1", "0, -1, 1, 1" }) // Pares fila/columna y filaEsperada/columnaEsperada
	@DisplayName("Constructor de tablero con valores acotados")
	void probarConstructorConValoresAcotados(int filas, int columnas, int filasEsperadas, int columnasEsperadas) {
		Tablero tablero = new Tablero(filas, columnas);

		assertAll(() -> assertThat("Número de filas incorrectas", tablero.obtenerNumeroFilas(), is(filasEsperadas)),
				() -> assertThat("Número de columnas incorrectas", tablero.obtenerNumeroColumnas(),
						is(columnasEsperadas)));
	}

	/**
	 * Comprueba la correcta inicialización de celdas vacías y diferentes en un
	 * tablero. Se entiende por diferentes a que son objetos independientes con
	 * distinta identidad.
	 * 
	 * @param filas    filas
	 * @param columnas columnas
	 */
	@ParameterizedTest
	@CsvSource({ "1, 1", "2, 1", "3, 2", "4, 3", "5, 5", "6, 8" }) // Pares fila y columna
	@DisplayName("Constructor inicializa con celdas diferentes")
	void probarInicializacionCeldas(int filas, int columnas) {
		// Inicializamos
		Tablero tablero = new Tablero(filas, columnas);
		List<Celda> celdas = new ArrayList<>();
		// Comprobamos que todas las celdas del tabler están vacías y que son objetos
		// "diferentes"
		// (evitar compartir referencias)
		for (int i = 0; i < tablero.obtenerNumeroFilas(); i++) {
			for (int j = 0; j < tablero.obtenerNumeroColumnas(); j++) {
				Celda celda = tablero.obtenerCelda(i, j);
				assertTrue(tablero.estaEnTablero(i, j), "Las coordenadas utilizadas no están en el tablero");
				assertAll(() -> assertNotNull(celda, "La celda no puede ser nula"),
						() -> assertTrue(celda.estaVacia(), "La celda iniciamente debe estar vacía"),
						() -> assertThat("La celda no puede estar contenida previamente", celdas, not(hasItem(celda))));
				celdas.add(celda); // agregamos la celda
			}
		}
	}

	/**
	 * Comprueba que la colocación de una pieza en una celda es correcta. Se
	 * comprueba que se engancha tanto la pieza a la celda como la celda a la pieza.
	 */
	@Test
	@DisplayName("Correcto funcionamiento de colocar")
	void probarColocarEnTablero() {
		// Inicializaciones
		Tablero tablero = new Tablero(3, 3);
		Pieza pieza = new Pieza(Color.BLANCO);
		// Colocamos una pieza en la celda
		Celda celda = tablero.obtenerCelda(1, 1);
		tablero.colocar(pieza, celda); // de color blanco
		// Comprobamos el doble enganche..
		assertAll(() -> assertThat("Celda incorrectamente asignada a la pieza", pieza.obtenerCelda(), is(celda)),
				() -> assertThat("Pieza incorrectamente asignada a la celda", celda.obtenerPieza(), is(pieza)));
	}

	/**
	 * Comprueba el correcto número de piezas tras llenar un tablero de 3x3
	 * colocando alternativamente piezas blancas y negras.
	 * 
	 * El tablero contendrá finalmente:
	 * <p>
	 * {@code OXO}
	 * <p>
	 * {@code XOX}
	 * <p>
	 * {@code OXO}
	 */
	@Test
	@DisplayName("Colocación de piezas llenando el tablero")
	void probarRellenadoDelTablero() {
		// Inicializamos
		Tablero tablero = new Tablero(3, 3);
		rellenarTableroTresPorTres(COORDENADAS_3x3, tablero);
		// Comprobamos
		assertAll(
				() -> assertThat("Número de piezas blancas incorrecto", tablero.obtenerNumeroPiezas(Color.BLANCO),
						is(5)),
				() -> assertThat("Número de piezas negras incorrecto", tablero.obtenerNumeroPiezas(Color.NEGRO), is(4)),
				() -> assertTrue(tablero.estaCompleto(), " El tablero no está completo"));
	}

	/**
	 * Comprueba el correcto formato de la conversión a cadena de texto.
	 * 
	 * El tablero contendrá finalmente:
	 * <p>
	 * {@code OXO}
	 * <p>
	 * {@code XOX}
	 * <p>
	 * {@code OXO}
	 */
	@Test
	@DisplayName("Conversion a texto del tablero")
	void probarConversionATexto() {
		// Inicializamos
		Tablero tablero = new Tablero(3, 3);
		rellenarTableroTresPorTres(COORDENADAS_3x3, tablero);
		// Comprobamos
		String esperado = "OXO" + "\n" + "XOX" + "\n" + "OXO";
		assertEquals(esperado, tablero.toString(), "Texto incorrecto generado.");
	}

	/**
	 * Comprueba el correcto formato de la conversión a cadena de texto con un
	 * tablero vacío.
	 * 
	 * El tablero contendrá finalmente:
	 * <p>
	 * {@code ---}
	 * <p>
	 * {@code ---}
	 * <p>
	 * {@code ---}
	 */
	@Test
	@DisplayName("Conversion a texto del tablero vacío")
	void probarConversionATextoTableroVacio() {
		// Inicializamos
		Tablero tablero = new Tablero(3, 3);
		// En este caso NO rellenamos el tablero con piezas
		// Comprobamos
		final String vacio = "---";
		String esperado = vacio + "\n" + vacio + "\n" + vacio;
		assertEquals(esperado, tablero.toString(), "Texto incorrecto generado.");
	}

	/**
	 * Comprueba el correcto conteo de piezas del mismo color en la misma dirección.
	 * 
	 * El tablero contendrá:
	 * <p>
	 * {@code OXO}
	 * <p>
	 * {@code XOX}
	 * <p>
	 * {@code OXO} }
	 * 
	 * @param fila         fila
	 * @param columna      columna
	 * @param direccion    direccion
	 * @param numeroPiezas número de piezas del mismo color contiguas en la
	 *                     dirección dada
	 */
	@ParameterizedTest
	@MethodSource("crearParametrosCeldaDireccionYNumeroPiezas")
	@DisplayName("Conteo de piezas del mismo color en distintas direcciones")
	void probarConteoDePiezasDelMismoColorEnUnaDireccion(final int fila, final int columna, Direccion direccion,
			final int numeroPiezas) {
		// Inicializamos
		Tablero tablero = new Tablero(3, 3);
		rellenarTableroTresPorTres(COORDENADAS_3x3, tablero);
		final Celda celda = tablero.obtenerCelda(fila, columna);
		// Comprobamos
		assertAll(() -> assertThat("Número de piezas blancas incorrecto", tablero.contarPiezas(celda, direccion),
				is(numeroPiezas)), () -> assertTrue(tablero.estaCompleto(), " El tablero no está completo"));
	}

	/**
	 * Fuente de datos para comprobar conteo correcto de piezas en distintas
	 * direcciones.
	 *
	 * @return flujo de argumentos con coordenadas, direccion y numero de piezas
	 * @see TableroTest#generar(int, int, int...)
	 */
	@SuppressWarnings("unused")
	private static Stream<Arguments> crearParametrosCeldaDireccionYNumeroPiezas() {
		List<Arguments> arguments = new ArrayList<>();
		// Para cada celda Ej: (0,0) comprobamos el número de piezas iguales
		// en cada una de las cuatro direcciones horizonal-vertical-NOSE-SONE. Ej: 1, 1,
		// 3, 1
		arguments.addAll(generar(0, 0, 1, 1, 3, 1));
		arguments.addAll(generar(0, 1, 1, 1, 2, 2));
		arguments.addAll(generar(0, 2, 1, 1, 1, 3));
		// Segunda fila
		arguments.addAll(generar(1, 0, 1, 1, 2, 2));
		arguments.addAll(generar(1, 1, 1, 1, 3, 3));
		arguments.addAll(generar(1, 2, 1, 1, 2, 2));
		// Tercera fila
		arguments.addAll(generar(2, 0, 1, 1, 1, 3));
		arguments.addAll(generar(2, 1, 1, 1, 2, 2));
		arguments.addAll(generar(2, 2, 1, 1, 3, 1));
		return arguments.stream();
	}

	/**
	 * Genera la lista de argumentos a comparar para cada dirección.
	 * 
	 * @param fila         fila
	 * @param columna      columna
	 * @param numeroPiezas array con el numero de piezas para cada direccion
	 *                     concreta
	 * @return devuelve una lista (tupla) de la forma [fila, columna, direccion,
	 *         numeroPiezas]
	 */
	private static List<Arguments> generar(int fila, int columna, int... numeroPiezas) {
		List<Arguments> list = new ArrayList<>();
		list.add(Arguments.arguments(fila, columna, Direccion.HORIZONTAL, numeroPiezas[0]));
		list.add(Arguments.arguments(fila, columna, Direccion.VERTICAL, numeroPiezas[1]));
		list.add(Arguments.arguments(fila, columna, Direccion.DIAGONAL_NO_SE, numeroPiezas[2]));
		list.add(Arguments.arguments(fila, columna, Direccion.DIAGONAL_SO_NE, numeroPiezas[3]));
		return list;
	}

	/**
	 * Rellena un tablero con piedras blancas o negras en las coordenadas indicadas.
	 * 
	 * @param coordenadas coordenadas de celdas
	 * @param tablero     tablero
	 */
	private void rellenarTableroTresPorTres(final int[][] coordenadas, Tablero tablero) {
		// Rellenamos el tablero alternativamente de piezas blancos o negras hasta
		// llenarlo completamente (9 piezas)
		for (int i = 0; i < coordenadas.length; i++) {
			// si la suma de las coordenadas es par, blancas, si no, negras
			Color color = ((coordenadas[i][0] + coordenadas[i][1]) % 2 == 0) ? Color.BLANCO : Color.NEGRO;
			Pieza pieza = new Pieza(color);
			Celda celda = tablero.obtenerCelda(coordenadas[i][0], coordenadas[i][1]);
			tablero.colocar(pieza, celda);
		}
	}

	/**
	 * Comprueba que al consultar celdas fuera de un tablero devuelve null.
	 * 
	 * @param fila    fila
	 * @param columna columna
	 */
	@ParameterizedTest
	@CsvSource({ "-1, -1", "-1, 0", "0, -1", "3, 0", "0, 3", "2, -1", "3, 3", "4, 5" }) // Pares fila y columna
	@DisplayName("Constructor de tablero en tamaño correcto")
	void probarConsultaCeldaConValorNuloSiLasCoordenadasEstanFuera(int fila, int columna) {
		Tablero tablero = new Tablero(3, 3);
		assertNull(tablero.obtenerCelda(fila, columna), "Las coordenadas están fuera del tablero y debe devolver null");
	}

	/**
	 * Comprueba el correcto conteo de piezas a cero en un tablero vacío.
	 */
	@Test
	@DisplayName("Conteo de piezas del mismo color a cero en distintas direcciones en un tablero vacío")
	void probarConteoDePiezasEnTableroVacio() {
		// Inicializamos
		Tablero tablero = new Tablero(3, 3);
		// No rellenamos en este caso.
		// Comprobamos...
		for (int fila = 0; fila < tablero.obtenerNumeroFilas(); fila++) {
			for (int columna = 0; columna < tablero.obtenerNumeroColumnas(); columna++) {
				for (Direccion direccion : Direccion.values()) {
					Celda celda = tablero.obtenerCelda(fila, columna);
					assertThat("El número de piezas debería ser cero en un tablero vacío",
							tablero.contarPiezas(celda, direccion), is(0));
				}
			}
		}
	}

	/**
	 * Comprueba el correcto conteo de piezas con piezas en las esquinas del tablero.
	 *
	 * El tablero contendrá:
	 * <p>
	 * {@code O-O}
	 * <p>
	 * {@code ---}
	 * <p>
	 * {@code O-O} 
	 */
	@Test
	@DisplayName("Conteo de piezas del mismo color en distintas direcciones con piezas en las esquinas")
	void probarConteoDePiezasEnTableroConHuecosYPiezasEnEsquinas() {
		Tablero tablero = new Tablero(3, 3);
		// Colocamos 4 piezas
		Celda celda00 = tablero.obtenerCelda(0, 0);
		tablero.colocar(new Pieza(Color.BLANCO), celda00);
		Celda celda02 = tablero.obtenerCelda(0, 2);
		tablero.colocar(new Pieza(Color.BLANCO), celda02);
		Celda celda20 = tablero.obtenerCelda(2, 0);
		tablero.colocar(new Pieza(Color.BLANCO), celda20);
		Celda celda22 = tablero.obtenerCelda(2, 2);
		tablero.colocar(new Pieza(Color.BLANCO), celda22);
		
		// Leemos 5 celdas vacías...
		Celda celda01 = tablero.obtenerCelda(0, 1);
		Celda celda10 = tablero.obtenerCelda(1, 0);
		Celda celda11 = tablero.obtenerCelda(1, 1);
		Celda celda12 = tablero.obtenerCelda(1, 2);
		Celda celda21 = tablero.obtenerCelda(2, 1);
		
		assertAll(
				// primera fila
				() -> assertThat("Número incorrecto en conteo NO_SE en " + celda00.toString(), tablero.contarPiezas(celda00,  Direccion.DIAGONAL_NO_SE), is(1)),
				() -> assertThat("Número incorrecto en conteo SO_NE en " + celda00.toString(), tablero.contarPiezas(celda00,  Direccion.DIAGONAL_SO_NE), is(1)),
				() -> assertThat("Número incorrecto en conteo V en " + celda00.toString(), tablero.contarPiezas(celda00,  Direccion.VERTICAL), is(1)),
				() -> assertThat("Número incorrecto en conteo H en " + celda00.toString(), tablero.contarPiezas(celda00,  Direccion.HORIZONTAL), is(1)),
				
				() -> assertThat("Número incorrecto en conteo NO_SE en " + celda01.toString(), tablero.contarPiezas(celda01,  Direccion.DIAGONAL_NO_SE), is(0)),
				() -> assertThat("Número incorrecto en conteo SO_NE en " + celda01.toString(), tablero.contarPiezas(celda01,  Direccion.DIAGONAL_SO_NE), is(0)),
				() -> assertThat("Número incorrecto en conteo V en " + celda01.toString(), tablero.contarPiezas(celda01,  Direccion.VERTICAL), is(0)),
				() -> assertThat("Número incorrecto en conteo H en " + celda01.toString(), tablero.contarPiezas(celda01,  Direccion.HORIZONTAL), is(0)),					
				
				() -> assertThat("Número incorrecto en conteo NO_SE en " + celda02.toString(), tablero.contarPiezas(celda02,  Direccion.DIAGONAL_NO_SE), is(1)),
				() -> assertThat("Número incorrecto en conteo SO_NE en " + celda02.toString(), tablero.contarPiezas(celda02,  Direccion.DIAGONAL_SO_NE), is(1)),
				() -> assertThat("Número incorrecto en conteo V en " + celda02.toString(), tablero.contarPiezas(celda02,  Direccion.VERTICAL), is(1)),
				() -> assertThat("Número incorrecto en conteo H en " + celda02.toString(), tablero.contarPiezas(celda02,  Direccion.HORIZONTAL), is(1)),
								
				// fila intermedia
				
				() -> assertThat("Número incorrecto en conteo NO_SE en " + celda10.toString(), tablero.contarPiezas(celda10,  Direccion.DIAGONAL_NO_SE), is(0)),
				() -> assertThat("Número incorrecto en conteo SO_NE en " + celda10.toString(), tablero.contarPiezas(celda10,  Direccion.DIAGONAL_SO_NE), is(0)),
				() -> assertThat("Número incorrecto en conteo V en " + celda10.toString(), tablero.contarPiezas(celda10,  Direccion.VERTICAL), is(0)),
				() -> assertThat("Número incorrecto en conteo H en " + celda10.toString(), tablero.contarPiezas(celda10,  Direccion.HORIZONTAL), is(0)),
				
				() -> assertThat("Número incorrecto en conteo NO_SE en " + celda11.toString(), tablero.contarPiezas(celda11,  Direccion.DIAGONAL_NO_SE), is(0)),
				() -> assertThat("Número incorrecto en conteo SO_NE en " + celda11.toString(), tablero.contarPiezas(celda11,  Direccion.DIAGONAL_SO_NE), is(0)),
				() -> assertThat("Número incorrecto en conteo V en " + celda11.toString(), tablero.contarPiezas(celda11,  Direccion.VERTICAL), is(0)),
				() -> assertThat("Número incorrecto en conteo H en " + celda11.toString(), tablero.contarPiezas(celda11,  Direccion.HORIZONTAL), is(0)),
				
			
				() -> assertThat("Número incorrecto en conteo NO_SE en " + celda12.toString(), tablero.contarPiezas(celda12,  Direccion.DIAGONAL_NO_SE), is(0)),
				() -> assertThat("Número incorrecto en conteo SO_NE en " + celda12.toString(), tablero.contarPiezas(celda12,  Direccion.DIAGONAL_SO_NE), is(0)),
				() -> assertThat("Número incorrecto en conteo V en " + celda12.toString(), tablero.contarPiezas(celda12,  Direccion.VERTICAL), is(0)),
				() -> assertThat("Número incorrecto en conteo H en " + celda12.toString(), tablero.contarPiezas(celda12,  Direccion.HORIZONTAL), is(0)),
				
				// última fila
				
				() -> assertThat("Número incorrecto en conteo NO_SE en " + celda20.toString(), tablero.contarPiezas(celda20,  Direccion.DIAGONAL_NO_SE), is(1)),
				() -> assertThat("Número incorrecto en conteo SO_NE en " + celda20.toString(), tablero.contarPiezas(celda20,  Direccion.DIAGONAL_SO_NE), is(1)),
				() -> assertThat("Número incorrecto en conteo V en " + celda20.toString(), tablero.contarPiezas(celda20,  Direccion.VERTICAL), is(1)),
				() -> assertThat("Número incorrecto en conteo H en " + celda20.toString(), tablero.contarPiezas(celda20,  Direccion.HORIZONTAL), is(1)),
				
				() -> assertThat("Número incorrecto en conteo NO_SE en " + celda21.toString(), tablero.contarPiezas(celda21,  Direccion.DIAGONAL_NO_SE), is(0)),
				() -> assertThat("Número incorrecto en conteo SO_NE en " + celda21.toString(), tablero.contarPiezas(celda21,  Direccion.DIAGONAL_SO_NE), is(0)),
				() -> assertThat("Número incorrecto en conteo V en " + celda21.toString(), tablero.contarPiezas(celda21,  Direccion.VERTICAL), is(0)),
				() -> assertThat("Número incorrecto en conteo H en " + celda21.toString(), tablero.contarPiezas(celda21,  Direccion.HORIZONTAL), is(0)),
				
				() -> assertThat("Número incorrecto en conteo NO_SE en " + celda22.toString(), tablero.contarPiezas(celda22,  Direccion.DIAGONAL_NO_SE), is(1)),
				() -> assertThat("Número incorrecto en conteo SO_NE en " + celda22.toString(), tablero.contarPiezas(celda22,  Direccion.DIAGONAL_SO_NE), is(1)),
				() -> assertThat("Número incorrecto en conteo V en " + celda22.toString(), tablero.contarPiezas(celda22,  Direccion.VERTICAL), is(1)),
				() -> assertThat("Número incorrecto en conteo H en " + celda22.toString(), tablero.contarPiezas(celda22,  Direccion.HORIZONTAL), is(1))
				);
	}
	
	/**
	 * Comprueba el correcto conteo de piezas con piezas en cruz el centro del tablero.
	 *
	 * El tablero contendrá:
	 * <p>
	 * {@code -O-}
	 * <p>
	 * {@code OOO}
	 * <p>
	 * {@code -O-} 
	 */
	@Test
	@DisplayName("Conteo de piezas del mismo color en distintas direcciones con piezas en cruz")
	void probarConteoDePiezasEnTableroConCruzEnElCentroDelTablero() {
		Tablero tablero = new Tablero(3,3);
		// Colocamos 5 piezas
		Celda celda01 = tablero.obtenerCelda(0, 1);
		tablero.colocar(new Pieza(Color.BLANCO), celda01);
		Celda celda10 = tablero.obtenerCelda(1, 0);
		tablero.colocar(new Pieza(Color.BLANCO), celda10);
		Celda celda11 = tablero.obtenerCelda(1, 1);
		tablero.colocar(new Pieza(Color.BLANCO), celda11);
		Celda celda12 = tablero.obtenerCelda(1, 2);
		tablero.colocar(new Pieza(Color.BLANCO), celda12);
		Celda celda21 = tablero.obtenerCelda(2, 1);
		tablero.colocar(new Pieza(Color.BLANCO), celda21);
		
		// Leemos las 4 celdas vacías...
		Celda celda00 = tablero.obtenerCelda(0, 0);
		Celda celda02 = tablero.obtenerCelda(0, 2);
		Celda celda20 = tablero.obtenerCelda(2, 0);
		Celda celda22 = tablero.obtenerCelda(2, 2);
	
		assertAll(
				// primera fila
				() -> assertThat("Número incorrecto en conteo NO_SE en " + celda00.toString(), tablero.contarPiezas(celda00,  Direccion.DIAGONAL_NO_SE), is(0)),
				() -> assertThat("Número incorrecto en conteo SO_NE en " + celda00.toString(), tablero.contarPiezas(celda00,  Direccion.DIAGONAL_SO_NE), is(0)),
				() -> assertThat("Número incorrecto en conteo V en " + celda00.toString(), tablero.contarPiezas(celda00,  Direccion.VERTICAL), is(0)),
				() -> assertThat("Número incorrecto en conteo H en " + celda00.toString(), tablero.contarPiezas(celda00,  Direccion.HORIZONTAL), is(0)),
														
				() -> assertThat("Número incorrecto en conteo NO_SE en " + celda01.toString(), tablero.contarPiezas(celda01,  Direccion.DIAGONAL_NO_SE), is(2)),
				() -> assertThat("Número incorrecto en conteo SO_NE en " + celda01.toString(), tablero.contarPiezas(celda01,  Direccion.DIAGONAL_SO_NE), is(2)),
				() -> assertThat("Número incorrecto en conteo V en " + celda01.toString(), tablero.contarPiezas(celda01,  Direccion.VERTICAL), is(3)),
				() -> assertThat("Número incorrecto en conteo H en " + celda01.toString(), tablero.contarPiezas(celda01,  Direccion.HORIZONTAL), is(1)),
								
				() -> assertThat("Número incorrecto en conteo NO_SE en " + celda02.toString(), tablero.contarPiezas(celda02,  Direccion.DIAGONAL_NO_SE), is(0)),
				() -> assertThat("Número incorrecto en conteo SO_NE en " + celda02.toString(), tablero.contarPiezas(celda02,  Direccion.DIAGONAL_SO_NE), is(0)),
				() -> assertThat("Número incorrecto en conteo V en " + celda02.toString(), tablero.contarPiezas(celda02,  Direccion.VERTICAL), is(0)),
				() -> assertThat("Número incorrecto en conteo H en " + celda02.toString(), tablero.contarPiezas(celda02,  Direccion.HORIZONTAL), is(0)),
				
				// fila intermedia
				
				() -> assertThat("Número incorrecto en conteo NO_SE en " + celda10.toString(), tablero.contarPiezas(celda10,  Direccion.DIAGONAL_NO_SE), is(2)),
				() -> assertThat("Número incorrecto en conteo SO_NE en " + celda10.toString(), tablero.contarPiezas(celda10,  Direccion.DIAGONAL_SO_NE), is(2)),
				() -> assertThat("Número incorrecto en conteo V en " + celda10.toString(), tablero.contarPiezas(celda10,  Direccion.VERTICAL), is(1)),
				() -> assertThat("Número incorrecto en conteo H en " + celda10.toString(), tablero.contarPiezas(celda10,  Direccion.HORIZONTAL), is(3)),
				
				() -> assertThat("Número incorrecto en conteo NO_SE en " + celda11.toString(), tablero.contarPiezas(celda11,  Direccion.DIAGONAL_NO_SE), is(1)),
				() -> assertThat("Número incorrecto en conteo SO_NE en" + celda11.toString(), tablero.contarPiezas(celda11,  Direccion.DIAGONAL_SO_NE), is(1)),
				() -> assertThat("Número incorrecto en conteo V en " + celda11.toString(), tablero.contarPiezas(celda11,  Direccion.VERTICAL), is(3)),
				() -> assertThat("Número incorrecto en conteo H en " + celda11.toString(), tablero.contarPiezas(celda11,  Direccion.HORIZONTAL), is(3)),
				
				() -> assertThat("Número incorrecto en conteo NO_SE en " + celda12.toString(), tablero.contarPiezas(celda12,  Direccion.DIAGONAL_NO_SE), is(2)),
				() -> assertThat("Número incorrecto en conteo SO_NE en " + celda12.toString(), tablero.contarPiezas(celda12,  Direccion.DIAGONAL_SO_NE), is(2)),
				() -> assertThat("Número incorrecto en conteo V en " + celda12.toString(), tablero.contarPiezas(celda12,  Direccion.VERTICAL), is(1)),
				() -> assertThat("Número incorrecto en conteo H en " + celda12.toString(), tablero.contarPiezas(celda12,  Direccion.HORIZONTAL), is(3)),	
				
				// última fila
				
				() -> assertThat("Número incorrecto en conteo NO_SE en " + celda20.toString(), tablero.contarPiezas(celda20,  Direccion.DIAGONAL_NO_SE), is(0)),
				() -> assertThat("Número incorrecto en conteo SO_NE en " + celda20.toString(), tablero.contarPiezas(celda20,  Direccion.DIAGONAL_SO_NE), is(0)),
				() -> assertThat("Número incorrecto en conteo V en " + celda20.toString(), tablero.contarPiezas(celda20,  Direccion.VERTICAL), is(0)),
				() -> assertThat("Número incorrecto en conteo H en " + celda20.toString(), tablero.contarPiezas(celda20,  Direccion.HORIZONTAL), is(0)),				
				
				() -> assertThat("Número incorrecto en conteo NO_SE en " + celda21.toString(), tablero.contarPiezas(celda21,  Direccion.DIAGONAL_NO_SE), is(2)),
				() -> assertThat("Número incorrecto en conteo SO_NE en " + celda21.toString(), tablero.contarPiezas(celda21,  Direccion.DIAGONAL_SO_NE), is(2)),
				() -> assertThat("Número incorrecto en conteo V en " + celda21.toString(), tablero.contarPiezas(celda21,  Direccion.VERTICAL), is(3)),
				() -> assertThat("Número incorrecto en conteo H en " + celda21.toString(), tablero.contarPiezas(celda21,  Direccion.HORIZONTAL), is(1)),
				
				() -> assertThat("Número incorrecto en conteo NO_SE en " + celda22.toString(), tablero.contarPiezas(celda22,  Direccion.DIAGONAL_NO_SE), is(0)),
				() -> assertThat("Número incorrecto en conteo SO_NE en " + celda22.toString(), tablero.contarPiezas(celda22,  Direccion.DIAGONAL_SO_NE), is(0)),
				() -> assertThat("Número incorrecto en conteo V en " + celda22.toString(), tablero.contarPiezas(celda22,  Direccion.VERTICAL), is(0)),
				() -> assertThat("Número incorrecto en conteo H en" + celda22.toString(), tablero.contarPiezas(celda22,  Direccion.HORIZONTAL), is(0))
				);
	}
	
	
	/**
	 * Comprueba el correcto conteo de piezas con piezas de ambos colores y huecos.
	 *
	 * El tablero contendrá:
	 * <p>
	 * {@code OOX}
	 * <p>
	 * {@code O-X}
	 * <p>
	 * {@code XX-} 
	 */
	@Test
	@DisplayName("Conteo de piezas de diferentes colores con huecos")
	void probarConteoDePiezasDeDiferentesColoresConHuecos() {
		Tablero tablero = new Tablero(3,3);
		// Colocamos 7 piezas
		Celda celda00 = tablero.obtenerCelda(0, 0);
		tablero.colocar(new Pieza(Color.BLANCO), celda00);
		Celda celda01 = tablero.obtenerCelda(0, 1);
		tablero.colocar(new Pieza(Color.BLANCO), celda01);
		Celda celda02 = tablero.obtenerCelda(0, 2);
		tablero.colocar(new Pieza(Color.NEGRO), celda02);
		Celda celda10 = tablero.obtenerCelda(1, 0);
		tablero.colocar(new Pieza(Color.BLANCO), celda10);
		Celda celda12 = tablero.obtenerCelda(1, 2);
		tablero.colocar(new Pieza(Color.NEGRO), celda12);
		Celda celda20 = tablero.obtenerCelda(2, 0);
		tablero.colocar(new Pieza(Color.NEGRO), celda20);
		Celda celda21 = tablero.obtenerCelda(2, 1);
		tablero.colocar(new Pieza(Color.NEGRO), celda21);
		
		// Leemos las 2 celdas vacías...
		Celda celda11 = tablero.obtenerCelda(1, 1);
		Celda celda22 = tablero.obtenerCelda(2, 2);
	
		assertAll(
				// primera fila
				() -> assertThat("Número incorrecto en conteo NO_SE en " + celda00.toString(), tablero.contarPiezas(celda00,  Direccion.DIAGONAL_NO_SE), is(1)),
				() -> assertThat("Número incorrecto en conteo SO_NE en " + celda00.toString(), tablero.contarPiezas(celda00,  Direccion.DIAGONAL_SO_NE), is(1)),
				() -> assertThat("Número incorrecto en conteo V en " + celda00.toString(), tablero.contarPiezas(celda00,  Direccion.VERTICAL), is(2)),
				() -> assertThat("Número incorrecto en conteo H en " + celda00.toString(), tablero.contarPiezas(celda00,  Direccion.HORIZONTAL), is(2)),
				
				() -> assertThat("Número incorrecto en conteo NO_SE en " + celda01.toString(), tablero.contarPiezas(celda01,  Direccion.DIAGONAL_NO_SE), is(1)),
				() -> assertThat("Número incorrecto en conteo SO_NE en " + celda01.toString(), tablero.contarPiezas(celda01,  Direccion.DIAGONAL_SO_NE), is(2)),
				() -> assertThat("Número incorrecto en conteo V en " + celda01.toString(), tablero.contarPiezas(celda01,  Direccion.VERTICAL), is(1)),
				() -> assertThat("Número incorrecto en conteo H en " + celda01.toString(), tablero.contarPiezas(celda01,  Direccion.HORIZONTAL), is(2)),
				
				() -> assertThat("Número incorrecto en conteo NO_SE en " + celda02.toString(), tablero.contarPiezas(celda02,  Direccion.DIAGONAL_NO_SE), is(1)),
				() -> assertThat("Número incorrecto en conteo SO_NE en " + celda02.toString(), tablero.contarPiezas(celda02,  Direccion.DIAGONAL_SO_NE), is(1)),
				() -> assertThat("Número incorrecto en conteo V en " + celda02.toString(), tablero.contarPiezas(celda02,  Direccion.VERTICAL), is(2)),
				() -> assertThat("Número incorrecto en conteo H en " + celda02.toString(), tablero.contarPiezas(celda02,  Direccion.HORIZONTAL), is(1)),
				
				
				// fila intermedia				
				
				() -> assertThat("Número incorrecto en conteo NO_SE en " + celda10.toString(), tablero.contarPiezas(celda10,  Direccion.DIAGONAL_NO_SE), is(1)),
				() -> assertThat("Número incorrecto en conteo SO_NE en " + celda10.toString(), tablero.contarPiezas(celda10,  Direccion.DIAGONAL_SO_NE), is(2)),
				() -> assertThat("Número incorrecto en conteo V en " + celda10.toString(), tablero.contarPiezas(celda10,  Direccion.VERTICAL), is(2)),
				() -> assertThat("Número incorrecto en conteo H en " + celda10.toString(), tablero.contarPiezas(celda10,  Direccion.HORIZONTAL), is(1)),
				
				() -> assertThat("Número incorrecto en conteo NO_SE en " + celda11.toString(), tablero.contarPiezas(celda11,  Direccion.DIAGONAL_NO_SE), is(0)),
				() -> assertThat("Número incorrecto en conteo SO_NE en" + celda11.toString(), tablero.contarPiezas(celda11,  Direccion.DIAGONAL_SO_NE), is(0)),
				() -> assertThat("Número incorrecto en conteo V en " + celda11.toString(), tablero.contarPiezas(celda11,  Direccion.VERTICAL), is(0)),
				() -> assertThat("Número incorrecto en conteo H en " + celda11.toString(), tablero.contarPiezas(celda11,  Direccion.HORIZONTAL), is(0)),
				
				() -> assertThat("Número incorrecto en conteo NO_SE en " + celda12.toString(), tablero.contarPiezas(celda12,  Direccion.DIAGONAL_NO_SE), is(1)),
				() -> assertThat("Número incorrecto en conteo SO_NE en " + celda12.toString(), tablero.contarPiezas(celda12,  Direccion.DIAGONAL_SO_NE), is(2)),
				() -> assertThat("Número incorrecto en conteo V en " + celda12.toString(), tablero.contarPiezas(celda12,  Direccion.VERTICAL), is(2)),
				() -> assertThat("Número incorrecto en conteo H en " + celda12.toString(), tablero.contarPiezas(celda12,  Direccion.HORIZONTAL), is(1)),
				
				// última fila
				
				() -> assertThat("Número incorrecto en conteo NO_SE en " + celda20.toString(), tablero.contarPiezas(celda20,  Direccion.DIAGONAL_NO_SE), is(1)),
				() -> assertThat("Número incorrecto en conteo SO_NE en " + celda20.toString(), tablero.contarPiezas(celda20,  Direccion.DIAGONAL_SO_NE), is(1)),
				() -> assertThat("Número incorrecto en conteo V en " + celda20.toString(), tablero.contarPiezas(celda20,  Direccion.VERTICAL), is(1)),
				() -> assertThat("Número incorrecto en conteo H en " + celda20.toString(), tablero.contarPiezas(celda20,  Direccion.HORIZONTAL), is(2)),
								
				() -> assertThat("Número incorrecto en conteo NO_SE en " + celda21.toString(), tablero.contarPiezas(celda21,  Direccion.DIAGONAL_NO_SE), is(1)),
				() -> assertThat("Número incorrecto en conteo SO_NE en " + celda21.toString(), tablero.contarPiezas(celda21,  Direccion.DIAGONAL_SO_NE), is(2)),
				() -> assertThat("Número incorrecto en conteo V en " + celda21.toString(), tablero.contarPiezas(celda21,  Direccion.VERTICAL), is(1)),
				() -> assertThat("Número incorrecto en conteo H en " + celda21.toString(), tablero.contarPiezas(celda21,  Direccion.HORIZONTAL), is(2)),
				
				() -> assertThat("Número incorrecto en conteo NO_SE en " + celda22.toString(), tablero.contarPiezas(celda22,  Direccion.DIAGONAL_NO_SE), is(0)),
				() -> assertThat("Número incorrecto en conteo SO_NE en " + celda22.toString(), tablero.contarPiezas(celda22,  Direccion.DIAGONAL_SO_NE), is(0)),
				() -> assertThat("Número incorrecto en conteo V en " + celda22.toString(), tablero.contarPiezas(celda22,  Direccion.VERTICAL), is(0)),
				() -> assertThat("Número incorrecto en conteo H en" + celda22.toString(), tablero.contarPiezas(celda22,  Direccion.HORIZONTAL), is(0))
				);
	}

	/**
	 * Comprueba que no coloca cuando la pieza es nula.
	 */
	@Test
	@DisplayName("Intentar colocar con valor incorrecto de pieza")
	void colocarPiezaIncorrecta() {
		Tablero tablero = new Tablero(8, 8);
		Celda celda = tablero.obtenerCelda(0, 0);
		tablero.colocar(null, celda);
		assertThat("No debería asignar piez.", celda.obtenerPieza(), is(nullValue()));
	}

	/**
	 * Comprueba que no coloca cuando la celda es nula.
	 */
	@Test
	@DisplayName("Intentar colocar con valor incorrecto de celda nula")
	void colocarCeldaNulaIncorrecta() {
		Tablero tablero = new Tablero(8, 8);
		Pieza pieza = new Pieza(Color.BLANCO);
		tablero.colocar(pieza, null);
		assertThat("No debería asignar celda.", pieza.obtenerCelda(), is(nullValue()));
	}

	/**
	 * Comprueba que no coloca cuando la celda está fuera del tablero.
	 */
	@Test
	@DisplayName("Intentar colocar con valor incorrecto de celda fuera del tablero")
	void colocarCeldaFueraDelTableroIncorrecta() {
		Tablero tablero = new Tablero(8, 8);
		Pieza pieza = new Pieza(Color.BLANCO);
		Celda celda = new Celda(8, 8);
		tablero.colocar(pieza, celda);
		assertAll(() -> assertThat("No debería asignar celda.", pieza.obtenerCelda(), is(nullValue())),
				() -> assertThat("No debería asignar pieza.", celda.obtenerPieza(), is(nullValue())));
	}

}
