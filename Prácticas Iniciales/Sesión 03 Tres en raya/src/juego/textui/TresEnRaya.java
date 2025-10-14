// COMPLETAR PAQUETE...
package juego.textui;

import java.util.Scanner;

import juego.modelo.Tablero;

// COMPLETAR IMPORTACIONES....

/**
 * Juego del tres en raya. Ejercicio práctico - Metodología de la Programación.
 * <p>
 * 2º GII - Curso 2022-2023
 * 
 * @author INCLUIR_NOMBRE_APELLIDOS 
 */
public class TresEnRaya {

	/** Número de argumentos máximo. */
	private static final int NUM_ARGUMENTOS = 2;

	/** Arbitro. */
	//private static ArbitroTresEnRaya arbitro;

	/**
	 * Flujo de ejecución principal del juego.
	 * 
	 * @param args
	 *            nombres de los jugadores
	 */
	public static void main(String[] args) {
		if (args.length != NUM_ARGUMENTOS) {
			mostrarAyuda();
		} else {
			// Inicialización del juego según los argumentos validados
			// COMPLETAR POR EL ALUMNO

			// Fase de juego, iteramos sobre las distintas jugadas.
			Scanner teclado = new Scanner(System.in); // teclado permite leer enteros con el método nextInt
			// COMPLETAR POR EL ALUMNO
			
			// mientras la partida no esté acabada leer de teclado fila, columna, y si la
			// jugada es válida, completarla
			// Tip: para leer de teclado un entero usaríamos: int valor = teclado.nextInt(); 

			// Informar del resultado final de la partida (ganador o tablas)
			// COMPLETAR POR EL ALUMNO
			teclado.close();

		} // número de argumentos correcto
	}

	/**
	 * Muestra la ayuda en línea para la inicialización correcta del juego.
	 */
	private static void mostrarAyuda() {
		// COMPLETAR...
	}

	/**
	 * Muestra el estado actual del tablero.
	 * 
	 * @param tablero
	 *            tablero a pintar en pantalla.
	 */
	private static void mostrarTablero(Tablero tablero) {
		// COMPLETAR
	} // mostrarTablero

	
	// AÑADIR MÁS MÉTODOS NECESARIOS PARA EL MODO TEXTO...
} // TresEnRaya