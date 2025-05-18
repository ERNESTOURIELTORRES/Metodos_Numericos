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

   
}

