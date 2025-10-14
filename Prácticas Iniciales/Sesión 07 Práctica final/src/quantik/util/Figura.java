package quantik.util;

/**
 * Enumeración Figura que contiene los distintos tipos de figuras que tiene el juego.
 *
 * @author Alberto Santos Martínez
 */
public enum Figura {

	/** El cilindro. */
	CILINDRO("CL"),
	/** El cono. */
	CONO("CN"), 
	/** El cubo. */
	CUBO("CB"), 
	/** La esfera. */
	ESFERA("ES");

	/** Texto que contiene la figura. */
	private String texto;
	
	/**
	 * Metodo que instacia el texto de la figura.
	 *
	 * @param texto El texto
	 */
	private Figura(String texto) {
		this.texto = texto;
	}

	/**
	 * Clase aTexto que devuelve el valor de texto, es decir, la figura.
	 *
	 * @return Devuelve el string que contiene el nombre de la figura.
	 */
	public String aTexto(){
		return texto;
	}
	
}
