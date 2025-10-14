/**
 * 
 * @author Alberto Santos Martínez
 *
 */
public class Palindromo {

	public static void main(String[] args) {
		
		char texto[] = {'a','n','a'};
		boolean resultado = esPalindromo(texto);
		if(resultado) 
			System.out.println("Es palindromo");
		else 
			System.out.println("No es palindromo");	
			
	}
	
	/**
	 * Método que verifica si un array pasado por parametro es un palindromo o no comparando las posiciones primera
	 * y final del array
	 * 
	 * @param texto
	 * @return true o false dependiendo si el resultado es palindromo o no
	 */
	public static boolean esPalindromo (char[] texto) {
		
	int j = texto.length;
	for(int i = 0; i < j; i++) {
		--j;
		if(texto[i] != texto[j]) {
			return false;
		}
	}
		return true;
	}
}
