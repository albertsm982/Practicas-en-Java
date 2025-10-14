package musica;

import java.util.Random;

import musica.Cuerda;
import musica.Metal;
import musica.Musical;
import musica.Nota;
import musica.Viento;

/**
 * Musica.
 *
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @author Alberto Santos Martínez
 * @version 1.0
 */
public class Musica {
	
	private static Random random = new Random();
	
	public static Nota obtenerNotaAleatoria() {
		return Nota.values()[random.nextInt(0, Nota.values().length)];
	}
	
	private static Musical obtenerMusicalAleatorio() {
		int index = random.nextInt(0, 3);
		
		float precio = random.nextFloat(1, 1000);
		
		switch (index) {
		case 0:
			return new Viento(precio);
		case 1:
			return new Cuerda(precio);
		default:
			return new Metal(precio);
		}
	}

	/**
	 * Afina el instrumento.
	 * 
	 * @param i instrumento
	 */
	public static void afinar(Musical i) {
		i.tocar(obtenerNotaAleatoria());
	}

	/**
	 * Método principal.
	 * 
	 * @param args argumentos
	 */
	public static void main(String args[]) {
		
		for(int n = 0; n < 10; n++) {
			Musical musical = obtenerMusicalAleatorio();
			afinar(musical);
		}
	}
	
}