package es.ubu.gii.edat.sesion01;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Frecuencias {

	public static <E> int frecuencia(Collection<E> coleccion, Object o, boolean usarEquals) {

		Iterator<E> iter = coleccion.iterator();
		int contador = 0;

		
		if (usarEquals) {
			while (iter.hasNext()) {
				E elemento = iter.next();
				if (elemento.equals(o))
					contador++;
			}
			return contador;
		} else {
			for (E elemento : coleccion) {
				if (elemento == o) {
					contador++;
				}
			}
			return contador;
		}
	}

	public static <E> E menosFrecuente(Collection<E> coleccion, boolean usarEquals) {
		
		if(coleccion == null)
		{
			throw new NoSuchElementException();
		}
		Iterator<E> iter = coleccion.iterator();
		Iterator<E> iterAux = coleccion.iterator();
		int contador = 0;
		int menor = 0;
		E resultado = null;

		if (usarEquals) {
			while (iter.hasNext()) {
				E elemento = iter.next();	
				while (iterAux.hasNext()) {
					E elementoAux = iter.next();

					if (elemento.equals(elementoAux))
						contador++;
				}
				if (contador < menor) {
					menor = contador; // para saber cuantas veces existe ese elemento (aunque no se pida)
					resultado = elemento;
				}
			}
			return resultado;
		} else {
			for (E elemento : coleccion) {
				contador = 0;
				for (E elementoAux : coleccion) {
					if (elemento == elementoAux) 
						contador++;
				}
				if (contador < menor) {
					menor = contador; // para saber cuantas veces existe ese elemento (aunque no se pida)
					resultado = elemento;
				}
			}
			return resultado;

		}
	}

}
