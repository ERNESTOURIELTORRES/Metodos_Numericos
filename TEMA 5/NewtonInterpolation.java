public class NewtonInterpolation {

    // Método para calcular las diferencias divididas
    public static double[][] calcularDiferenciasDivididas(double[] x, double[] y) {
        int n = x.length;
        double[][] dd = new double[n][n];

        // Inicializar primera columna con y
        for (int i = 0; i < n; i++) {
            dd[i][0] = y[i];
        }

        // Calcular las diferencias divididas
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < n - j; i++) {
                dd[i][j] = (dd[i + 1][j - 1] - dd[i][j - 1]) / (x[i + j] - x[i]);
            }
        }

        return dd;
    }

    // Evaluar el polinomio interpolado en un punto xEval
    public static double evaluarPolinomio(double[][] dd, double[] x, double xEval) {
        int n = x.length;
        double resultado = dd[0][0];
        double producto = 1.0;

        for (int i = 1; i < n; i++) {
            producto *= (xEval - x[i - 1]);
            resultado += dd[0][i] * producto;
        }

        return resultado;
    }

    // Método principal de prueba
    public static void main(String[] args) {
        double[] x = {9, 12, 15}; // Ejemplo de valores de x
        double[] y = {15, 21, 18}; // Ejemplo de valores de y

        double[][] dd = calcularDiferenciasDivididas(x, y);
        double xEval = 13.5;
        double resultado = evaluarPolinomio(dd, x, xEval);

        System.out.printf("Valor interpolado en x = %.2f es: %.2f\n", xEval, resultado);
    }
}

