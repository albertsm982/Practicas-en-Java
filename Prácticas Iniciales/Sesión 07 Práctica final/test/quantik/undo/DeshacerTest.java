package quantik.undo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import quantik.control.Partida;
import quantik.excepcion.CoordenadasIncorrectasException;
import quantik.modelo.Caja;
import quantik.modelo.Pieza;
import quantik.modelo.Tablero;
import quantik.util.Color;
import quantik.util.Figura;

/**
 * Tests unitarios sobre el mecanismo de deshacer.
 * 
 * @author <a href="rmartico@ubu.es">Raúl Marticorena</a>
 * @since 1.0
 * @version 2.0
 */
@Timeout(value = 3, unit = TimeUnit.SECONDS) // Time out global para todos los tests salvo los de ciclo de vida
public abstract class DeshacerTest {
	
	/** Numero de filas. */
	static final int NUM_COLUMNAS = 4;
	
	/** Numero de columnas. */
	static final int NUM_FILAS = 4;

	/**
	 * Mecanismo a testar.
	 */
	MecanismoDeDeshacer deshacer;
		
	/**
	 * Obtiene un mecanismo de deshacer con fecha personalizada.
	 * 
	 * @param date fecha de inicialización
	 * @return mecanismo de deshacer
	 */
	abstract MecanismoDeDeshacer obtenerDeshacerConFechaPersonalizada(Date date);
	
	/**
	 * Comprueba correcta inicialización con fecha.
	 */
	@Test
	@DisplayName("Comprobar que inicializa correctamente con la fecha.")
	void inicializacionConFecha() {
		MecanismoDeDeshacer deshacerLocal = obtenerDeshacerConFechaPersonalizada(new Date(0,1,2,3,4,5));
		assertThat(deshacerLocal.obtenerFechaInicio(), is(new Date(0,1,2,3,4,5)));
	}

	/**
	 * Comprueba si deshace bien si no hay jugada previa.
	 */
	@Test
	@DisplayName("Comprobar que deshace correctamente si no ha habido ninguna jugada previa.")
	void probarDeshacerSinHaberGrabadoNingunaJugada() {
		Partida partidaInicial = generarPartidaInicial();
		deshacer.deshacerJugada();
		Partida partida = deshacer.consultarPartidaActual();		
		assertThat("Si no hay jugadas grabadas debería devolver un valor nulo.", partida, is(partidaInicial));
	}
	
	/**
	 * Comprueba que gestiona bien el contador de jugadas a deshacer si no había jugada previa.
	 */
	@Test
	@DisplayName("Comprobar que gestiona correctamente el contador si no había ninguna jugada previa al deshacer.")
	void probarGestionDeContadorSinHaberGrabadoNingunaJugada() {
		assertThat("No debe haber jugadas a deshacer al iniciar.", deshacer.consultarNumeroJugadasEnHistorico(),is(0));
		deshacer.deshacerJugada();
		assertThat("No deber haber jugadas a deshacer, pese a que se haya invocado una vez sin jugada a deshacer.", deshacer.consultarNumeroJugadasEnHistorico(),is(0));
	}

	/**
	 * Comprueba si deshace bien si solo hay una jugada previa.
	 * 
	 * @throws CoordenadasIncorrectasException si las coordenadas están fuera del tablero
	 */
	@Test
	@DisplayName("Comprobar si deshace correctamente si solo había una jugada previa.")
	void probarDeshacerGrabandoUnaJugada() throws CoordenadasIncorrectasException {
		deshacer.hacerJugada(0, 0, Figura.CILINDRO, Color.BLANCO);
		deshacer.deshacerJugada();
		Partida partida = deshacer.consultarPartidaActual();	
		Tablero tablero = partida.consultarTablero();
		assertAll("comprobar que deshacer con solo una jugada grabada devuelve una partida en estado inicial",
				() -> assertThat("El número de jugadas en el histórico es incorrecto.", deshacer.consultarNumeroJugadasEnHistorico(),is(0)),
				() -> assertThat("La celda donde hemos colocado una figura inicialmente debería estar vacía.",
						tablero.consultarCelda(0, 0).estaVacia(), is(true)),
				() -> assertThat("El turno es incorrecto.", partida.consultarTurno(), is(Color.BLANCO)),
				() -> assertThat("El contador de jugadas es incorrecto.", partida.consultarNumeroJugada(), is(0)),
				() -> assertThat("No debería haber ganador.", partida.consultarGanador(), is(nullValue())),
				() -> assertThat("No debería estar bloqueado el turno actual.", partida.estaBloqueadoTurnoActual(),
						is(false)),
				() -> assertThat("No debería haber grupo ganador.", partida.hayAlgunGrupoCompleto(), is(false)),
				() -> assertThat("La partida no debería estar acabada.", partida.estaAcabadaPartida(), is(false))

		);
	}

	/**
	 * Deshacer con dos jugadas.
	 *
	 */
	@Nested
	@DisplayName("Deshacer tras dos jugadas")
	class ConDosJugadas {

		/**
		 * Comprueba que deshace correctamente deshaciendo solo una jugada.
		 * 
		 * @throws CoordenadasIncorrectasException si las coordenadas están fuera del tablero
		 */
		@Test
		@DisplayName("Comprobar que deshacer correctamente una jugada.")
		void probarDeshacerGrabandoDosJugadasDeshaciendoSoloUna() throws CoordenadasIncorrectasException {
			// given
			deshacer.hacerJugada(0, 0, Figura.CILINDRO, Color.BLANCO);
			deshacer.hacerJugada(2, 2, Figura.CILINDRO, Color.NEGRO);
			// when
			deshacer.deshacerJugada();
			Partida partida = deshacer.consultarPartidaActual();	
			// then
			Tablero tablero = partida.consultarTablero();
			assertAll("comprobar que deshacer una jugada con dos jugadas grabadas devuelve una partida correcta",
					() -> assertThat("El número de jugadas en el histórico es incorrecto.", deshacer.consultarNumeroJugadasEnHistorico(),is(1)),
					() -> assertThat(
							"La celda donde hemos colocado una figura en la primera jugada debería contener un cilindro blanco.",
							tablero.consultarCelda(0, 0).consultarPieza(),
							is(new Pieza(Figura.CILINDRO, Color.BLANCO))),
					() -> assertThat(
							"La celda donde hemos colocado una figura en la segunda jugada debería estar vacía.",
							tablero.consultarCelda(2, 2).estaVacia(), is(true)),
					() -> assertThat("El turno es incorrecto.", partida.consultarTurno(), is(Color.NEGRO)),
					() -> assertThat("El contador de jugadas es incorrecto.", partida.consultarNumeroJugada(), is(1)),
					() -> assertThat("No debería haber ganador.", partida.consultarGanador(), is(nullValue())),
					() -> assertThat("No debería estar bloqueado el turno actual.", partida.estaBloqueadoTurnoActual(),
							is(false)),
					() -> assertThat("No debería haber grupo ganador.", partida.hayAlgunGrupoCompleto(), is(false)),
					() -> assertThat("La partida no debería estar acabada.", partida.estaAcabadaPartida(), is(false))

			);
		}
		
		/**
		 * Comprueba que deshace correctamente deshaciendo dos jugadas.
		 * 
		 * @throws CoordenadasIncorrectasException si las coordenadas están fuera del tablero
		 */
		@Test
		@DisplayName("Comprobar que deshacer correctamente las dos jugadas.")
		void probarDeshacerGrabandoDosJugadasDeshaciendoLasDos() throws CoordenadasIncorrectasException {
			// given
			deshacer.hacerJugada(0, 0, Figura.CILINDRO, Color.BLANCO);
			deshacer.hacerJugada(2, 2, Figura.CILINDRO, Color.NEGRO);
			// when
			deshacer.deshacerJugada(); 
			deshacer.deshacerJugada();
			Partida partida = deshacer.consultarPartidaActual();
			// then
			Tablero tablero = partida.consultarTablero();
			assertAll("comprobar que deshacer dos jugadas con dos jugadas grabadas devuelve una partida inicial",
					() -> assertThat("El número de jugadas en el histórico es incorrecto.", deshacer.consultarNumeroJugadasEnHistorico(),is(0)),

					() -> assertThat("La celda donde hemos colocado una figura inicialmente debería estar vacía.",
							tablero.consultarCelda(0, 0).estaVacia(), is(true)),
					() -> assertThat("La celda donde hemos colocado una figura a continuación debería estar vacía.",
							tablero.consultarCelda(2, 2).estaVacia(), is(true)),
					() -> assertThat("El turno es incorrecto.", partida.consultarTurno(), is(Color.BLANCO)),
					() -> assertThat("El contador de jugadas es incorrecto.", partida.consultarNumeroJugada(), is(0)),
					() -> assertThat("No debería haber ganador.", partida.consultarGanador(), is(nullValue())),
					() -> assertThat("No debería estar bloqueado el turno actual.", partida.estaBloqueadoTurnoActual(),
							is(false)),
					() -> assertThat("No debería haber grupo ganador.", partida.hayAlgunGrupoCompleto(), is(false)),
					() -> assertThat("La partida no debería estar acabada.", partida.estaAcabadaPartida(), is(false))

			);
		}
	}
	
	/**
	 * Deshacer con tres jugadas.
	 *
	 */
	@Nested
	@DisplayName("Deshacer tras tres jugadas")
	class ConTresJugadas {

		/**
		 * Comprueba que deshace correctamente deshaciendo solo una jugada.
		 * 
		 * @throws CoordenadasIncorrectasException si las coordenadas están fuera del tablero
		 */
		@Test
		@DisplayName("Comprobar que deshacer correctamente una jugada.")
		void probarDeshacerGrabandoTresJugadasDeshaciendoSoloUna() throws CoordenadasIncorrectasException {
			// given
			deshacer.hacerJugada(0, 0, Figura.CILINDRO, Color.BLANCO);
			deshacer.hacerJugada(2, 2, Figura.CILINDRO, Color.NEGRO);
			deshacer.hacerJugada(3, 2, Figura.CONO, Color.BLANCO);
			// when
			deshacer.deshacerJugada();
			Partida partida = deshacer.consultarPartidaActual();
			// then
			Tablero tablero = partida.consultarTablero();
			
			assertAll("comprobar que deshacer una jugada con tres jugadas grabadas devuelve una partida correcta con dos jugadas",
					() -> assertThat("El número de jugadas en el histórico es incorrecto.", deshacer.consultarNumeroJugadasEnHistorico(),is(2)),

					() -> assertThat(
							"La celda donde hemos colocado una figura en la primera jugada debería contener un cilindro blanco.",
							tablero.consultarCelda(0, 0).consultarPieza(),
							is(new Pieza(Figura.CILINDRO, Color.BLANCO))),
					() -> assertThat(
							"La celda donde hemos colocado una figura en la segunda jugada debería contener un cilindro negro.",
							tablero.consultarCelda(2, 2).consultarPieza(),
							is(new Pieza(Figura.CILINDRO, Color.NEGRO))),
					() -> assertThat(
							"La celda donde hemos colocado una figura en la segunda jugada debería estar vacía.",
							tablero.consultarCelda(3, 2).estaVacia(), is(true)),
					() -> assertThat("El turno es incorrecto.", partida.consultarTurno(), is(Color.BLANCO)),
					() -> assertThat("El contador de jugadas es incorrecto.", partida.consultarNumeroJugada(), is(2)),
					() -> assertThat("No debería haber ganador.", partida.consultarGanador(), is(nullValue())),
					() -> assertThat("No debería estar bloqueado el turno actual.", partida.estaBloqueadoTurnoActual(),
							is(false)),
					() -> assertThat("No debería haber grupo ganador.", partida.hayAlgunGrupoCompleto(), is(false)),
					() -> assertThat("La partida no debería estar acabada.", partida.estaAcabadaPartida(), is(false))

			);
		}
		
		/**
		 * Comprueba que deshace correctamente deshaciendo dos jugadas.
		 * 
		 * @throws CoordenadasIncorrectasException si las coordenadas están fuera del tablero
		 */
		@Test
		@DisplayName("Comprobar que deshacer correctamente dos jugadas.")
		void probarDeshacerGrabandoTresJugadasDeshaciendoDos() throws CoordenadasIncorrectasException {
			// given
			deshacer.hacerJugada(0, 0, Figura.CILINDRO, Color.BLANCO);
			deshacer.hacerJugada(2, 2, Figura.CILINDRO, Color.NEGRO);
			deshacer.hacerJugada(3, 2, Figura.CONO, Color.BLANCO);
			// when
			deshacer.deshacerJugada(); 
			deshacer.deshacerJugada();
			Partida partida = deshacer.consultarPartidaActual();
			// then
			Tablero tablero = partida.consultarTablero();
			assertAll("comprobar que deshacer dos jugadas con tres jugadas grabadas devuelve una partida con una jugada",
					() -> assertThat("El número de jugadas en el histórico es incorrecto.", deshacer.consultarNumeroJugadasEnHistorico(),is(1)),

					() -> assertThat(
							"La celda donde hemos colocado una figura en la primera jugada debería contener un cilindro blanco.",
							tablero.consultarCelda(0, 0).consultarPieza(),
							is(new Pieza(Figura.CILINDRO, Color.BLANCO))),
					() -> assertThat("La celda donde hemos colocado una figura a continuación debería estar vacía.",
							tablero.consultarCelda(2, 2).estaVacia(), is(true)),
					() -> assertThat("La celda donde hemos colocado una figura a continuación debería estar vacía.",
							tablero.consultarCelda(3, 2).estaVacia(), is(true)),
					() -> assertThat("El turno es incorrecto.", partida.consultarTurno(), is(Color.NEGRO)),
					() -> assertThat("El contador de jugadas es incorrecto.", partida.consultarNumeroJugada(), is(1)),
					() -> assertThat("No debería haber ganador.", partida.consultarGanador(), is(nullValue())),
					() -> assertThat("No debería estar bloqueado el turno actual.", partida.estaBloqueadoTurnoActual(),
							is(false)),
					() -> assertThat("No debería haber grupo ganador.", partida.hayAlgunGrupoCompleto(), is(false)),
					() -> assertThat("La partida no debería estar acabada.", partida.estaAcabadaPartida(), is(false))

			);
		}
		
		/**
		 * Comprueba que deshace correctamente deshaciendo tres jugadas.
		 * 
		 * @throws CoordenadasIncorrectasException si las coordenadas están fuera del tablero
		 */
		@Test
		@DisplayName("Comprobar que deshacer correctamente tres jugadas.")
		void probarDeshacerGrabandoTresJugadasDeshaciendoTodas() throws CoordenadasIncorrectasException {
			// given
			deshacer.hacerJugada(0, 0, Figura.CILINDRO, Color.BLANCO);
			deshacer.hacerJugada(2, 2, Figura.CILINDRO, Color.NEGRO);
			deshacer.hacerJugada(3, 2, Figura.CONO, Color.BLANCO);
			// when
			deshacer.deshacerJugada(); 
			deshacer.deshacerJugada();
			deshacer.deshacerJugada();
			Partida partida = deshacer.consultarPartidaActual();
			// then
			Tablero tablero = partida.consultarTablero();
			assertAll("comprobar que deshacer tres jugadas con tres jugadas grabadas devuelve una partida inicial",
					() -> assertThat("El número de jugadas en el histórico es incorrecto.", deshacer.consultarNumeroJugadasEnHistorico(),is(0)),

					() -> assertThat("La celda donde hemos colocado una figura inicialmente debería estar vacía.",
							tablero.consultarCelda(0, 0).estaVacia(), is(true)),
					() -> assertThat("La celda donde hemos colocado una figura a continuación debería estar vacía.",
							tablero.consultarCelda(2, 2).estaVacia(), is(true)),
					() -> assertThat("La celda donde hemos colocado una figura a continuación debería estar vacía.",
							tablero.consultarCelda(3, 2).estaVacia(), is(true)),
					() -> assertThat("El turno es incorrecto.", partida.consultarTurno(), is(Color.BLANCO)),
					() -> assertThat("El contador de jugadas es incorrecto.", partida.consultarNumeroJugada(), is(0)),
					() -> assertThat("No debería haber ganador.", partida.consultarGanador(), is(nullValue())),
					() -> assertThat("No debería estar bloqueado el turno actual.", partida.estaBloqueadoTurnoActual(),
							is(false)),
					() -> assertThat("No debería haber grupo ganador.", partida.hayAlgunGrupoCompleto(), is(false)),
					() -> assertThat("La partida no debería estar acabada.", partida.estaAcabadaPartida(), is(false))

			);
		}
	}
	
	/**
	 * Comprobación con partida completa de 16 jugadas.
	 *
	 */
	@Nested
	@DisplayName("Comprobar con partida completa de 16 jugadas")
	class Con16jugadas {
		
		/**
		 * Comprueba que deshace correctamente deshaciendo una jugada.
		 * 
		 * @throws CoordenadasIncorrectasException si las coordenadas están fuera del tablero 
		 */
		@Test
		@DisplayName("Comprobar que deshacer correctamente una jugada.")
		void probarDeshacerGrabando16JugadasDeshaciendoSoloUnaJugada() throws CoordenadasIncorrectasException {
			// given
			grabar16JugadasRellenandoTablero(deshacer);
			// when
			deshacer.deshacerJugada();
			Partida partida = deshacer.consultarPartidaActual(); // nos quedamos con la partida con 15 jugadas
			// then
			Tablero tablero = partida.consultarTablero();			
			assertAll("comprobar que deshacer una jugada con 16 jugadas grabadas devuelve una partida correcta con 15 jugadas",
					() -> assertThat("El número de jugadas en el histórico es incorrecto.", deshacer.consultarNumeroJugadasEnHistorico(),is(15)),

					() -> assertThat(
							"La celda donde hemos colocado una figura en la primera jugada debería contener un cilindro blanco.",
							tablero.consultarCelda(0, 0).consultarPieza(),
							is(new Pieza(Figura.CILINDRO, Color.BLANCO))),
					() -> assertThat(
							"La celda donde hemos colocado una figura en la segunda jugada debería contener un cilindro negro.",
							tablero.consultarCelda(3, 3).consultarPieza(),
							is(new Pieza(Figura.CILINDRO, Color.NEGRO))),
					
					() -> assertThat(
							"La celda donde hemos colocado una figura en la tercera jugada debería contener un cilindro blanco.",
							tablero.consultarCelda(1, 0).consultarPieza(),
							is(new Pieza(Figura.CILINDRO, Color.BLANCO))),
					() -> assertThat(
							"La celda donde hemos colocado una figura en la cuarta jugada debería contener un cilindro negro.",
							tablero.consultarCelda(2, 3).consultarPieza(),
							is(new Pieza(Figura.CILINDRO, Color.NEGRO))),
					
					() -> assertThat(
							"La celda donde hemos colocado una figura en la quinta jugada debería contener un cono blanco.",
							tablero.consultarCelda(0, 1).consultarPieza(),
							is(new Pieza(Figura.CONO, Color.BLANCO))),
					() -> assertThat(
							"La celda donde hemos colocado una figura en la sexta jugada debería contener un cono negro.",
							tablero.consultarCelda(3, 2).consultarPieza(),
							is(new Pieza(Figura.CONO, Color.NEGRO))),
					
					() -> assertThat(
							"La celda donde hemos colocado una figura en la séptima jugada debería contener un cono blanco.",
							tablero.consultarCelda(1, 1).consultarPieza(),
							is(new Pieza(Figura.CONO, Color.BLANCO))),
					() -> assertThat(
							"La celda donde hemos colocado una figura en la octava jugada debería contener un cono negro.",
							tablero.consultarCelda(2, 2).consultarPieza(),
							is(new Pieza(Figura.CONO, Color.NEGRO))),
					
					() -> assertThat(
							"La celda donde hemos colocado una figura en la novena jugada debería contener un esfera blanca.",
							tablero.consultarCelda(2, 0).consultarPieza(),
							is(new Pieza(Figura.ESFERA, Color.BLANCO))),
					() -> assertThat(
							"La celda donde hemos colocado una figura en la décima jugada debería contener una esfera negra.",
							tablero.consultarCelda(1, 3).consultarPieza(),
							is(new Pieza(Figura.ESFERA, Color.NEGRO))),
					
					() -> assertThat(
							"La celda donde hemos colocado una figura en la úndecima jugada debería contener un esfera blanca.",
							tablero.consultarCelda(2, 1).consultarPieza(),
							is(new Pieza(Figura.ESFERA, Color.BLANCO))),
					() -> assertThat(
							"La celda donde hemos colocado una figura en la duodécima jugada debería contener una esfera negra.",
							tablero.consultarCelda(1, 2).consultarPieza(),
							is(new Pieza(Figura.ESFERA, Color.NEGRO))),
					
					() -> assertThat(
							"La celda donde hemos colocado una figura en la decimotercera jugada debería contener un cubo blanco.",
							tablero.consultarCelda(3, 0).consultarPieza(),
							is(new Pieza(Figura.CUBO, Color.BLANCO))),
					() -> assertThat(
							"La celda donde hemos colocado una figura en la decimocuarta jugada debería contener un cubo negro.",
							tablero.consultarCelda(0, 3).consultarPieza(),
							is(new Pieza(Figura.CUBO, Color.NEGRO))),
					
					() -> assertThat(
							"La celda donde hemos colocado una figura en la decimoquintaa jugada debería contener un cubo blanco.",
							tablero.consultarCelda(3, 1).consultarPieza(),
							is(new Pieza(Figura.CUBO, Color.BLANCO))),
							
					() -> assertThat(
							"La celda donde hemos colocado la última figura en la decimosexta jugada debería estar vacía.",
							tablero.consultarCelda(0, 2).estaVacia(), is(true)),
					
					() -> assertThat("El turno es incorrecto.", partida.consultarTurno(), is(Color.NEGRO)),
					() -> assertThat("El contador de jugadas es incorrecto.", partida.consultarNumeroJugada(), is(15)),
					() -> assertThat("No debería haber ganador.", partida.consultarGanador(), is(nullValue())),
					() -> assertThat("No debería estar bloqueado el turno actual.", partida.estaBloqueadoTurnoActual(),
							is(false)),
					() -> assertThat("No debería haber grupo ganador.", partida.hayAlgunGrupoCompleto(), is(false)),
					() -> assertThat("La partida no debería estar acabada.", partida.estaAcabadaPartida(), is(false))
			);
		}
		
		/**
		 * Comprueba que deshace correctamente deshaciendo dos jugadas.
		 * 
		 * @throws CoordenadasIncorrectasException si las coordenadas están fuera del tablero
		 */
		@Test
		@DisplayName("Comprobar que deshacer correctamente dos jugadas.")
		void probarDeshacerGrabando16JugadasDeshaciendoSoloDosJugadas() throws CoordenadasIncorrectasException {
			// given
			grabar16JugadasRellenandoTablero(deshacer);
			// when			
			deshacer.deshacerJugada(); // nos quedamos con la partida con 15 jugadas
			deshacer.deshacerJugada(); // nos quedamos con la partida con 14 jugadas
			Partida partida = deshacer.consultarPartidaActual(); 
			// then
			Tablero tablero = partida.consultarTablero();			
			assertAll("comprobar que deshacer una jugada con 16 jugadas grabadas devuelve una partida correcta con 14 jugadas",
					() -> assertThat("El número de jugadas en el histórico es incorrecto.", deshacer.consultarNumeroJugadasEnHistorico(),is(14)),

					() -> assertThat(
							"La celda donde hemos colocado una figura en la primera jugada debería contener un cilindro blanco.",
							tablero.consultarCelda(0, 0).consultarPieza(),
							is(new Pieza(Figura.CILINDRO, Color.BLANCO))),
					() -> assertThat(
							"La celda donde hemos colocado una figura en la segunda jugada debería contener un cilindro negro.",
							tablero.consultarCelda(3, 3).consultarPieza(),
							is(new Pieza(Figura.CILINDRO, Color.NEGRO))),
					
					() -> assertThat(
							"La celda donde hemos colocado una figura en la tercera jugada debería contener un cilindro blanco.",
							tablero.consultarCelda(1, 0).consultarPieza(),
							is(new Pieza(Figura.CILINDRO, Color.BLANCO))),
					() -> assertThat(
							"La celda donde hemos colocado una figura en la cuarta jugada debería contener un cilindro negro.",
							tablero.consultarCelda(2, 3).consultarPieza(),
							is(new Pieza(Figura.CILINDRO, Color.NEGRO))),
					
					() -> assertThat(
							"La celda donde hemos colocado una figura en la quinta jugada debería contener un cono blanco.",
							tablero.consultarCelda(0, 1).consultarPieza(),
							is(new Pieza(Figura.CONO, Color.BLANCO))),
					() -> assertThat(
							"La celda donde hemos colocado una figura en la sexta jugada debería contener un cono negro.",
							tablero.consultarCelda(3, 2).consultarPieza(),
							is(new Pieza(Figura.CONO, Color.NEGRO))),
					
					() -> assertThat(
							"La celda donde hemos colocado una figura en la séptima jugada debería contener un cono blanco.",
							tablero.consultarCelda(1, 1).consultarPieza(),
							is(new Pieza(Figura.CONO, Color.BLANCO))),
					() -> assertThat(
							"La celda donde hemos colocado una figura en la octava jugada debería contener un cono negro.",
							tablero.consultarCelda(2, 2).consultarPieza(),
							is(new Pieza(Figura.CONO, Color.NEGRO))),
					
					() -> assertThat(
							"La celda donde hemos colocado una figura en la novena jugada debería contener un esfera blanca.",
							tablero.consultarCelda(2, 0).consultarPieza(),
							is(new Pieza(Figura.ESFERA, Color.BLANCO))),
					() -> assertThat(
							"La celda donde hemos colocado una figura en la décima jugada debería contener una esfera negra.",
							tablero.consultarCelda(1, 3).consultarPieza(),
							is(new Pieza(Figura.ESFERA, Color.NEGRO))),
					
					() -> assertThat(
							"La celda donde hemos colocado una figura en la úndecima jugada debería contener un esfera blanca.",
							tablero.consultarCelda(2, 1).consultarPieza(),
							is(new Pieza(Figura.ESFERA, Color.BLANCO))),
					() -> assertThat(
							"La celda donde hemos colocado una figura en la duodécima jugada debería contener una esfera negra.",
							tablero.consultarCelda(1, 2).consultarPieza(),
							is(new Pieza(Figura.ESFERA, Color.NEGRO))),
					
					() -> assertThat(
							"La celda donde hemos colocado una figura en la decimotercera jugada debería contener un cubo blanco.",
							tablero.consultarCelda(3, 0).consultarPieza(),
							is(new Pieza(Figura.CUBO, Color.BLANCO))),
					() -> assertThat(
							"La celda donde hemos colocado una figura en la decimocuarta jugada debería contener un cubo negro.",
							tablero.consultarCelda(0, 3).consultarPieza(),
							is(new Pieza(Figura.CUBO, Color.NEGRO))),
					
					() -> assertThat(
							"La celda donde hemos colocado la penúltima figura en la decimoquinta jugada debería estar vacía.",
							tablero.consultarCelda(3, 1).estaVacia(), is(true)),
							
					() -> assertThat(
							"La celda donde hemos colocado la última figura en la decimosexta jugada debería estar vacía.",
							tablero.consultarCelda(0, 2).estaVacia(), is(true)),
					
					() -> assertThat("El turno es incorrecto.", partida.consultarTurno(), is(Color.BLANCO)),
					() -> assertThat("El contador de jugadas es incorrecto.", partida.consultarNumeroJugada(), is(14)),
					() -> assertThat("No debería haber ganador.", partida.consultarGanador(), is(nullValue())),
					() -> assertThat("No debería estar bloqueado el turno actual.", partida.estaBloqueadoTurnoActual(),
							is(false)),
					() -> assertThat("No debería haber grupo ganador.", partida.hayAlgunGrupoCompleto(), is(false)),
					() -> assertThat("La partida no debería estar acabada.", partida.estaAcabadaPartida(), is(false))
			);
		}
		
		/**
		 * Comprueba que deshace correctamente deshaciendo tres jugadas.
		 * 
		 * @throws CoordenadasIncorrectasException si las coordenadas están fuera del tablero
		 */
		@Test
		@DisplayName("Comprobar que deshacer correctamente tres jugadas.")
		void probarDeshacerGrabando16JugadasDeshaciendoTresJugadas() throws CoordenadasIncorrectasException {
			// given
			grabar16JugadasRellenandoTablero(deshacer);
			// when
			deshacer.deshacerJugada(); // nos quedamos con la partida con 15 jugadas
			deshacer.deshacerJugada(); // nos quedamos con la partida con 14 jugadas
			deshacer.deshacerJugada(); // nos quedamos con la partida con 13 jugadas
			Partida partida = deshacer.consultarPartidaActual(); 
			// then
			Tablero tablero = partida.consultarTablero();			
			assertAll("comprobar que deshacer una jugada con 16 jugadas grabadas devuelve una partida correcta con 13 jugadas",
					() -> assertThat("El número de jugadas en el histórico es incorrecto.", deshacer.consultarNumeroJugadasEnHistorico(),is(13)),

					() -> assertThat(
							"La celda donde hemos colocado una figura en la primera jugada debería contener un cilindro blanco.",
							tablero.consultarCelda(0, 0).consultarPieza(),
							is(new Pieza(Figura.CILINDRO, Color.BLANCO))),
					() -> assertThat(
							"La celda donde hemos colocado una figura en la segunda jugada debería contener un cilindro negro.",
							tablero.consultarCelda(3, 3).consultarPieza(),
							is(new Pieza(Figura.CILINDRO, Color.NEGRO))),
					
					() -> assertThat(
							"La celda donde hemos colocado una figura en la tercera jugada debería contener un cilindro blanco.",
							tablero.consultarCelda(1, 0).consultarPieza(),
							is(new Pieza(Figura.CILINDRO, Color.BLANCO))),
					() -> assertThat(
							"La celda donde hemos colocado una figura en la cuarta jugada debería contener un cilindro negro.",
							tablero.consultarCelda(2, 3).consultarPieza(),
							is(new Pieza(Figura.CILINDRO, Color.NEGRO))),
					
					() -> assertThat(
							"La celda donde hemos colocado una figura en la quinta jugada debería contener un cono blanco.",
							tablero.consultarCelda(0, 1).consultarPieza(),
							is(new Pieza(Figura.CONO, Color.BLANCO))),
					() -> assertThat(
							"La celda donde hemos colocado una figura en la sexta jugada debería contener un cono negro.",
							tablero.consultarCelda(3, 2).consultarPieza(),
							is(new Pieza(Figura.CONO, Color.NEGRO))),
					
					() -> assertThat(
							"La celda donde hemos colocado una figura en la séptima jugada debería contener un cono blanco.",
							tablero.consultarCelda(1, 1).consultarPieza(),
							is(new Pieza(Figura.CONO, Color.BLANCO))),
					() -> assertThat(
							"La celda donde hemos colocado una figura en la octava jugada debería contener un cono negro.",
							tablero.consultarCelda(2, 2).consultarPieza(),
							is(new Pieza(Figura.CONO, Color.NEGRO))),
					
					() -> assertThat(
							"La celda donde hemos colocado una figura en la novena jugada debería contener un esfera blanca.",
							tablero.consultarCelda(2, 0).consultarPieza(),
							is(new Pieza(Figura.ESFERA, Color.BLANCO))),
					() -> assertThat(
							"La celda donde hemos colocado una figura en la décima jugada debería contener una esfera negra.",
							tablero.consultarCelda(1, 3).consultarPieza(),
							is(new Pieza(Figura.ESFERA, Color.NEGRO))),
					
					() -> assertThat(
							"La celda donde hemos colocado una figura en la úndecima jugada debería contener un esfera blanca.",
							tablero.consultarCelda(2, 1).consultarPieza(),
							is(new Pieza(Figura.ESFERA, Color.BLANCO))),
					() -> assertThat(
							"La celda donde hemos colocado una figura en la duodécima jugada debería contener una esfera negra.",
							tablero.consultarCelda(1, 2).consultarPieza(),
							is(new Pieza(Figura.ESFERA, Color.NEGRO))),
					
					() -> assertThat(
							"La celda donde hemos colocado una figura en la decimotercera jugada debería contener un cubo blanco.",
							tablero.consultarCelda(3, 0).consultarPieza(),
							is(new Pieza(Figura.CUBO, Color.BLANCO))),
					() -> assertThat(
							"La celda donde hemos colocado la figura en la decimocuarta jugada debería estar vacía.",
							tablero.consultarCelda(0, 3).estaVacia(), is(true)),
					
					() -> assertThat(
							"La celda donde hemos colocado la penúltima figura en la decimoquinta jugada debería estar vacía.",
							tablero.consultarCelda(3, 1).estaVacia(), is(true)),
							
					() -> assertThat(
							"La celda donde hemos colocado la última figura en la decimosexta jugada debería estar vacía.",
							tablero.consultarCelda(0, 2).estaVacia(), is(true)),
					
					() -> assertThat("El turno es incorrecto.", partida.consultarTurno(), is(Color.NEGRO)),
					() -> assertThat("El contador de jugadas es incorrecto.", partida.consultarNumeroJugada(), is(13)),
					() -> assertThat("No debería haber ganador.", partida.consultarGanador(), is(nullValue())),
					() -> assertThat("No debería estar bloqueado el turno actual.", partida.estaBloqueadoTurnoActual(),
							is(false)),
					() -> assertThat("No debería haber grupo ganador.", partida.hayAlgunGrupoCompleto(), is(false)),
					() -> assertThat("La partida no debería estar acabada.", partida.estaAcabadaPartida(), is(false))
			);
		}
		
		/**
		 * Comprueba que deshace correctamente deshaciendo ocho jugadas.
		 * 
		 * @throws CoordenadasIncorrectasException si las coordenadas están fuera del tablero 
		 */
		@Test
		@DisplayName("Comprobar que deshacer correctamente ocho jugadas.")
		void probarDeshacerGrabando16JugadasDeshaciendo8Jugadas() throws CoordenadasIncorrectasException {
			// given
			grabar16JugadasRellenandoTablero(deshacer);
			// when
			deshacer.deshacerJugada(); // nos quedamos con la partida con 15 jugadas
			deshacer.deshacerJugada(); // nos quedamos con la partida con 14 jugadas
			deshacer.deshacerJugada(); // nos quedamos con la partida con 13 jugadas
			deshacer.deshacerJugada(); // nos quedamos con la partida con 12 jugadas
			deshacer.deshacerJugada(); // nos quedamos con la partida con 11 jugadas
			deshacer.deshacerJugada(); // nos quedamos con la partida con 10 jugadas
			deshacer.deshacerJugada(); // nos quedamos con la partida con 9 jugadas
			deshacer.deshacerJugada(); // nos quedamos con la partida con 8 jugadas
			Partida partida = deshacer.consultarPartidaActual(); 
			// then
			Tablero tablero = partida.consultarTablero();			
			assertAll("comprobar que deshacer una jugada con 16 jugadas grabadas devuelve una partida correcta con 8 jugadas",
					() -> assertThat("El número de jugadas en el histórico es incorrecto.", deshacer.consultarNumeroJugadasEnHistorico(),is(8)),

					() -> assertThat(
							"La celda donde hemos colocado una figura en la primera jugada debería contener un cilindro blanco.",
							tablero.consultarCelda(0, 0).consultarPieza(),
							is(new Pieza(Figura.CILINDRO, Color.BLANCO))),
					() -> assertThat(
							"La celda donde hemos colocado una figura en la segunda jugada debería contener un cilindro negro.",
							tablero.consultarCelda(3, 3).consultarPieza(),
							is(new Pieza(Figura.CILINDRO, Color.NEGRO))),
					
					() -> assertThat(
							"La celda donde hemos colocado una figura en la tercera jugada debería contener un cilindro blanco.",
							tablero.consultarCelda(1, 0).consultarPieza(),
							is(new Pieza(Figura.CILINDRO, Color.BLANCO))),
					() -> assertThat(
							"La celda donde hemos colocado una figura en la cuarta jugada debería contener un cilindro negro.",
							tablero.consultarCelda(2, 3).consultarPieza(),
							is(new Pieza(Figura.CILINDRO, Color.NEGRO))),
					
					() -> assertThat(
							"La celda donde hemos colocado una figura en la quinta jugada debería contener un cono blanco.",
							tablero.consultarCelda(0, 1).consultarPieza(),
							is(new Pieza(Figura.CONO, Color.BLANCO))),
					() -> assertThat(
							"La celda donde hemos colocado una figura en la sexta jugada debería contener un cono negro.",
							tablero.consultarCelda(3, 2).consultarPieza(),
							is(new Pieza(Figura.CONO, Color.NEGRO))),
					
					() -> assertThat(
							"La celda donde hemos colocado una figura en la séptima jugada debería contener un cono blanco.",
							tablero.consultarCelda(1, 1).consultarPieza(),
							is(new Pieza(Figura.CONO, Color.BLANCO))),
					() -> assertThat(
							"La celda donde hemos colocado una figura en la octava jugada debería contener un cono negro.",
							tablero.consultarCelda(2, 2).consultarPieza(),
							is(new Pieza(Figura.CONO, Color.NEGRO))),
					
					() -> assertThat(
							"La celda donde hemos colocado una figura en la novena jugada debería estar vacía.",
							tablero.consultarCelda(2, 0).estaVacia(), is(true)),
					() -> assertThat(
							"La celda donde hemos colocado una figura en la décima jugada debería estar vacía.",
							tablero.consultarCelda(1, 3).estaVacia(), is(true)),
					
					() -> assertThat(
							"La celda donde hemos colocado una figura en la úndecima jugada debería estar vacía.",
							tablero.consultarCelda(2, 1).estaVacia(), is(true)),
					() -> assertThat(
							"La celda donde hemos colocado una figura en la duodécima jugada debería estar vacía.",
							tablero.consultarCelda(1, 2).estaVacia(), is(true)),
					
					() -> assertThat(
							"La celda donde hemos colocado una figura en la decimotercera jugada debería estar vacía.",
							tablero.consultarCelda(3, 0).estaVacia(), is(true)),
					() -> assertThat(
							"La celda donde hemos colocado la figura en la decimocuarta jugada debería estar vacía.",
							tablero.consultarCelda(0, 3).estaVacia(), is(true)),
					
					() -> assertThat(
							"La celda donde hemos colocado la penúltima figura en la decimoquinta jugada debería estar vacía.",
							tablero.consultarCelda(3, 1).estaVacia(), is(true)),
							
					() -> assertThat(
							"La celda donde hemos colocado la última figura en la decimosexta jugada debería estar vacía.",
							tablero.consultarCelda(0, 2).estaVacia(), is(true)),
					
					() -> assertThat("El turno es incorrecto.", partida.consultarTurno(), is(Color.BLANCO)),
					() -> assertThat("El contador de jugadas es incorrecto.", partida.consultarNumeroJugada(), is(8)),
					() -> assertThat("No debería haber ganador.", partida.consultarGanador(), is(nullValue())),
					() -> assertThat("No debería estar bloqueado el turno actual.", partida.estaBloqueadoTurnoActual(),
							is(false)),
					() -> assertThat("No debería haber grupo ganador.", partida.hayAlgunGrupoCompleto(), is(false)),
					() -> assertThat("La partida no debería estar acabada.", partida.estaAcabadaPartida(), is(false))
			);
		}
		
	}
	
	// Util
	

	/**
	 * Genera una partida inicial.
	 * 
	 * @return partida inicial
	 */
	static Partida generarPartidaInicial() {
		// Generamos una partida inicial...
		Tablero tablero = new Tablero();
		Caja cajaBlancas = new Caja(Color.BLANCO);
		Caja cajaNegras = new Caja(Color.NEGRO);

		return new Partida(tablero, cajaBlancas, cajaNegras);
	}
	
	/**
	 * Genera y graba las 16 jugadas rellenando el tablero sin conseguir victoria.
	 * 
	 * @param deshacer mecanismo de deshacer para grabar las jugadas
	 * @throws CoordenadasIncorrectasException si las coordenadas están fuera del tablero
	 */
	static void grabar16JugadasRellenandoTablero(MecanismoDeDeshacer deshacer) throws CoordenadasIncorrectasException {
		deshacer.hacerJugada(0, 0, Figura.CILINDRO, Color.BLANCO); // 00CLB
		deshacer.hacerJugada(3, 3, Figura.CILINDRO, Color.NEGRO); // 33CLN
		
		deshacer.hacerJugada(1, 0, Figura.CILINDRO, Color.BLANCO); // 10CLB
		deshacer.hacerJugada(2, 3, Figura.CILINDRO, Color.NEGRO); // 23CLN

		deshacer.hacerJugada(0, 1, Figura.CONO, Color.BLANCO); // 01CNB
		deshacer.hacerJugada(3, 2, Figura.CONO, Color.NEGRO); // 32CNN
		
		deshacer.hacerJugada(1, 1, Figura.CONO, Color.BLANCO); // 11CNB
		deshacer.hacerJugada(2, 2, Figura.CONO, Color.NEGRO); // 22CNN

		deshacer.hacerJugada(2, 0, Figura.ESFERA, Color.BLANCO); // 20ESB
		deshacer.hacerJugada(1, 3, Figura.ESFERA, Color.NEGRO); // 13ESN
		
		deshacer.hacerJugada(2, 1, Figura.ESFERA, Color.BLANCO); // 21ESB
		deshacer.hacerJugada(1, 2, Figura.ESFERA, Color.NEGRO); // 12ESN

		deshacer.hacerJugada(3, 0, Figura.CUBO, Color.BLANCO); // 30CBB
		deshacer.hacerJugada(0, 3, Figura.CUBO, Color.NEGRO); // 03CBN

		deshacer.hacerJugada(3, 1, Figura.CUBO, Color.BLANCO); // 31CBB
		deshacer.hacerJugada(0, 2, Figura.CUBO, Color.NEGRO); // 02CBB tablero completo sin piezas disponibles
																// en ninguna caja ni ganador
	}
	
	/**
	 * Comprueba que la grabación de jugadas fuera del tablero genera excepción.
	 *
	 * @param fila fila 
	 * @param columna columna
	 * @see quantik.modelo.Util#proveerCoordenadasIncorrectas
	 */
	@ParameterizedTest
	@MethodSource("quantik.modelo.Util#proveerCoordenadasIncorrectas")
	@DisplayName("Comprobar que la grabación de jugadas sobre coordenadas incorrectas fuera del tablero genera excepción.")
	void probarGenerarJugadaIncorrecta(int fila, int columna) {
		// en este caso no interesan los valores de figura y color, solo que las coordenadas son erróneas
		assertThrows("comprobar que la grabación de jugadas fuera del tablero genera excepción", CoordenadasIncorrectasException.class,
				() -> deshacer.hacerJugada(fila, columna, null, null)
		);
	}

}

