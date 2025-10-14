package musica;


/**
 * Instrumento.
 *
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @author Alberto Santos Martínez
 * @version 1.0
 */
public abstract class Instrumento implements Musical{

	/** Precio. */
	private float precio;
	
	/**
	 * Constructor. 
	 * 
	 * @param precio precio
	 */
	public Instrumento(float precio) {
		this.precio= precio;
	}

	/**
	 * Consulta el precio.
	 * 
	 * @return precio
	 */
	public float consultarPrecio() {
		return precio;
	}
	
	@Override
	public abstract void tocar(Nota n);
	
	@Override
	public String toString() {
		return "[" + consultarPrecio() +  ":Instrumento]";
	}
}