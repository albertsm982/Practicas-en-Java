/**
 * Estructuras de control. Ejemplo de uso de recursividad.
 *
 * @author <A HREF="mailto:rmartico@ubu.es">Raúl Marticorena</A>
 * @author <A HREF="mailto:cpardo@ubu.es">Carlos Pardo</A>
 * @version 1.0
 */
public class Factorial {

	/**
	 * Función principal (Método raíz).
	 *
	 * @param args parametros de entrada introducidos en línea de comandos
	 */
	public static void main(String[] args) {
		System.out.printf("%d%n", factorial(7));
	}

	/**
	 * Función recursiva.
	 *
	 * @param n valor sobre el que se realiza el cálculo
	 * @return el factorial del valor de entrada
	 */
	static long factorial(long n) {
		if (n == 1) { // condición de finalización de llamadas recursivas
			return 1; // final de la recursion
		} else {
			return n * factorial(n - 1); // llamada recursiva
		}
	}

}