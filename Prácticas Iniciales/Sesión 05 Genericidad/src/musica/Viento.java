package musica;

/**
 * Viento.
 *
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @author Alberto Santos Martínez
 * @version 1.0
 */
public class Viento extends Instrumento {	
	
	/**
	 * Constructor.
	 * 
	 * @param precio precio
	 */
	public Viento(float precio) {
		super(precio);
	}
	
	@Override
	public void tocar(Nota nota) {		
		System.out.printf("Viento.tocar(): %s%n", nota.toString());
	}

	@Override
	public String toString() {
		return "[" + this.consultarPrecio() +  ":Viento]";
	}
}
