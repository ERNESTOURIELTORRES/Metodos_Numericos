public class PuntoFijo {
    public static double g(double x) {
        return Math.cbrt(x + 2); // despeje de x^3 - x - 2 = 0
    }

    public static void main(String[] args) {
        double x = 1.5, tolerancia = 0.0001;
        double x1;

        
         do {
            x1 = g(x);
            if (Math.abs(x1 - x) < tolerancia)
                break;
            x = x1;
        } while (true);

        System.out.println("La raíz aproximada es: " + x1);
    }
}