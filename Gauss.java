import java.util.Scanner;

public class Gauss {  


    public static void llenarMatriz(double[][] matriz, int n) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese los elementos de la matriz aumentada:");
        for (int i = 0; i < n; i++) {
            System.out.println("** Fila " + (i + 1) + " **");
            for (int j = 0; j <= n; j++) {
                if (j < n) {
                    System.out.print("Coeficiente de x" + (j + 1) + ": ");
                } else {
                    System.out.print("Término independiente: ");
                }
                matriz[i][j] = scanner.nextDouble();
            }
        }
    }


    public static void imprimirMatriz(double[][] matriz, int n) {
        System.out.println("Matriz aumentada:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.printf("%10.4f ", matriz[i][j]);
            }
            System.out.println();
        }
    }


    public static void resolverSistema(double[][] matriz, int n) {
        for (int i = 0; i < n; i++) {
        
            int max = i;
            for (int k = i + 1; k < n; k++) {
                if (Math.abs(matriz[k][i]) > Math.abs(matriz[max][i])) {
                    max = k;
                }
            }
         
            double[] temp = matriz[i];
            matriz[i] = matriz[max];
            matriz[max] = temp;

           
            if (matriz[i][i] == 0) {
                System.out.println("El sistema no tiene solución única.");
                return;
            }

           
            for (int k = i + 1; k < n; k++) {
                double factor = matriz[k][i] / matriz[i][i];
                for (int j = i; j <= n; j++) {
                    matriz[k][j] -= factor * matriz[i][j];
                }
            }
        }

    
        double[] solucion = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            solucion[i] = matriz[i][n];
            for (int j = i + 1; j < n; j++) {
                solucion[i] -= matriz[i][j] * solucion[j];
            }
            solucion[i] /= matriz[i][i];
        }

       
        System.out.println("Solución del sistema:");
        for (int i = 0; i < n; i++) {
            System.out.printf("x%d = %.4f\n", i + 1, solucion[i]);
        }
    }

    //IMPRIMIR ESTA COSA 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        System.out.print("Ingrese el número de ecuaciones: ");
        int n = scanner.nextInt();

        // Crear la matriz aumentada
        double[][] matriz = new double[n][n + 1];

        // Llenar la matriz por teclado
        llenarMatriz(matriz, n);

        // Imprimir la matriz
        imprimirMatriz(matriz, n);

        // Resolver el sistema
        resolverSistema(matriz, n);
    }
}