/**
 *
 * @author Alberto Santos Martínez
 * 
 */
 
public class Fibonacci{
	
	
	public static void main(String[] args) {
		
		System.out.printf("La cadena de Fibonacci es:");
		fibonacci(20);
	}
	
	static long fibonacci (long val){
		
		long n1 = 0, n2 = 1;
		
		if(val == 0) 
			return 0;
		else {
			long resultado;

			resultado = n1 + n2;
			n1 = n2;
			n2 = resultado;
			System.out.printf(" " + resultado);
				
			return fibonacci(val - 1);	
		}
		
	}
	
	
	
	/*
	public static void main(String[] args) {
		
		int val = 7; //numero de la serie de fibonacci, cuando el valor es igual a 4 calcula el valor del valor 3
		long fibo = fibonacci(val);
		if (val == 0)
			System.out.println("0");
		else
			System.out.println("" + fibo);
	}
	// 1 1 2 3 5 8 13 21 34 55 89 144
	static long fibonacci(long val) {
		
		long fibo1 = 1, fibo2 = 1; // primer y segundo numero de la serie
		int i;
		
		System.out.println("El valor numero " + val + " de la serie Fibonacci es: ");
		//val--;
		
		for(i=2;i<val;i++){
            fibo2 = fibo1 + fibo2;
            fibo1 = fibo2 - fibo1;
        }
		if(val%3 == 0)
			return fibo1;
		else
			return fibo2;
	}
	*/
}