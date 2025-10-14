package p01.c02;

public class MyThreadX implements Runnable{

	@Override
	public void run() {
		
		for( int i = 0; i < 1000; i++) {
			
			for( int j = 0; j < 100000; j++) {
				System.out.print("xaa");
				}
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println();
	}

	
}
