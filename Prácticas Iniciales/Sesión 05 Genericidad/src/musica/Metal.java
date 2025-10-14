package musica;


/**
 * Metal.
 *
 * @autor Alberto Santos Martinez
 * 
 */
public class Metal extends Instrumento {	
	
	/**
	 * Constructor.
	 * 
	 * @param precio precio
	 */
	public Metal(float precio) {
		super(precio);
	}
	
	@Override
	public void tocar(Nota nota) {		
		System.out.printf("Metal.tocar(): %s%n", nota.toString());
	}

	@Override
	public String toString() {
		return "[" + this.consultarPrecio() +  ":Metal]";
	}
}
