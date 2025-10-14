/**
 * 
 * @author Alberto Santos Martínez
 *
 */
public class NumeroPerfecto {

	public static void main(String[] args) {
	
		for(int i = 1; i < 10000; i++) {
			boolean resultado = esPerfecto(i);
			if(resultado) 
				System.out.print(i + " ");
		}
		
		
	}

	static boolean esPerfecto (int valor) {
		
		int sumatorio = 0;
		
		for(int i = valor; i > 0; i--) {
			if(valor%i == 0)
				sumatorio += i;
		}
		int valorFinal = sumatorio-valor;
		if(valor == valorFinal)
			return true;
		else
			return false;
	}
}
