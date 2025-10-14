package genericidad;

import java.util.Random;

import musica.Cuerda;
import musica.Instrumento;
import musica.Metal;
import musica.Musica;
import musica.Musical;
import musica.Nota;
import musica.Viento;

/**
 * 
 * @author Alberto Santos Martínez
 *
 */
public class Principal {
	
	public static Random random = new Random();
	
	private static Lista<Nota> rellenarNotas (){
		Lista<Nota> listaNotas = new Lista<>(10);
		for (int i = 0; i < 10; i++) {
			listaNotas.set(i, Musica.obtenerNotaAleatoria());
		}
		return listaNotas;
	}
	
	private static void mostrarNotas (Lista<Nota> notas) {
		for (int i = 0; i <notas.size(); i++ ) {
			System.out.println(notas.get(i).toString());
		}
	}
	
	private static Lista<Instrumento> rellenarInstrumentos(){
		Lista<Instrumento> listaInstrumentos = new Lista<>(10);
		for(int i = 0; i < 10; i++) {
			listaInstrumentos.set(i, obtenerInstrumentoAleatorio());
		}
		return listaInstrumentos;
	}
	
	private static void mostrarInstrumentos (Lista<Instrumento> instrumentos) {
		for(int i = 0; i < instrumentos.size(); i++) {
			System.out.println(instrumentos.get(i).toString());
		}
	}
	
	private static void mostrar(Lista<?> objetos) {
		for(int i = 0; i < objetos.size(); i++) {
			System.out.println(objetos.get(i).toString());
		}
	}
	
	private static Instrumento obtenerInstrumentoAleatorio() {
		int index = random.nextInt(0,2);
		switch(index) {
		case 0:
			return new Viento (100.F);
		case 1:
			return new Cuerda (200.F);
		default:
			return new Metal (300.F);
		}
	}
	
	 public static void main(String[] args) {
		Lista<Nota> notas = rellenarNotas();
		mostrar(notas);
		
		Lista<Instrumento> instrumentos= rellenarInstrumentos();
		mostrar(instrumentos);
	}
	
}
