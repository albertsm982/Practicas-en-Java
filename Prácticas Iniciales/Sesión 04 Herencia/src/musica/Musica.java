package musica;

import java.util.Random;

/**
 * Musica.
 *
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @version 1.0
 */
public class Musica {	

	/**
	 * Afina el instrumento.
	 * 
	 * @param i instrumento
	 */
	public static void afinar(Instrumento i) {
		// ...
		i.tocar(Nota.DO);
		i.tocar(Nota.FA);
	}	
	
	/**
	 * Método principal.
	 * 
	 * @param args argumentos
	 */
	public static void main(String[] args) {
		Viento flauta = new Viento(100.0F);
		Cuerda cuerda = new Cuerda(200.0F);
		Metal metal = new Metal(300.0F);
		
		afinar(flauta); // Upcasting, objeto Viento se asigna a Instrumento
		afinar(cuerda);
		afinar(metal);
	}

	// COMPLETAR
}