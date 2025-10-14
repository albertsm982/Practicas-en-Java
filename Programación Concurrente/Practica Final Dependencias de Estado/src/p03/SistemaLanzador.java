package p03;

/**
 * Clase Sistema Lanzador que contiene el método main para lanzar el sistema que
 * se encarga de crear instancias de ActividadEntradaPuerta y
 * ActividadSalidaPuerta para cada puerta del parque, y lanzar un hilo para cada
 * una de ellas. La clase recibe como argumento de entrada el número de puertas
 * que se quieren crear, y utiliza la clase AdaptadorParqueSincronizado para
 * obtener una instancia del parque sincronizado.
 * 
 * @author Alberto Santos Martínez
 * @author Jon Ander Incera Moreno
 */
public class SistemaLanzador {

	public static void main(String[] args) {

		IParque parque = AdaptadorParqueSincronizado.getInstancia();
		char letra_puerta = 'A';

		for (int i = 0; i < Integer.parseInt(args[0]); i++) { // Bucle que recoge las entradas por argumento

			String puerta = "" + ((char) (letra_puerta++));

			// Creación de hilos de entrada
			ActividadEntradaPuerta entradas = new ActividadEntradaPuerta(puerta, parque);
			new Thread(entradas).start();

			// Creación de hilos de salida
			ActividadSalidaPuerta salidas = new ActividadSalidaPuerta(puerta, parque);
			new Thread(salidas).start();
		}
	}

}