import java.util.ArrayList;
import java.util.List;

public class AdamsBashforthConRK4 {

    // Definición de la función f(t, y) = y
    public static double f(double t, double y) {
        return y;
    }

    public static void main(String[] args) {
        double t0 = 0.0;      // Valor inicial de t
        double y0 = 1.0;      // Valor inicial de y
        double h = 0.2;       // Tamaño de paso
        int n = 20;           // Número de pasos a calcular

        // Listas para almacenar los valores de t y y
        List<Double> tVals = new ArrayList<>();
        List<Double> yVals = new ArrayList<>();

        // Inicializar con condiciones iniciales
        tVals.add(t0);
        yVals.add(y0);

        // Usar Runge-Kutta de orden 4 para obtener los primeros 3 puntos
        for (int i = 0; i < 3; i++) {
            double t = tVals.get(tVals.size() - 1);
            double y = yVals.get(yVals.size() - 1);

            double k1 = f(t, y);
            double k2 = f(t + h / 2.0, y + h * k1 / 2.0);
            double k3 = f(t + h / 2.0, y + h * k2 / 2.0);
            double k4 = f(t + h, y + h * k3);

            double yNext = y + (h / 6.0) * (k1 + 2 * k2 + 2 * k3 + k4);
            double tNext = t + h;

            tVals.add(tNext);
            yVals.add(yNext);
        }

        // Aplicar Adams-Bashforth de 4 pasos
        for (int i = 3; i < n; i++) {
            double tNext = tVals.get(i) + h;

            double yNext = yVals.get(i) + (h / 24.0) * (
                55 * f(tVals.get(i), yVals.get(i)) -
                59 * f(tVals.get(i - 1), yVals.get(i - 1)) +
                37 * f(tVals.get(i - 2), yVals.get(i - 2)) -
                 9 * f(tVals.get(i - 3), yVals.get(i - 3))
            );

            tVals.add(tNext);
            yVals.add(yNext);
        }

        // Mostrar resultados numéricos
        System.out.println("Resultados del método Adams-Bashforth de 4 pasos para dy/dt = y, y(0) = 1:");
        for (int i = 0; i < tVals.size(); i++) {
            System.out.printf("t = %.2f, y = %.6f\n", tVals.get(i), yVals.get(i));
        }

        // Mostrar comparación con la solución exacta: y = e^t
        System.out.println("\nComparación con la solución exacta y(t) = e^t:");
        for (int i = 0; i < tVals.size(); i++) {
            double t = tVals.get(i);
            double yAprox = yVals.get(i);
            double yExacta = Math.exp(t);
            System.out.printf("t = %.2f, y_aprox = %.6f, y_exacta = %.6f\n", t, yAprox, yExacta);
        }
    }
}