package p01.c04;

public class MyThreadO implements Runnable{

	@Override
	public void run() {
		
		for( int i = 0; i < 2000000; i++) {
			System.out.print("o");
			//Thread.yield();
		}
		System.out.println();
	}
}
