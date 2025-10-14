package tiempo;

/**
 * Ejercicio de lanzamiento de excepciones.
 * 
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @version 1.0
 */
public class Alarma {

	/** Hora. */
	private int hora;

	/** Minuto. */
	private int minuto;

	/**
	 * Inicializa la hora y minuto de una alarma.
	 * 
	 * @param hora   hora
	 * @param minuto minuto
	 */
	public Alarma(int hora, int minuto) throws Exception {
		if (isHoraValida(hora) || isMinutoValido(minuto)) {
			if (isHoraValida(hora))
				this.hora = hora;
			else
				throw new RuntimeException("Hora incorrecta: " + hora);

			if (isMinutoValido(minuto))
				this.minuto = minuto;
			else
				throw new Exception("Minuto incorrecto: " + minuto);
		} else
			throw new Exception("Hora y minuto incorrectos: " + hora + ":" + minuto);
	}

	private boolean isHoraValida(int horas) {
		return horas >= 0 && horas < 24;
	}

	private boolean isMinutoValido(int minutos) {
		return minutos >= 0 && minutos < 60;
	}

	/**
	 * Consulta la hora.
	 * 
	 * @return hora
	 */
	public int consultarHora() {
		return hora;
	}

	/**
	 * Consulta el minuto.
	 * 
	 * @return alarma
	 */
	public int consultarMinuto() {
		return minuto;
	}
}
