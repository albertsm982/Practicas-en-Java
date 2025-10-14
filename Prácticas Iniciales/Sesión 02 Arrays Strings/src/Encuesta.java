/**
 * Arrays. Ejemplo de uso de arrays de una dimensión, paso de arrays como
 * argumentos, etc.
 *
 * @author <A HREF="mailto:rmartico@ubu.es">Raúl Marticorena</A>
 * @author <A HREF="mailto:cpardo@ubu.es">Carlos Pardo</A>
 * @version 1.0
 */
public class Encuesta {

	/**
	 * Valor constante con el numero de pesos (votaciones). En Java se utiliza la
	 * palabra reservada final para indicar que es constante (similar al uso de
	 * const en C). La convención de nombres es utilizar mayúsculas.
	 */
	private static final int VALORES = 11;

	/**
	 * Función principal (Método raíz).
	 *
	 * @param args parametros de entrada introducidos en línea de comandos
	 */
	public static void main(String[] args) {
		// Respuestas introducidas por los clientes
		int[] respuestas = { 1, 2, 6, 4, 4, 4, 3, 7, 9, 0, 1, 2, 6, 4, 8, 5, 3, 7, 9, 0, 1, 2, 6, 4, 8, 5, 3, 3, 9, 0,
				1, 2, 6, 4, 8, 5, 3, 2, 9, 10 };
		// Tabla de frecuencias
		int[] frecuencias = new int[VALORES];

		calcular(frecuencias, respuestas); // calcular frecuencias
		mostrar(frecuencias); // calcular valores
	}

	/**
	 * Calcula la frecuencia de las apariciones de cada peso.
	 *
	 * @param frecuencias frecuencia de aparacion de cada número / voto
	 * @param respuestas  valores de números / votos recibidos en la encuesta
	 */
	private static void calcular(int[] frecuencias, int[] respuestas) {
		for (int j = 0; j < respuestas.length; j++) {
			++frecuencias[respuestas[j]];
		}
	}

	/**
	 * Muestra por pantalla las frecuencias de las votaciones.
	 *
	 * @param frecuencias frecuencias de aparición de cada número / voto
	 */

	private static void mostrar(int[] frecuencias) {
		System.out.printf("--------------------%n");
		System.out.printf("Tabla de frecuencias%n");
		System.out.printf("--------------------%n");
		for (int j = 0; j < frecuencias.length; j++) {
			System.out.printf("Valor: %d \t Frecuencia: %d%n", j, frecuencias[j]);
		}
	}
}
