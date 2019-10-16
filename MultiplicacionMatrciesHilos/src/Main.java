import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Random rnd = new Random();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("De que tamaño desas la matriz?");
		int tam = sc.nextInt();
		
        // Dos matrices para multiplicar 
        double [][] m1 = new double[tam][tam];
        double [][] m2 = new double[tam][tam];
        
      //Llenado de matrices
      		for (int i = 0; i < m1.length; i++) {
      			for (int j = 0; j < m1.length; j++) {
      				m1[i][j]=rnd.nextInt(5);
      			}
      		}
      		for (int i = 0; i < m2.length; i++) {
      			for (int j = 0; j < m2.length; j++) {
      				m2[i][j]=rnd.nextInt(5);
      			}
      		}
      		
    		System.out.println("Matriz 1");
    		for (int i = 0; i < m1.length; i++) {
    			for (int j = 0; j < m1.length; j++) {
    				System.out.print(m1[i][j]+"-");
    			}
    			System.out.println();
    		}
    		System.out.println("Matriz 2");
    		for (int i = 0; i < m1.length; i++) {
    			for (int j = 0; j < m1.length; j++) {
    				System.out.print(m1[i][j]+"-");
    			}
    			System.out.println();
    		}
    		System.out.println("------------------------------------------------------");

        // Se multiplican
        double [][] resultado = new Main().multiplica(m1, m2);


        // Se saca por pantalla el resultado.
        for (int i=0;i<resultado.length; i++)
        {
            for (int j=0;j<resultado[0].length;j++)
                System.out.print(resultado[i][j]+" ");
            System.out.println(" ");
        }
        
	}
	
	public double[][] multiplica (double [][] m1, double [][] m2)
    {
        // condiciones que deben cumplirse y que se suponen ciertas
        // con los parámetros de entrada
        assert m1!=null;
        assert m2!=null;
        assert m1.length > 0;
        assert m1[0].length > 0;
        assert m2.length > 0;
        assert m2[0].length > 0;
        assert m1.length==m2[0].length;
        
        System.out.println("cantidad de Hilos: ");
        int cantidadHilos = new Scanner(System.in).nextInt();
        System.out.println("leyo: "+cantidadHilos);
        
        long inicio = System.currentTimeMillis();

        // Calculo de las dimensiones de la matriz resultado y
        // reserva de espacio para ella
        int filas = m1.length;
        int columnas = m2[0].length;
        double [][] resultado = new double[filas][columnas];
        cantidadHilos=filas/cantidadHilos;
        

        // Lista con todos los hilos lanzados.
        LinkedList<Thread> hilos = new LinkedList<Thread>();

        // Para cada elemento de la matriz resultado, se lanza el hilo
        // correspondiente.
        for (int fila=0; fila<filas; fila+=cantidadHilos)
            for (int columna=0; columna<columnas; columna+=cantidadHilos)
            {
                Thread hilo = new Thread(
                        new HiloMultiplicador(m1,m2,resultado,fila,columna));
                hilos.add(hilo);
                hilo.start();
            }

        // Se espera que terminen todos los hilos
        for (Thread hilo: hilos)
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        
        
        long fin = System.currentTimeMillis();
		double tiempo = (double) ((fin - inicio));
        
        System.out.println("Tardo: "+tiempo +" milisegundos");
        // se devuelve el resultado obtenido    
        return resultado;
    }

}

class HiloMultiplicador implements Runnable
{
    private double[][] m1;
    private double[][] m2;
    private double[][] resultado;
    private int fila;
    private int columna;

    /**
     * Guarda los parámetros que se le pasan 
     * @param m1 primer operando
     * @param m2 segundo operando
     * @param resultado matriz donde dejar el resultado
     * @param fila fila que debe calcular
     * @param columna columna que debe calcular
     */
    public HiloMultiplicador (double[][] m1, double[][]m2, double[][]resultado, int fila, int columna)
    {
        this.m1 = m1;
        this.m2 = m2;
        this.resultado = resultado;
        this.fila = fila;
        this.columna = columna;
    }

    /**
     * Calcula el elemento fila,columna de la matriz resultado
     */
    public void run()
    {
        
        double temp=0;
        for (int x = 0; x < m2.length; x++) {
			for (int i = 0; i < m1.length; i++) {
				for (int j = 0; j < m1.length; j++) {
					temp = temp+(m1[x][j]*m2[j][i]);
				}
				resultado[x][i]=temp;
				temp=0;
			}
			
		}
    }
}
