/**
* Arrays, ejemplo de generación de error en tiempo de ejecución.
*
* @author <A HREF="mailto:rmartico@ubu.es">Raúl Marticorena</A>
* @author <A HREF="mailto:cpardo@ubu.es">Carlos Pardo</A>
* @version 1.0
*
*/
public class RomperArray {

	/**
	* Función principal (Método raíz). 
	*
	* @param args parametros de entrada introducidos en línea de comandos
	*/
	public static void main(String[] args) {
		int[] a = {1, 2, 3, 4}; // local al metodo

		// OJO con <= se genera excepcion
		for ( byte i = 0; i <= a.length; i ++ ) { 
			System.out.printf("%d%n", a[i]);
			a[i] = a[i] + 1;
		} // for

	} // main

}