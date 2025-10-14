

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/**
 * Tests ejercicio de rotación de cadena de izquierda a derecha.
 *
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @version 1.0
 */
public class RotacionTest {

	/**
	 * Comprueba casos límites positivos.
	 */
	@DisplayName("Casos límite positivos")
	@Test
	void límitesPositivos() {
		assertAll("Comprobar casos límite positivos",
				() -> assertTrue(Rotacion.esRotacion("".toCharArray(), "".toCharArray()), "Caso 1 sin caracteres"),
				() -> assertTrue(Rotacion.esRotacion("a".toCharArray(), "a".toCharArray()), "Caso 2 un solo carácter"),
				() -> assertTrue(Rotacion.esRotacion("ab".toCharArray(), "ba".toCharArray()),
						"Caso 3 con dos caracteres rotacion uno"),
				() -> assertTrue(Rotacion.esRotacion("ab".toCharArray(), "ab".toCharArray()),
						"Caso 3 con dos caracteres rotación cero"),
				() -> assertTrue(Rotacion.esRotacion("abc".toCharArray(), "cab".toCharArray()),
						"Caso 4 con tres caracteres rotacion uno a la derecha"),
				() -> assertTrue(Rotacion.esRotacion("abc".toCharArray(), "bca".toCharArray()),
						"Caso 4 con tres caracteres rotacion uno a la izquierda"),
				() -> assertTrue(Rotacion.esRotacion("abc".toCharArray(), "abc".toCharArray()),
						"Caso 4 con tres caracteres rotacion cero"),
				() -> assertTrue(Rotacion.esRotacion("holaquetal".toCharArray(), "olaquetalh".toCharArray()),
						"Caso 5 rotación de derecha a izquierda"));
	}

	/**
	 * Comprueba casos límites negativos.
	 */
	@DisplayName("Casos límite negativos")
	@Test
	void límitesNegativos() {
		assertAll("Comprobar casos límite negativos",
				() -> assertFalse(Rotacion.esRotacion("".toCharArray(), "a".toCharArray()), "Caso 1 diferente tamaño"),
				() -> assertFalse(Rotacion.esRotacion("a".toCharArray(), "".toCharArray()), "Caso 2 diferente tamaño"),
				() -> assertFalse(Rotacion.esRotacion("ab".toCharArray(), "bac".toCharArray()),
						"Caso 3 diferente tamaño"),
				() -> assertFalse(Rotacion.esRotacion("abc".toCharArray(), "ca".toCharArray()),
						"Caso 4 diferente tamaño"));
	}

	/**
	 * Comprueba casos básicos.
	 */
	@DisplayName("Casos básicos")
	@Test
	void básicos() {
		assertAll("Comprobar casos básicos",
				() -> assertTrue(Rotacion.esRotacion("holaquetal".toCharArray(), "talholaque".toCharArray()), "Caso 1"),
				() -> assertTrue(Rotacion.esRotacion("holaquetal".toCharArray(), "lholaqueta".toCharArray()), "Caso 2"),
				() -> assertTrue(Rotacion.esRotacion("holaquetal".toCharArray(), "quetalhola".toCharArray()), "Caso 3"),
				() -> assertTrue(Rotacion.esRotacion("holaquetal".toCharArray(), "alholaquet".toCharArray()),
						"Caso 4"));
	}

	/**
	 * Comprueba casos básicos complejos.
	 */
	@DisplayName("Casos complejos")
	@Test
	void complejos() {
		assertAll("Comprobar casos complejos",
				() -> assertTrue(Rotacion.esRotacion("holaquetalestamostodosConMayúsculasYÑhola".toCharArray(),
						"ConMayúsculasYÑholaholaquetalestamostodos".toCharArray()), "Caso 1"),
				() -> assertTrue(Rotacion.esRotacion("holaquetalestamostodosConMayúsculasYÑhola".toCharArray(),
						"tamostodosConMayúsculasYÑholaholaquetales".toCharArray()), "Caso 2"),
				() -> assertTrue(Rotacion.esRotacion("holaquetalestamostodosConMayúsculasYÑhola".toCharArray(),
						"úsculasYÑholaholaquetalestamostodosConMay".toCharArray()), "Caso 3"),
				() -> assertTrue(Rotacion.esRotacion("holaquetalestamostodosConMayúsculasYÑhola".toCharArray(),
						"aholaquetalestamostodosConMayúsculasYÑhol".toCharArray()), "Caso 4"));
	}

	/**
	 * Comprueba casos que NO son rotación.
	 */
	@DisplayName("Casos que NO son rotación")
	@Test
	void sinRotación() {
		assertAll("Comprobar casos que NO son rotación",
				() -> assertFalse(Rotacion.esRotacion("holaquetal".toCharArray(), "taxholaque".toCharArray()),
						"Caso 1 carácter x incorrecto"),
				() -> assertFalse(Rotacion.esRotacion("holaquetal".toCharArray(), "yholaqueta".toCharArray()),
						"Caso 2 carácter y incorrecto"),
				() -> assertFalse(Rotacion.esRotacion("holaquetal".toCharArray(), "quetalzola".toCharArray()),
						"Caso 3 carácter z incorrecto"),
				() -> assertFalse(Rotacion.esRotacion("holaquetal".toCharArray(), "talhVlaque".toCharArray()),
						"Caso 4 carácter V incorrecto"));
	}

	/**
	 * Comprueba casos con palíndromos.
	 */
	@DisplayName("Casos complejos con palíndromos")
	@Test
	void complejosConPalíndromos() {
		assertAll("Comprobar casos complejos con palíndromos",
				() -> assertTrue(Rotacion.esRotacion("SATORAREPOTENETOPERAROTAS".toCharArray(),
						"NETOPERAROTASSATORAREPOTE".toCharArray()), "Caso 1"),
				() -> assertTrue(Rotacion.esRotacion("SATORAREPOTENETOPERAROTAS".toCharArray(),
						"ROTASSATORAREPOTENETOPERA".toCharArray()), "Caso 2"),
				() -> assertTrue(Rotacion.esRotacion("SATORAREPOTENETOPERAROTAS".toCharArray(),
						"POTENETOPERAROTASSATORARE".toCharArray()), "Caso 3"),
				() -> assertTrue(Rotacion.esRotacion("SATORAREPOTENETOPERAROTAS".toCharArray(),
						"SSATORAREPOTENETOPERAROTA".toCharArray()), "Caso 4"));
	}

	/**
	 * Comprueba casos con falsos palíndromos.
	 */
	@DisplayName("Casos complejos con falsos palíndromos")
	@Test
	void complejosConFalsosPalíndromos() {
		assertAll("Comprobar casos complejos con falsos palíndromos",
				() -> assertFalse(Rotacion.esRotacion("SATORAREPOTENETOPERAROTAS".toCharArray(),
						"NETOPERRAOTASSATORAREPOTE".toCharArray()), "Caso 1"),
				() -> assertFalse(Rotacion.esRotacion("SATORAREPOTENETOPERAROTAS".toCharArray(),
						"ROTASSATORAREPOTEENTOPERA".toCharArray()), "Caso 2"),
				() -> assertFalse(Rotacion.esRotacion("SATORAREPOTENETOPERAROTAS".toCharArray(),
						"POTENTTOPERAROTASSATORARE".toCharArray()), "Caso 3"),
				() -> assertFalse(Rotacion.esRotacion("SATORAREPOTENETOPERAROTAS".toCharArray(),
						"SASTORAREPOTENETOPERAROTA".toCharArray()), "Caso 4"));
	}

}
