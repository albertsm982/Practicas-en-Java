/**
 * Códigos de ejemplo con las nuevas características de switch como expresión
 * introducidas en las últimas versiones de Java.
 * 
 * Utilizamos enteros como tipo del valor a resolver en el switch, pero en la
 * actualidad es más habitual encontrar ejemplos con textos (String) y tipos
 * enumerados (que iremos introduciendo gradualmente en la asignatura).
 *
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @version 1.0
 */
public class SwitchNuevasCaracteristicas {

	/**
	 * Main de ejemplo para ejecutar distintas versiones de uso del switch.
	 * 
	 * @param args argumentos en línea de comandos
	 */
	public static void main(String[] args) {
		int numero1 = (int) (Math.random() * 15); // Versión "rápida" para generar aleatorios..
		System.out.printf("El número %d lo convertimos a texto como %s.%n", numero1, resolverClasico(numero1));
		System.out.printf("El número %d lo convertimos a texto como %s.%n", numero1,
				resolverConInstruccionNuevaNotacion(numero1));
		System.out.printf("El número %d lo convertimos a texto como %s.%n", numero1, resolverConExpresion(numero1));
	}

	/**
	 * Devuelve el texto correspondiente a un número entre cero y cuatro. Si el
	 * valor está entre 5 a 9 lo identificar en ese intervalo devolviendo el texto
	 * correspondiente. Si es mayor de 9, devuelve un texto por defecto.
	 * 
	 * Se utiliza un switch "instrucción" clásico con cuidado de usar el break para
	 * no seguir procesando las instrucciones posteriores.
	 * 
	 * @param numero número
	 * @return texto texto correspondiente a dicho número
	 */
	public static String resolverClasico(int numero) {
		String texto = null;
		switch (numero) { // switch como "instrucción", hay que asignar el valor par retornarlo fuera de
							// la instrucción
		case 0:
			texto = "cero";
			break;
		case 1:
			texto = "uno";
			break;
		case 2:
			texto = "dos";
			break;
		case 3:
			texto = "tres";
			break;
		case 4:
			texto = "cuatro";
			break;
		case 5, 6, 7, 8, 9: // en las últimas versiones se pueden utilizar comas para separar varios
							// valores...
			texto = "entre cinco y nueve";
			break;
		// NO obligatorio cubrir todos los posibles casos (exhaustivo), aunque lo
		// hacemos en el ejemplo.
		default:
			texto = "mayor de nueve...";
		}
		return texto; // retorna el valor evaluado en la instrucción switch
	}

	/**
	 * Devuelve el texto correspondiente a un número entre cero y cuatro. Si el
	 * valor está entre 5 a 9 lo identificar en ese intervalo devolviendo el texto
	 * correspondiente. Si es mayor de 9, devuelve un texto por defecto.
	 * 
	 * Se utiliza un switch como "instrucción" pero usando la nueva notación.
	 * 
	 * @param numero número
	 * @return texto texto correspondiente a dicho número
	 */
	public static String resolverConInstruccionNuevaNotacion(int numero) {
		String texto = null;
		switch (numero) { // el switch se evalúa y asigna directamente un valor
		case 0 -> texto = "cero"; // notación -> a la derecha del case, sin necesidad del break, solo se procesa
									// esta línea
		case 1 -> texto = "uno";
		case 2 -> texto = "dos";
		case 3 -> texto = "tres";
		case 4 -> texto = "cuatro";
		case 5, 6, 7, 8, 9 -> {
			// si necesitamos varias líneas en el bloque case añadimos llaves de bloque
			// (aunque aquí no era obligatorio, se hace como ejemplo)
			texto = "entre cinco y nueve";
			// aquí podrían ir más instrucciones...
		}
		// NO obligatorio cubrir todos los posibles casos (exhaustivo), aunque lo
		// hacemos en el ejemplo.
		default -> texto = "mayor de nueve...";
		};
		return texto;
	}

	/**
	 * Devuelve el texto correspondiente a un número entre cero y cuatro. Si el
	 * valor está entre 5 a 9 lo identificar en ese intervalo devolviendo el texto
	 * correspondiente. Si es mayor de 9, devuelve un texto por defecto.
	 * 
	 * Se utiliza el nuevo switch como "expresión" que se resuelve directamente como
	 * un valor.
	 * 
	 * @param numero número
	 * @return texto texto correspondiente a dicho número
	 */
	public static String resolverConExpresion(int numero) {
		// swith como "expresión"
		return switch (numero) { // el switch se evalúa y devuelve directamente un valor
		case 0 -> "cero"; // notación de flecha -> para retornar el valor (no podemos usar return)
							// ya no utilizamos dos puntos (:) ni la palabra reservada break
		case 1 -> "uno";
		case 2 -> "dos";
		case 3 -> "tres";
		case 4 -> "cuatro";
		case 5, 6, 7, 8, 9 -> "entre cinco y nueve";
		default -> // Es obligatorio cubrir TODOS los posibles casos (debe ser exhaustivo)
					// bien a través de que los case cubran todos los valores
					// o bien finalmente incluyendo el default para cubrir el resto de casos
		{
			// el ejemplo se ha "forzado" el uso de un bloque de instrucciones entre
			// llaves, dado que podemos necesitar escribir varias instrucciones (en este
			// ejemplo no era obligatorio porque solo había una instrucción)
			//
			// Para retornar un valor en un bloque se utiliza la nueva palabra reservada
			// "yield" (Tip: + o - equivalente a return, pero dentro de una expresión
			// switch)
			//
			// En este ejemplo es redundante el uso de yield, al haber solo una instrucción.
			yield "mayor de nueve...";
		}
		};
	}

}