package genericidad;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import musica.Cuerda;
import musica.Instrumento;
import musica.Metal;
import musica.Viento;

/**
 * Test sobre la implementación de lista genérica acotada.
 *
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @version 1.0
 */
public class ListaTest {

	/** Dummy lista. Se implementan los tests sobre una lista de instrumentos. */
	private Lista<Instrumento> lista;

	/**
	 * Comprueba la correcta inicialización de una lista.
	 * 
	 * @param tamaño tamaño
	 */
	@ParameterizedTest
	@ValueSource(ints = { 1, 3, 10, 100 })
	@DisplayName("Comprueba la correcta inicialización en tamaño de la lista genérica acotada")
	void probarConstructorConTamañoInicial(int tamaño) {
		lista = new Lista<>(tamaño); // utilizando el operador diamante en instanciación
		assertThat("El tamaño da lista acotada no está correctamente inicializado", lista.size(), is(tamaño));
		for (int posicion = 0; posicion < tamaño; posicion++) {
			assertNull(lista.get(posicion), "Toda posición debe estar inicialmente a nulo");
		}
	}

	/**
	 * Comprueba el correcto establecimiento de valores y consulta en una lista. Se
	 * van añadiendo instrumentos secuencialmente hasta completar todas las
	 * posiciones libres.
	 */
	@Test
	@DisplayName("Comprueba el uso de método mutador set y acceso get")
	void probarMetodosMutadoresYDeConsulta() {
		lista = new Lista<>(3);
		Viento viento = new Viento(1.0F);
		Metal metal = new Metal(1.0F);
		Cuerda cuerda = new Cuerda(1.0F);
		lista.set(0, viento);
		assertAll(() -> assertThat(lista.get(0), is(viento)), () -> assertNull(lista.get(1)),
				() -> assertNull(lista.get(2)));
		lista.set(1, metal);
		assertAll(() -> assertThat(lista.get(0), is(viento)), () -> assertThat(lista.get(1), is(metal)),
				() -> assertNull(lista.get(2)));
		lista.set(2, cuerda);
		assertAll(() -> assertThat(lista.get(0), is(viento)), () -> assertThat(lista.get(1), is(metal)),
				() -> assertThat(lista.get(2), is(cuerda)));
		assertThat("El tamaño da lista acotada no debe cambiar", lista.size(), is(3));
	}

	/**
	 * Comprueba el correcto establecimiento de valores y consulta en una lista. Se
	 * van añadiendo instrumentos, en algunos casos pisando valores viejos,
	 * secuencialmente hasta completar todas las posiciones libres.
	 */
	@Test
	@DisplayName("Comprueba el uso de método mutador set y acceso get sobreescribiendo valores")
	void probarMetodosMutadoresYDeConsultaPisandoValores() {
		lista = new Lista<>(3);
		Viento viento = new Viento(1.0F);
		Metal metal = new Metal(1.0F);
		Cuerda cuerda = new Cuerda(1.0F);
		lista.set(0, viento);
		assertAll(() -> assertThat(lista.get(0), is(viento)), () -> assertNull(lista.get(1)),
				() -> assertNull(lista.get(2)));
		// se sobreescribe el instrumento de viento en la posición 0 con uno de metal
		lista.set(0, metal);
		assertAll(() -> assertThat(lista.get(0), is(metal)), () -> assertNull(lista.get(1)),
				() -> assertNull(lista.get(2)));
		lista.set(1, cuerda);
		assertAll(() -> assertThat(lista.get(0), is(metal)), () -> assertThat(lista.get(1), is(cuerda)),
				() -> assertNull(lista.get(2)));
		// se sobreescribe el instrumento de cuerda en la posición 0 con uno de viento
		lista.set(1, viento);
		assertAll(() -> assertThat(lista.get(0), is(metal)), () -> assertThat(lista.get(1), is(viento)),
				() -> assertNull(lista.get(2)));
		lista.set(2, cuerda);
		assertAll(() -> assertThat(lista.get(0), is(metal)), () -> assertThat(lista.get(1), is(viento)),
				() -> assertThat(lista.get(2), is(cuerda)));

		assertThat("El tamaño da lista acotada no debe cambiar", lista.size(), is(3));
	}

	/**
	 * Comprueba que devuelve nulos al consultar en posiciones fuera de la lista.
	 * 
	 * @param posicion posición
	 */
	@ParameterizedTest
	@ValueSource(ints = { -10, -1, 3, 4, 10 })
	@DisplayName("Comprueba el retorno de nulos en posiciones fuera de la lista")
	void comprobarMetodoGetFueraDeLimites(int posicion) {
		lista = new Lista<>(3); // se inicializa a tamaño 3
		assertNull(lista.get(posicion));
	}

	/**
	 * Comprueba que no hace nada (ni siquiera salta excepción) al intentar
	 * establecer valores fuera de los límites en una lista vacía. Los valores de la
	 * lista inicial no se modifican.
	 * 
	 * @param posicion posición
	 */
	@ParameterizedTest
	@ValueSource(ints = { -10, -1, 3, 4, 10 })
	@DisplayName("Comprueba que no cambia el estado al intentar establecer valores fuera de la lista")
	void comprobarMetodoSetFueraDeLimites(int posicion) {
		lista = new Lista<>(3); // se inicializa a tamaño 3, todas las posiciones a nulo
		lista.set(posicion, new Viento(0.0F));
		for (int pos = 0; pos < 3; pos++) {
			assertNull(lista.get(pos), "Toda posición debe seguir estando a nulo, sin alterar la lista");
		}
	}
}
