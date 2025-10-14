package es.ubu.gii.edat.pr01;

import java.util.Random;
import org.junit.Test;
import es.ubu.gii.edat.pr01.ElementoMayoritario.RespuestaMayoritaria;

/**
 * 
 * Clase que pretende servir como ejemplo para la realización de análisis
 * de la complejidad algortimica de un programa (o parte de él).
 * 
 * Las salidas de datos se han formateado de manera que cumplen con el formato .csv
 * Basta con almacenarlas en un fichero de texto para luego poder importar esos datos
 * a una hoja de cálculo y realizar las operaciones y gráficas que sean necesarias. 
 * 
 * @author Alberto Santos Martínez
 * @author bbaruque
 *
 */

public class RendimientoTest {

	// Numero maximo de elementos que se quiere probar a trabajar
	int limiteElementos = 10000;
	// Numero de elementos en el que se quiere aunmentar el tamaño en cada paso
	// Se propone obtener 10 mediciones diferentes por cada experimento
	int paso = limiteElementos/10;
	
	// Arrays sobre los que se trabajará
	Integer [] listaTrabajoInt;
	Character [] listaTrabajoChar;
	
	public void comprobacionEnterosIterativo(){

		double inicio, fin;
		
		System.out.println("Tiempos de ejecución para comprobación de mayoritarios con el método iterativo");
		System.out.println("en diferentes tamaños de arrays: ");
		System.out.println("Array de Enteros - Iterativo: ");
		
		for(int i=1; i<limiteElementos; i=i+paso){
			
			// Generamos el array de enteros
			listaTrabajoInt = this.generarArrayAleatorioInteger(i);
			
			inicio = System.currentTimeMillis();
			ElementoMayoritario.mayoritarioIterativo(listaTrabajoInt);
			fin = System.currentTimeMillis(); 
			
			System.out.println("Num.Elem,"+i+",tiempo(ms),"+(fin-inicio));

		}
		
	}
	
	public void comprobacionEnterosRecursivo(){

		double inicio, fin;
		
		System.out.println("Tiempos de ejecución para comprobación de mayoritarios con el método recursivo");
		System.out.println("en diferentes tamaños de arrays: ");
		System.out.println("Array de Enteros - Recursivo: ");
		
		for(int i=1; i<limiteElementos; i=i+paso){
			
			// Generamos el array de enteros
			listaTrabajoInt = this.generarArrayAleatorioInteger(i);
			
			inicio = System.currentTimeMillis();
			ElementoMayoritario.mayoritarioRecursivo(listaTrabajoInt);
			fin = System.currentTimeMillis(); 
			
			System.out.println("Num.Elem,"+i+",tiempo(ms),"+(fin-inicio));

		}
		
	}
	
	public void comprobacionCharactersIterativo(){

		double inicio, fin;
		
		System.out.println("Tiempos de ejecución para comprobación de mayoritarios con el método iterativo");
		System.out.println("en diferentes tamaños de arrays: ");
		System.out.println("Array de Carácteres - Iterativo: ");
		
		for(int i=1; i<limiteElementos; i=i+paso){
			
			// Generamos el array de enteros
			listaTrabajoChar = this.generarArrayAleatorioCaracteres(i);
			
			inicio = System.currentTimeMillis();
			ElementoMayoritario.mayoritarioIterativo(listaTrabajoChar);
			fin = System.currentTimeMillis(); 
			
			System.out.println("Num.Elem,"+i+",tiempo(ms),"+(fin-inicio));

		}
		
	}
	
	public void comprobacionCharactersRecursivo(){

		double inicio, fin;
		
		System.out.println("Tiempos de ejecución para comprobación de mayoritarios con el método recursivo");
		System.out.println("en diferentes tamaños de arrays: ");
		System.out.println("Array de Carácteres - Recursivo: ");
		
		for(int i=1; i<limiteElementos; i=i+paso){
			
			// Generamos el array de enteros
			listaTrabajoChar = this.generarArrayAleatorioCaracteres(i);
			
			inicio = System.currentTimeMillis();
			ElementoMayoritario.mayoritarioRecursivo(listaTrabajoChar);
			fin = System.currentTimeMillis(); 
			
			System.out.println("Num.Elem,"+i+",tiempo(ms),"+(fin-inicio));

		}
		
	}

	/**
	* Se repiten los dos tests que vemos arriba solamente incrementando el numero de elementos
	* que se incluyen en ambas listas. De esta forma, se puede comprobar la progresión del tiempo
	* que se tarda en finalizar cada test en función del tamaño de las estructuras empleadas.
	* 
	* Permite aumentar el tamaño de las observaciones hasta que nos encontramos con una tamaño
	* suficientemente grande como para apreciar claramente la diferencia en tiempo. 
	*/
	@Test 
	public void testIncremental(){
		
		limiteElementos = 100;
		
		/* El test va aumentando el tamaño del problema en un bucle infinito.
		 Es necesario interrumpir la ejecución manualmente para que finalice la prueba.
		 Esto solo se hace cuando no se conoce bien el tamaño de problema en el que se 
		 empiecen a apreciar claramente las diferencias en tiempo.
		 (Ese tamaño puede ser diferente por cada método) */
		
		while(true){
			
			limiteElementos = limiteElementos * 10;
			paso = limiteElementos/10;

			System.out.println(String.format("Realizando prueba con %d elementos",limiteElementos));
			
			comprobacionEnterosIterativo();
			comprobacionEnterosRecursivo();
			comprobacionCharactersIterativo();
			comprobacionEnterosRecursivo();
			
			System.out.println("Prueba finalizada");
			
		}
		
	}
	
	/**
	 * Permite generar una lista de enteros aleatorios del tamaño solicitado.
	 * 
	 * Los numeros generdos estarán en el rango del 0 al tamaño solicitado * 1,5.
	 * Se asegura que no habrá elementos duplicados en la lista generada. 
	 * 
	 * @param tamano
	 * @return
	 */
	private Integer[] generarArrayAleatorioInteger(int tamano) {
	    Integer[] array = new Integer[tamano];
	    Random random = new Random();
	    for (int i = 0; i < tamano; i++) {
	        array[i] = random.nextInt(9) + 1; // números aleatorios del 1 al 9
	    }
	    return array;
	}
	
	private Character[] generarArrayAleatorioCaracteres(int tamano) {
	    Random random = new Random();
	    Character[] array = new Character[tamano];
	    for (int i = 0; i < tamano; i++) {
	        char c = (char) (random.nextInt(7) + 'a'); // letras aleatorias entre la 'a' y la 'g'
	        array[i] = c;
	    }
	    return array;
	}


	
}