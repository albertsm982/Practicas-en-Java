/**
 * 
 * @author Alberto Santos Martínez
 *
 */
public class NumeroBinario {

	/**
	 * Escribir un programa que muestra en pantalla si un valor de tipo long
	 * codificado directamente en el código, es un número “binario” y su
	 * correspondiente valor en base 10. En este ejercicio en particular se entiende
	 * que un valor long contiene un valor binario si y solo si, está definido por
	 * dígitos unos y ceros 7 (e.g. long num = 101010; y en pantalla se mostrará su
	 * valor equivalente en decimal que es 42)
	 */
	public static void main(String[] args) {
		long num = 100;
		boolean resultado = esBinario(num);
		long decimal = obtenerValorDecimal(num);
		if (resultado)
			System.out.println("El número es binario y convertido a decimal es: " + decimal);
		else
			System.out.println("El número no es binario");
	}

	/**
	 * Métodos estático esBinario, a utilizar desde el método main, con las
	 * correspondiente signatura. Dado un valor comprueba que todos sus dígitos son
	 * ceros y unos, devolviendo un valor lógico (true o false)
	 * 
	 * @param valor
	 * @return
	 */
	static boolean esBinario(long valor) {

		long digit = 0;
		if (valor == 0 || valor == 1) 
			return true;
		else {
			while (valor != 0 || valor != 1) {
				if(valor == 0 || valor == 1)
					return true;
				digit = valor % 10;
				for(int i = 2; i<=9;i++) {
					if(digit == i)
						return false;
				}
				valor /= 10;
			}
		}
		return true;
	}

	/**
	 * Métodos estático obtenerValorDecimal, a utilizar en el método main, con las
	 * correspondiente signatura. Dado un número “binario” devuelve su
	 * correspondiente valor en base 10. Si el valor pasado no es “binario”
	 * (reutilizar el anterior método esBinario) devuelve un valor constante -1
	 * 
	 * @param numero
	 * @return
	 */
	static long obtenerValorDecimal(long numero) {

		
		if(!esBinario(numero)) 
			return -1;
		else {
			long numeroDecimal = 0;
			int i = 0;
			while (numero != 0) {
				
				long sumatorio = numero % 10;
				numeroDecimal += (long) (sumatorio * Math.pow(2, i));
				numero /= 10;
				i++;
			}
			return numeroDecimal;
		}
		
	}

}
