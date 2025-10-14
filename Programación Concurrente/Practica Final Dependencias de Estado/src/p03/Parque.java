package p03;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Clase Adaptador parque. Esta clase representa un parque con un número limitado de
 * personas permitidas y puertas de entrada/salida. Implementa la interfaz
 * IParque, la cual define los métodos entrarAlParque y salirDelParque.
 * 
 * @author Alberto Santos Martínez
 * @author Jon Ander Incera Moreno  
 */
public class Parque implements IParque {

	private int contadorPersonasTotales;
	private Hashtable<String, Integer> contadoresPersonasPuerta;
	private int aforo;

	/**
	 * Constructor de la clase Parque que inicializa los contadores de personas y el
	 * aforo máximo.
	 * 
	 * @param aforo el número máximo de personas permitidas en el parque
	 */
	public Parque(int aforo) {
		contadorPersonasTotales = 0;
		contadoresPersonasPuerta = new Hashtable<String, Integer>();
		this.aforo = aforo;
	}

	/**
	 * Método synchronized que permite a una persona entrar al parque por una puerta
	 * determinada.
	 * 
	 * @param puerta la puerta por la cual la persona entra al parque
	 */
	@Override
	public synchronized void entrarAlParque(String puerta) {

		// Llamamos a la precondición.
		comprobarAntesDeEntrar();

		// En caso de que no haya entradas por esa puerta, la inicializamos
		if (contadoresPersonasPuerta.get(puerta) == null) {
			contadoresPersonasPuerta.put(puerta, 0);
		}

		// Aumentamos el contador total de la gente que está en el parque y el de la
		// puerta
		contadorPersonasTotales++;
		contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta) + 1);

		// Imprimimos el estado actual del parque
		imprimirInfo(puerta, "Entrada");

		// Se comprueba el invariante
		checkInvariante();

		// Avisamos al resto de hilos que están a la espera de que hemos liberado el
		// recurso
		notifyAll();
	}

	/**
	 * Método que imprime el estado actual del parque, incluyendo el número total de
	 * personas y el número de personas por puerta.
	 * 
	 * @param puerta     la puerta que se está usando para entrar o salir del parque
	 * @param movimiento la acción que se está realizando (Entrada o Salida)
	 */
	private void imprimirInfo(String puerta, String movimiento) {
		System.out.println(movimiento + " por puerta " + puerta);
		System.out.println("--> Personas en el parque " + contadorPersonasTotales);
		for (String p : contadoresPersonasPuerta.keySet()) {
			System.out.println("----> Por puerta " + p + " " + contadoresPersonasPuerta.get(p));
		}
		System.out.println(" ");
	}

	/**
	 * Método que suma los contadores de personas por puerta y devuelve el total.
	 * 
	 * @return el número total de personas en el parque
	 */
	private int sumarContadoresPuerta() {
		int sumaContadoresPuerta = 0;
		Enumeration<Integer> iterPuertas = contadoresPersonasPuerta.elements();
		while (iterPuertas.hasMoreElements()) {
			sumaContadoresPuerta += iterPuertas.nextElement();
		}
		return sumaContadoresPuerta;
	}

	/**
	 * Método protegido para comprobar los invariantes de la clase Parque. Se
	 * asegura de que la suma de los contadores de las puertas sea igual al contador
	 * de personas totales. También se asegura de que el número de personas en el
	 * parque no supere el aforo máximo permitido y de que no puedan salir personas
	 * si el parque está vacío.
	 * 
	 */
	protected void checkInvariante() {
		assert sumarContadoresPuerta() == contadorPersonasTotales
				: "INV: La suma de contadores de las puertas debe ser igual al valor del contador del parte";
		assert contadorPersonasTotales <= aforo : "INV: El número de personas supera el aforo máximo permitido";
		assert contadorPersonasTotales >= 0 : "INV: No pueden salir personas si está vacío el parque";

	}

	/**
	 * Método sincronizado para comprobar si el parque está lleno antes de permitir
	 * que una persona entre. El método espera mientras el parque está lleno y
	 * despierta a los hilos en espera cuando hay espacio disponible
	 * 
	 */
	protected synchronized void comprobarAntesDeEntrar() {
		// Se mantiene a la espera comprobando si el parque está lleno.
		if (contadorPersonasTotales == aforo) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Método que permite a una persona salir del parque a través de una puerta
	 * especificada.
	 */
	@Override
	public synchronized void salirDelParque(String puerta) {

		// Llamamos a la postcondición.
		comprobarAntesDeSalir();

		// En caso de que no haya salidas por esa puerta, la inicializamos
		if (contadoresPersonasPuerta.get(puerta) == null) {
			contadoresPersonasPuerta.put(puerta, 0);
		}

		// Decrementamos el contador total de la gente que está en el parque y el de la
		// puerta
		contadorPersonasTotales--;
		contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta) - 1);

		// Imprimimos el estado actual del parque
		imprimirInfo(puerta, "Salida");

		// Se comprueba el invariante
		checkInvariante();

		// Avisamos al resto de hilos que están a la espera de que hemos liberado el
		// recurso
		notifyAll();
	}

	/**
	 * Método que mantiene al hilo a la espera de que se cumpla la postcondición
	 * antes de salir del parque. Se mantiene a la espera comprobando si el parque
	 * está vacío
	 * 
	 */
	protected synchronized void comprobarAntesDeSalir() {
		// Se mantiene a la espera comprobando si el parque está vacío.
		if (contadorPersonasTotales == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}