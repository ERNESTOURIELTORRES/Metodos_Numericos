public class Secante {
    public static double funcion(double x) {
        return Math.pow(x, 3) - x - 2;
    }

    public static void main(String[] args) {
        double x0 = 1, x1 = 2, tolerancia = 0.0001;
        double x2;
