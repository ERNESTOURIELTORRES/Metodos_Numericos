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

       
    }
}
