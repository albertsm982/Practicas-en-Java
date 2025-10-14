package p01.c03;

public class MyThreadX implements Runnable{
	
	private final char character;
	private final int sleep_time;
	private final int char_times;
	
	public MyThreadX(char character, int sleep_time, int char_times){
		this.character = character;
		this.sleep_time = sleep_time;
		this.char_times = char_times;
	}
	
	@Override
	public void run() {
		
		
		for( int i = 0; i < char_times; i++) {
			
			for( int j = 0; j < 100000; j++) {
			System.out.print(character);
			}
			
			try {
				Thread.sleep(sleep_time);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println();
	}
	
}
