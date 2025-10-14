package p01.c04;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CasoEstudio4 {

	public static void main(String[] args) {
		
		Thread hilo_x = new Thread(new MyThreadX());
		Thread hilo_o = new Thread(new MyThreadO());
		Thread hilo__ = new Thread(new MyThread_());
		
		ExecutorService exec = Executors.newCachedThreadPool();
		//ExecutorService exec = Executors.newSingleThreadExecutor();
		//ExecutorService exec = Executors.newFixedThreadPool(1);
		//ExecutorService exec = Executors.newFixedThreadPool(2);
		//ExecutorService exec = Executors.newFixedThreadPool(3);
		
		//ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
		//exec.scheduleAtFixedRate(hilo_x, 0, 5, TimeUnit.SECONDS);
		
		
		exec.execute(hilo_x);
		exec.execute(hilo_o);
		exec.execute(hilo__);
		
		//exec.shutdown();
		
	}

}
