import java.util.Scanner;

public class gaussjordan {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n;
        System.out.print("¿Cuántas ecuaciones tiene el sistema?: ");
        n = sc.nextInt();

        double[][] matriz = new double[n][n + 1];

        // Leer la matriz aumentada
        System.out.println("Ingresa los coeficientes de la matriz aumentada:");
        for (int i = 0; i < n; i++) {
            System.out.println("Ecuación " + (i + 1) + ":");
            for (int j = 0; j <= n; j++) {
                System.out.print("Elemento [" + (i + 1) + "][" + (j + 1) + "]: ");
                matriz[i][j] = sc.nextDouble();
            }
        }

        // Aplicar método de Gauss-Jordan
        for (int i = 0; i < n; i++) {
            // Hacer el pivote igual a 1
            double pivote = matriz[i][i];
            for (int j = 0; j <= n; j++) {
                matriz[i][j] = matriz[i][j] / pivote;
            }

            // Hacer ceros en la columna actual
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = matriz[k][i];
                    for (int j = 0; j <= n; j++) {
                        matriz[k][j] = matriz[k][j] - factor * matriz[i][j];
                    }
                }
            }
        }

        // Mostrar resultados
        System.out.println("\nSoluciones del sistema:");
        for (int i = 0; i < n; i++) {
            System.out.println("x" + (i + 1) + " = " + matriz[i][n]);
        }

        sc.close();
    }
}
