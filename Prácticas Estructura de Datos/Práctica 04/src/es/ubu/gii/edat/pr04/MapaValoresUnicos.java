package es.ubu.gii.edat.pr04;

import java.util.*;

/**
 * Clase MapaValoresUnicos que genera un mapa que permite asegurar que haya
 * tanto claves como valores únicos. Para ello, esta clase crea dos mapas
 * distintos los cuales a su vez son leidos sincronizadamente. Uno de forma
 * estándar y el otro de forma inversa. Esta clase extiende de AbstractMap por
 * lo que tambien sobreescribe los metodos que se incluyen en dicha clase.
 * 
 * @author Alberto Santos Martínez
 * @param <K> El tipo de la clave
 * @param <V> El tipo del valor
 */
public class MapaValoresUnicos<K, V> extends AbstractMap<K, V> {

	/** El mapa directo que se va editando de forma estándar. */
	private Map<K, V> directo;

	/** El mapa inverso que se va modificando de forma inversa. */
	private Map<V, K> inverso;

	/**
	 * Método constructor de la clase MapaValoresUnicos que inicializa los mapas de
	 * directo e inverso.
	 */
	public MapaValoresUnicos() {
		directo = new HashMap<>();
		inverso = new HashMap<>();
	}

	/**
	 * Metodo entrySet cuyo proposito es el de devolver una vista del conjunto de
	 * entradas que tiene el mapa "directo".
	 *
	 * @return Una vista del conjunto de entradas del mapa
	 */
	@Override
	public Set<Entry<K, V>> entrySet() {
		return directo.entrySet();
	}

	/**
	 * Método put que asocia un valor con una clave en el mapa. Si el mapa contenía
	 * previamente un mapeo para la clave, el valor anterior se reemplaza por el
	 * valor especificado.
	 * 
	 * @param key   la clave
	 * @param value el valor
	 * @return el valor anterior asociado con la clave o null si no había ninguno.
	 * @throws IllegalArgumentException si el valor ya se encuentra en el mapa.
	 */
	@Override
	public V put(K key, V value) {
		if (inverso.containsKey(value))
			throw new IllegalArgumentException("El valor a introducir ya se encuentra en el mapa: " + value);

		V valorAnterior = directo.put(key, value);

		if (valorAnterior != null)
			inverso.remove(valorAnterior);
		inverso.put(value, key);

		return valorAnterior;
	}

	/**
	 * Método remove que elimina una clave del mapa. Al tener que borrar dicha
	 * clave, tambien se elimina su valor correspondiente.
	 *
	 * @param key la clave a borrar
	 * @return el valor borrado que iba referenciado a la clave.
	 */
	@Override
	public V remove(Object key) {
		if (!directo.containsKey(key))
			return null;

		V value = directo.remove(key);

		if (value != null)
			inverso.remove(value);

		return value;
	}

	/**
	 * Método clear que limpia los tanto las claves como los valores de los mapas
	 * existentes.
	 */
	@Override
	public void clear() {
		directo.clear();
		inverso.clear();
	}

	/**
	 * Método containsValue que comprueba si el valor pasado por parámetro se
	 * encuentra o no en el mapa de inverso.
	 *
	 * @param value el valor a comprobar
	 * @return true, en caso de que el valor se encuentre en el mapa. false, en caso
	 *         contrario
	 */
	@Override
	public boolean containsValue(Object value) {
		return inverso.containsKey(value);
	}

	/**
	 * Método containsKey que devuelve un booleano dependiendo de si la clave pasada
	 * por parámetro se encuentra en en el mapa "directo" o no.
	 *
	 * @param key la clave a comprobar.
	 * @return true, en caso de que el mapa contenga la clave. false, en caso
	 *         contrario.
	 */
	@Override
	public boolean containsKey(Object key) {
		return directo.containsKey(key);
	}

	/**
	 * Método get que devuelve el valor al que una clave pasada por parámetro tiene
	 * asginado
	 *
	 * @param key la clave del valor a obtener.
	 * @return el valor que tiene asignado la clave.
	 */
	@Override
	public V get(Object key) {
		if (!directo.containsKey(key))
			return null;
		return directo.get(key);
	}

	/**
	 * Método inverso que permite devolver el mapa inverso actual facilitando los
	 * valores inicialmente insertados como claves y viceversa. Para ello crea un
	 * nuevo mapa de tipo MapaValoresUnicos al que se le asigna el mapa inverso al
	 * campo directo del mapa recién creado. Para posteriormente asignar el mapa
	 * directo al campo inverso del mapa que queremos devolver.
	 * 
	 * @return inversoMapa mapa que contiene los mapas inverso y directo
	 *         intercambiados representando el mapa inveso del mapa original
	 */
	public MapaValoresUnicos<V, K> inverso() {
		MapaValoresUnicos<V, K> inversoMapa = new MapaValoresUnicos<>();

		inversoMapa.directo = inverso;
		inversoMapa.inverso = directo;

		return inversoMapa;
	}

	/**
	 * Método forcePut que permite eliminar cualquier entrada preexistente con el
	 * valor especificado, sustituyendole por la pareja clave-valor pasada por
	 * parámetro. Para ello se obtiene el valor anterior el cual si existe se
	 * elimina. En el caso de que el valor que estamos pasando por parámetro se
	 * encuentre dentro del mapa inverso, se eliminará dicho valor del mapa y
	 * gracias a que remove nos devuelve la clave, la usamos para borrar tambien el
	 * valor del mapa directo. Al final del todo se insertarán la clave junto con su
	 * valor en ambos mapas.
	 * 
	 *
	 * @param key   la clave a introducir
	 * @param value el valor a introducir
	 * @return valorAnterior el cual es el valor que iba asociado a la clave pasada
	 *         por parámetro o nulo en caso de que no hubiera ningún valor con esa
	 *         clave.
	 */
	public V forcePut(K key, V value) {
		V valorAnterior = directo.get(key);

		if (valorAnterior != null)
			inverso.remove(valorAnterior);
		if (inverso.containsKey(value)) {
			K claveAnterior = inverso.remove(value);
			directo.remove(claveAnterior);
		}

		directo.put(key, value);
		inverso.put(value, key);

		return valorAnterior;
	}
}
