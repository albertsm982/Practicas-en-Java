package genericidad;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author Alberto Santos Martínez
 *
 * @param <E>
 */
public class Lista <E> {
	
	private List<E> internalList;
	private int tamaño;
	
	public Lista(int tamaño){
		internalList = new ArrayList<>(tamaño);
		this.tamaño = tamaño;
		
		for (int i = 0; i < tamaño ; i++) {
			internalList.add(null);
		}
	}
	
	public void set (int posicion, E valor) {
		if(isPositionInLista(posicion)) {
			internalList.set(posicion, valor);
		}
	}
	
	public E get (int posicion) {
		if(isPositionInLista(posicion))
			return internalList.get(posicion);
		else
			return null;
	}
	
	public int size() {
		return internalList.size();
	}

	private boolean isPositionInLista (int posicion) {
		return posicion >= 0 && posicion < tamaño;
	}
}
