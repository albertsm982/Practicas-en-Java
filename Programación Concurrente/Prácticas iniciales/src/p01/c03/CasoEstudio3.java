package p01.c03;

public class CasoEstudio3 {

	public static void main(String[] args) throws InterruptedException {
		
		Thread hilo_x = new Thread(new MyThreadX('x', 1000, 100000));
		Thread hilo_o = new Thread(new MyThreadO('o', 1000, 100000));
		Thread hilo__ = new Thread(new MyThread_('_', 1000, 100000));
		
		
		hilo_o.setPriority(10);
		hilo_o.start();
		
		hilo_x.setPriority(5);
		hilo_x.start();
		
		hilo__.setPriority(1);
		hilo__.start();
		
		hilo_x.join();
		hilo_o.join();
		hilo__.join();
		
		System.out.println("Buenas");
	}

}
