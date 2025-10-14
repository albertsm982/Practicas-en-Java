package p01.c01;

public class CasoEstudio1 {

	public static void main(String[] args) {
		
		Thread hilo_x = new Thread(new MyThreadX());
		Thread hilo_o = new Thread(new MyThreadO());
		Thread hilo__ = new Thread(new MyThread_());
		Thread hilo_y = new Thread(new MyThreadY());
		
		hilo_x.start();
		hilo_o.start();
		hilo__.start();
		hilo_y.start();
	}

}
