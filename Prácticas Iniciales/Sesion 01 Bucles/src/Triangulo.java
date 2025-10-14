/**
 * 
 * @author Alberto Santos Martínez
 *
 */
public class Triangulo {

	public static void main(String[] args) {

		float lado1 = 3F;
		float lado2 = 3F;
		float lado3 = 3F;
		boolean resultado = esTriangulo(lado1, lado2, lado3);
		if(resultado == true) {
			String res = consultarTipoTriangulo(lado1, lado2, lado3);
			System.out.println("Es un triángulo de tipo " + res);
			
		}else {
			System.out.println("No es un triángulo");
		}
	}
	
	static boolean esTriangulo(float lado1, float lado2, float lado3) {
		float lado12 = lado1+lado2;
		float lado13 = lado1+lado3;
		float lado23 = lado2+lado3;
		
		if ((lado12 > lado3) && (lado13 > lado2) && (lado23 > lado1))
			return true;
		else {
			return false;
		}
	}

	static String consultarTipoTriangulo(float lado1, float lado2, float lado3) {
		String res;
		if((lado1 == lado2) && (lado2 == lado3)) {
			res = "equilatero";
		}else if((lado1 == lado2) && (lado1 != lado3) || (lado1 == lado3) && (lado1 != lado2) || (lado2 == lado3) && (lado1 !=lado3)){
			res = "isósceles";
		}else {
			res = "escaleno";
		}
		return res;
	}
}
