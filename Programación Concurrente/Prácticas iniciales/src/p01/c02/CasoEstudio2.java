package p01.c02;

public class CasoEstudio2 {

	public static void main(String[] args) throws InterruptedException {
		
		Thread hilo_x = new Thread(new MyThreadX());
		Thread hilo_o = new Thread(new MyThreadO());
		Thread hilo__ = new Thread(new MyThread_());
		
		hilo_x.start();
		hilo_o.start();
		hilo__.start();
		
		hilo_x.join();
		hilo_o.join();
		hilo__.join();
		
		System.out.println("Buenas");
	}

}
