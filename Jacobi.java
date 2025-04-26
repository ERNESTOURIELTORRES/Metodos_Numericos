import java.util.Scanner;

public class Jacobi {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Ingresar el tamaño del sistema
        System.out.print("Ingrese el número de ecuaciones (n): ");
        int n = sc.nextInt();
        
        double[][] A = new double[n][n];
        double[] b = new double[n];
        double[] x = new double[n];
        double[] xNew = new double[n];

        // Ingresar la matriz A
        System.out.println("\nIngrese la matriz A (coeficientes):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("A[" + (i + 1) + "][" + (j + 1) + "]: ");
                A[i][j] = sc.nextDouble();
            }
        }

        // Ingresar el vector b
        System.out.println("\nIngrese el vector b (términos independientes):");
        for (int i = 0; i < n; i++) {
            System.out.print("b[" + (i + 1) + "]: ");
            b[i] = sc.nextDouble();
        }

        // Ingresar el vector inicial x0
        System.out.println("\nIngrese el vector inicial:");
        for (int i = 0; i < n; i++) {
            System.out.print("x0[" + (i + 1) + "]: ");
            x[i] = sc.nextDouble();
        }

        // Ingresar la tolerancia y el máximo de iteraciones
        System.out.print("\nIngrese la tolerancia: ");
        double tol = sc.nextDouble();
        
        System.out.print("Ingrese el número máximo de iteraciones: ");
        int maxIter = sc.nextInt();
        
        sc.close();

        // Aplicar el método de Jacobi
        boolean convergencia = false;
        int iteraciones = 0;

        while (iteraciones < maxIter) {
            iteraciones++;

            // Calcular la nueva aproximación
            for (int i = 0; i < n; i++) {
                double suma = 0;
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        suma += A[i][j] * x[j];
                    }
                }
                xNew[i] = (b[i] - suma) / A[i][i];
            }

            // Verificar la convergencia
            boolean esConvergente = true;
            for (int i = 0; i < n; i++) {
                if (Math.abs(xNew[i] - x[i]) > tol) {
                    esConvergente = false;
                    break;
                }
            }

            // Copiar xNew en x para la próxima iteración
            System.arraycopy(xNew, 0, x, 0, n);

            if (esConvergente) {
                convergencia = true;
                System.out.println("\nConvergencia alcanzada en " + iteraciones + " iteraciones.");
                break;
            }
        }

        if (!convergencia) {
            System.out.println("\nNo se alcanzó la convergencia en el número máximo de iteraciones.");
        }

        // Mostrar la solución aproximada
        System.out.println("\nSolución aproximada:");
        for (int i = 0; i < n; i++) {
            System.out.printf("x[%d] = %.6f\n", i + 1, x[i]);
        }
    }
}
