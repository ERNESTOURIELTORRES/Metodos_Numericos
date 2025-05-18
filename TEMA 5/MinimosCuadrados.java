import java.util.Scanner;
import java.util.ArrayList;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MinimosCuadrados {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#.####"); // 4 cifras decimales
        df.setRoundingMode(RoundingMode.HALF_UP);

        System.out.println("Metodo de minimos cuadrados - Ajuste lineal");

        // Leer la cantidad de pares
        System.out.print("Cuantos pares de datos vas a ingresar? ");
        int n = scanner.nextInt();

        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();

        // Ingreso de datos
        for (int i = 0; i < n; i++) {
            System.out.print("Ingrese x[" + (i + 1) + "]: ");
            double xi = scanner.nextDouble();

            System.out.print("Ingrese y[" + (i + 1) + "]: ");
            double yi = scanner.nextDouble();

            x.add(xi);
            y.add(yi);
        }

        // Cálculo de sumatorias
        double sum_x = 0, sum_y = 0, sum_x2 = 0, sum_xy = 0;

        for (int i = 0; i < n; i++) {
            double xi = x.get(i);
            double yi = y.get(i);
            sum_x += xi;
            sum_y += yi;
            sum_x2 += xi * xi;
            sum_xy += xi * yi;
        }

        // Cálculo de b y a
        double b = (n * sum_xy - sum_x * sum_y) / (n * sum_x2 - sum_x * sum_x);
        double a = (sum_y - b * sum_x) / n;

        // Redondear a 4 cifras decimales
        String a_str = df.format(a);
        String b_str = df.format(b);

        // Mostrar resultado
        System.out.println("\nResultado:");
        System.out.println("La recta de regresion es: y = " + a_str + " + " + b_str + "x");
        scanner.close();
    }
}
