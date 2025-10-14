package p01.c04;

public class MyThreadX implements Runnable{

	@Override
	public void run() {
		
		for( int i = 0; i < 2000000; i++) {
			System.out.print("x");
			//Thread.yield();
		}
		System.out.println();
	}

	
}
