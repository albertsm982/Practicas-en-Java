package musica;

/**
 * Cuerda
 *
 * @autor Alberto Santos Martinez
 * 
 */
public class Cuerda extends Instrumento{
	
	/**
	 * Constructor.
	 * 
	 * @param precio precio
	 */
	public Cuerda(float precio) {
		super(precio);
	}
	
	@Override
	public void tocar(Nota nota) {		
		System.out.printf("Cuerda.tocar(): %s%n", nota.toString());
	}

	@Override
	public String toString() {
		return "[" + this.consultarPrecio() +  ":Cuerda]";
	}

}