package p01.c01;

public class MyThreadY implements Runnable {

	@Override
	public void run() {

		for (int i = 0; i < 100000000; i++) {
			System.out.print("y");
			Thread.yield();
		}
		System.out.println();

	}
}
