/**
 * 
 * @author Alberto Santos Martínez
 *
 */
public class Bisiesto {

	public static void main(String[] args) {

		int año = 2020;
		boolean resultado = esBisiesto(año);
		if (resultado == true) {
			System.out.println("El año es un año bisiesto");
		} else {
			System.out.println("El año no es bisiesto");
		}

	}

	static boolean esBisiesto(int año) {
		/*
		 * Es bisiesto si es multiplo de 4 y no es de 100, o bien es múltiplo de 400
		 */
		if (año % 4 == 0 && (año % 100 != 0 || año % 400 == 0)) {
			return true;
		} else {
			return false;
		}
	}

}
