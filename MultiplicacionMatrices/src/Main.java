import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Random rnd = new Random();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("De que tamaño desas la matriz?");
		int tam = sc.nextInt();
		long inicio = System.currentTimeMillis();
		
		
		int matrizA [][] = new int[tam][tam];
		int matrizB [][] = new int[tam][tam];
		int matrizRes [][] = new int[tam][tam];
		
		//Llenado de matrices
		for (int i = 0; i < matrizA.length; i++) {
			for (int j = 0; j < matrizA.length; j++) {
				matrizA[i][j]=rnd.nextInt(5);
			}
		}
		for (int i = 0; i < matrizB.length; i++) {
			for (int j = 0; j < matrizB.length; j++) {
				matrizB[i][j]=rnd.nextInt(5);
			}
		}
		
		//Multiplicacion
		int temp=0;
		int cont=0;
		for (int x = 0; x < matrizB.length; x++) {
			for (int i = 0; i < matrizA.length; i++) {
				for (int j = 0; j < matrizA.length; j++) {
					temp = temp+(matrizA[x][j]*matrizB[j][i]);
				}
				matrizRes[x][i]=temp;
				temp=0;
			}
			
		}
		
		
		//Impresion de matrices
		System.out.println("Matriz A");
		for (int i = 0; i < matrizA.length; i++) {
			for (int j = 0; j < matrizA.length; j++) {
				System.out.print(matrizA[i][j]+"-");
			}
			System.out.println();
		}
		System.out.println("Matriz B");
		for (int i = 0; i < matrizB.length; i++) {
			for (int j = 0; j < matrizB.length; j++) {
				System.out.print(matrizB[i][j]+"-");
			}
			System.out.println();
		}
		System.out.println("Matriz Res");
		for (int i = 0; i < matrizRes.length; i++) {
			for (int j = 0; j < matrizRes.length; j++) {
				System.out.print(matrizRes[i][j]+"-");
			}
			System.out.println();
		}
		
		long fin = System.currentTimeMillis();
		double tiempo = (double) ((fin - inicio));
        
        System.out.println("Tardo: "+tiempo +" milisegundos");
		
	}
	

}
