import java.util.Scanner;

class Matrix {
    double[][] A;
    double[] b;

    Matrix(double[][] A, double[] b) {
        this.A = A;
        this.b = b;
    }
}

class InputHandler {
    public Matrix getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el tamaño del sistema: ");
        int n = scanner.nextInt();

        double[][] A = new double[n][n];
        double[] b = new double[n];

        System.out.println("Ingrese la matriz A:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("Ingrese el objeto en la posición (%d, %d): ", i + 1, j + 1);
                A[i][j] = scanner.nextDouble();
            }
        }

        System.out.println("Ingrese el vector b:");
        for (int i = 0; i < n; i++) {
            System.out.printf("Ingrese el objeto en la posición (%d): ", i + 1);
            b[i] = scanner.nextDouble();
        }
        return new Matrix(A, b);
    }
}

class Solver {
    public double[] gaussSeidel(Matrix matrix, double tol, int maxIter) {
        int n = matrix.A.length;
        double[] x = new double[n];  // Solución inicial (0, 0, ..., 0)
        double[] xOld = new double[n];
        
        for (int iter = 0; iter < maxIter; iter++) {
            System.arraycopy(x, 0, xOld, 0, n); // Copia del vector anterior

            for (int i = 0; i < n; i++) {
                double sum = 0.0;
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        sum += matrix.A[i][j] * x[j];
                    }
                }
                x[i] = (matrix.b[i] - sum) / matrix.A[i][i];
            }

            // Verificación de la tolerancia
            double maxDiff = 0.0;
            for (int i = 0; i < n; i++) {
                maxDiff = Math.max(maxDiff, Math.abs(x[i] - xOld[i]));
            }
            if (maxDiff < tol) {
                System.out.println("Convergencia alcanzada en " + (iter + 1) + " iteraciones.");
                break;
            }
        }
        return x;
    }
}

class Printer {
    public void printSolution(double[] solution) {
        System.out.println("Solución:");
        for (double v : solution) {
            System.out.printf("%.6f ", v);
        }
        System.out.println();
    }
}

public class GaussSeidel {
    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        Matrix matrix = inputHandler.getUserInput();
        
        double tol = 1e-6;
        int maxIter = 100;

        Solver solver = new Solver();
        Printer printer = new Printer();

        double[] solution = solver.gaussSeidel(matrix, tol, maxIter);
        printer.printSolution(solution);
    }
}
