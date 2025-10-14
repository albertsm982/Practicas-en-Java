package excepcion;

import java.util.Scanner;

/**
 * Principal.
 * 
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @version 1.0
 */
public class Principal {

	/**
	 * Método raíz.
	 *
	 * @param args argumentos
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Exception exception = null;
		for (int i = 0; i < 3; i++) {
			String s = scanner.next();
			Conversor conversor = new Conversor();
			try {
				System.out.println(conversor.aMinusculas(s));
				System.out.println(conversor.aMayusculas(s));
			} catch (BException c) {
				exception = c;
			} 
		}
		if(exception != null) {
			System.out.println(exception.getMessage());
		}
	}
}