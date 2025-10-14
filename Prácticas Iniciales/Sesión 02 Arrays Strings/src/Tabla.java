/**
 * 
 * @author Alberto Santos Martínez
 *
 */
public class Tabla {

	public static void main(String[] args) {

		int[][] tabla = new int[3][3]; // declaración y reserva
		inicializar(tabla); // inicializa el array
		mostrar(tabla); // muestra en pantalla el estado actual
		System.out.println("");
		elevarAlCuadrado(tabla); // eleva al cuadrado cada posición
		mostrar(tabla); // muestra en pantalla el estado actual
		
	}
	
	/**
	 * Inicializa los números de la tabla
	 * 
	 * @param tabla
	 * @return la tabla
	 */
	public static int[][] inicializar(int[][] tabla) {
		
		//tabla = new int[][] { {1,2,3} , {4,5,6} , {7,8,9} }; ¿Esto se puede hacer?
		int numero = 1;
		for(int i = 0; tabla.length > i; i++) {
			for(int j = 0; tabla.length > j; j++) {
				tabla[i][j] = numero;
				numero++;
			}
		}

		return tabla;
	}
	
	/**
	 * Imprime por pantalla la tabla cada vez que se llama
	 * 
	 * @param tabla
	 */
	public static void mostrar (int[][] tabla){
		
		for(int i = 0; tabla.length > i; i++) {
			for(int j = 0; tabla.length > j; j++) {
				System.out.print(tabla[i][j] + "(" + i + "," + j + ")" + "\t");
			}
			System.out.println("");
		}
	}
	
	/**
	 * Eleva al cuadrado la tabla
	 * 
	 * @param tabla
	 * @return la tabla
	 */
	public static int[][] elevarAlCuadrado(int[][] tabla) {
		for(int i = 0; tabla.length > i; i++) {
			for(int j = 0; tabla.length > j; j++) {
				tabla[i][j] = (int) Math.pow(tabla[i][j],2);
			}
		}
		return tabla;
	}
}
	