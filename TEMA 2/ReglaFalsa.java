public class ReglaFalsa {
    public static double funcion(double x) {
        return Math.pow(x, 3) - x - 2;
    }

    public static void main(String[] args) {
        double a = 1, b = 2, tolerancia = 0.0001;
        double c;

        if (funcion(a) * funcion(b) >= 0) {
            System.out.println("No se puede aplicar el método.");
            return;
        }