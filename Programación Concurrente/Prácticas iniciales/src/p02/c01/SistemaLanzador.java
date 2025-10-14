package p02.c01;

public class SistemaLanzador {


	public static void main(String[] args) {

		/*IParque parque = AdaptadorParqueSincronizado.getInstancia();

		ActividadEntradaPuerta pa = new ActividadEntradaPuerta("A", parque);
		ActividadEntradaPuerta pb = new ActividadEntradaPuerta("B", parque);

		new Thread(pa).start();
		new Thread(pb).start();*/
	
		IParque parque = AdaptadorParqueSincronizado.getInstancia();
		char letra_puerta = 'A';

		for (int i = 0; i < Integer.parseInt(args[0]); i++) {
			
			String puerta = ""+((char) (letra_puerta++));
			
			// Creación de hilos de entrada
			ActividadEntradaPuerta entradas = new ActividadEntradaPuerta(puerta, parque);
			new Thread (entradas).start();
			
			
		}
	}

}
