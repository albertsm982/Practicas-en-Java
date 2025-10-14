/**
 * 
 * @author Alberto Santos Martínez
 *
 */
public class NumeroDeAdan {

	public static void main(String[] args) {

		
		for (int i = 1; i <= 1000; i++) {
			invertirDigitos(i);
			esNumeroDeAdan(i);
			if (esNumeroDeAdan(i))
				System.out.print(i + ", ");
			}
	}

	static int invertirDigitos(int valor) {

		int invertido = 0;
		while (valor != 0) {
			int digit = valor % 10;
			invertido = invertido * 10 + digit;
			valor /= 10;
		}
		return invertido;
	}

	static boolean esNumeroDeAdan(int valor) {

		int valorCuadrado = valor * valor;
		int invertidoCuadrado = invertirDigitos(valor);
		invertidoCuadrado *= invertidoCuadrado;
		if (valorCuadrado == invertirDigitos(invertidoCuadrado))
			return true;
		return false;
	}
}
