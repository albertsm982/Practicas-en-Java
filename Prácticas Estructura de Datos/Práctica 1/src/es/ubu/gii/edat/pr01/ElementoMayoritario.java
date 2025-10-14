package es.ubu.gii.edat.pr01;

// TODO: Auto-generated Javadoc
/**
 * Clase que alberga el código completo para la Práctica 01.
 *
 * @author bbaruque
 */
public class ElementoMayoritario {

	/**
	 * Para poder devolver dos valores diferentes por parte de un mismo método, se
	 * necesita una clase sencilla con dos atributos. En este caso, la clase
	 * RespuestaMayoritaria permite encapsular en el mismo objeto: - elemento
	 * mayoritario encontrado - el numero de veces que se repite (o frecuencia) como
	 * un entero
	 * 
	 * Al devolver la respuesta de cada metodo, se deberán incluir los dos datos
	 * encontrados dentro de un objeto de este tipo y devolverlo como respuesta.
	 *
	 * @author Alberto Santos Martínez
	 * @author bbaruque
	 * @param <E> the element type
	 */
	public static class RespuestaMayoritaria<E> {

		/** The elemento. */
		private E elemento;
		
		/** The frecuencia. */
		private int frecuencia;

		/**
		 * Instantiates a new respuesta mayoritaria.
		 *
		 * @param elemento the elemento
		 * @param frecuencia the frecuencia
		 */
		public RespuestaMayoritaria(E elemento, int frecuencia) {
			this.elemento = elemento;
			this.frecuencia = frecuencia;
		}

		/**
		 * Gets the elemento.
		 *
		 * @return the elemento
		 */
		public E getElemento() {
			return elemento;
		}

		/**
		 * Gets the frecuencia.
		 *
		 * @return the frecuencia
		 */
		public int getFrecuencia() {
			return frecuencia;
		}
	}

	/**
	 * Encuentra el elemento mayoritario de forma iterativa en un array. Si el
	 * elemento mayoritario no existe, la respuesta tiene valor nulo y frecuencia 0.
	 * 
	 * @param <E>   Tipo de elemento del array
	 * @param array array en el que se buscará el elemento mayoritario
	 * @return un objeto de tipo RespuestaMayoritaria que encapsula el elemento
	 *         mayoritario y su frecuencia. Si no hay mayoritario, devolverá null
	 */
	public static <E> RespuestaMayoritaria<E> mayoritarioIterativo(E[] array) {

		// Inicializamos un contador y la variable que guarda la frecuencia máxima a
		// cero, y el elemento que aparece más veces dentro del array y que puede
		// llegara a ser el mayoritario a nulo
		int contador = 0;
		int frecMax = 0;
		E elementoMax = null;

		// Iteramos sobre todos los elementos del array teniendo en cuenta su tamaño
		for (int i = 0; i < array.length; i++) {

			// Reiniciamos el contador en cada iteración para saber las veces que aparece
			// cada elemento
			contador = 0;

			// Iteramos sobre el array de nuevo para calcular la frecuencia del elemento
			// actual y comprobar si hay otro que aparece más veces
			for (E elemento : array) {
				if (elemento == array[i])
					contador++;

				// Si encontramos un elemento con una frecuencia mayor, actualizamos la variable
				// que guarda la frecuencia máxima y el reescribimos el elemento con frecuencia
				// máxima
				if (contador > frecMax) {
					frecMax = contador;
					elementoMax = elemento;
				}
			}
		}

		// Si la frecuencia máxima es mayor a la mitad del tamaño del array, el
		// elemento sí que esmayoritario y le devolvemos junto su frecuencia. En caso
		// contrario, devolveremos un elemento nulo con una frecuecia igual a 0.
		if (frecMax > array.length / 2)
			return new RespuestaMayoritaria<E>(elementoMax, frecMax);
		else
			return new RespuestaMayoritaria<E>(null, 0);
	}

	/**
	 * El método recursivo utiliza una técnica de Divide y Vencerás para poder
	 * encontrar el elemento mayoritario. Busca este mayoritario (en caso de que
	 * haya, teniendo en cuenta que un número es mayoritario cuando el número de
	 * veces que aparece es superior a la mitad del tamaño del array) dentro de un
	 * array de tipo genérico y devuelve un objeto de tipo RespuestaMayoritaria el
	 * cual contiene dicho elemento mayoritario junto con su frecuencia en el array.
	 *
	 * @param <E>   el tipo genérico del array y del elemento mayoritario
	 * @param array el array donde se busca el elemento mayoritario
	 * @return un objeto de tipo RespuestaMayoritaria que encapsula el elemento
	 *         mayoritario y su frecuencia. Si no hay mayoritario, devolverá null
	 */
	public static <E> RespuestaMayoritaria<E> mayoritarioRecursivo(E[] array) {

		// Guardamos en una variable el resultado de la función auxiliar para
		// encontrar el mayoritario
		E elemento = auxiliarRecursivo(array, 0, array.length - 1);

		// Guardamos en una variable las veces que aparece el elemento mayoritario
		// en el array
		int frecuencia = frecuencia(array, elemento, 0, array.length - 1);

		// Devolvemos un objeto de tipo RespuestaMayoritaria con el elemento mayoritario
		// y su frecuencia
		return new RespuestaMayoritaria<E>(elemento, frecuencia);
	}

	/**
	 * Método auxiliar del método recursivo para buscar el elemento mayoritario en
	 * un rango dado de un array.
	 * 
	 * @param <E>    el tipo de los elementos del array.
	 * @param array  el array en el que se busca el mayoritario.
	 * @param inicio el índice del inicio del rango.
	 * @param fin    el índice del tope del rango.
	 * @return el elemento mayoritario dentro del rango dado del array, o null en
	 *         caso de que no exista.
	 */
	private static <E> E auxiliarRecursivo(E[] array, int inicio, int fin) {
		// Si el array tiene solo un elemento, ese elemento es el mayoritario.
		if (inicio == fin)
			return array[inicio];

		// Si el rango del inicio es mayor que el del fin, no hay mayoritario por lo que
		// devuelvo nulo.
		if (inicio > fin)
			return null;

		// Calculo una variable que calule la mitad del rango.
		int mitad = (int) Math.floor((inicio + fin) / 2);

		// Guardo en dos variables y llamo recursivamente a la función para dividir en
		// dos subrangos.
		E m1 = auxiliarRecursivo(array, inicio, mitad);
		E m2 = auxiliarRecursivo(array, mitad + 1, fin);

		// Si la frecuencia del subrango m1 es mayor que la mitad del array, el
		// mayoritario en el rango dado es m1 y le devuelvo.
		if (frecuencia(array, m1, inicio, fin) > (fin - inicio + 1) / 2)
			return m1;

		// Si la frecuencia del subrango m2 es mayor que la mitad del array, el
		// mayoritario en el rango dado es m2 y le devuelvo.
		else if (frecuencia(array, m2, inicio, fin) > (fin - inicio + 1) / 2)
			return m2;

		// En caso de que no haya mayoritario en el rango, se devolverá null.
		else
			return null;

	}

	/**
	 * El método de frecuencia, calcula el número de veces que un elemento aparece
	 * en un array dentro de un rango dado por parámetro.
	 *
	 * @param <E>    el tipo de los elementos del array.
	 * @param array  el array en el que se buscas el elemento.
	 * @param o      el elemento que vamos a buscar.
	 * @param inicio el índice del inicio del rango.
	 * @param fin    el índice del tope del rango.
	 * @return el número de veces que aparece el elemento en el rango dado
	 */
	private static <E> int frecuencia(E[] array, E o, int inicio, int fin) {
		int contador = 0; // Inicializamos el contador a cero.
		for (int i = inicio; i <= fin; i++) {
			if (array[i] == o) { // Si encontramos que el elemento dado es igual a
									// otro del array dentro del rango dado,
									// incrementamos el contador.
				contador++;
			}
		}
		return contador; // Devolvemos el número de veces que aparece el elemento
							// en el rango dado
	}
}
