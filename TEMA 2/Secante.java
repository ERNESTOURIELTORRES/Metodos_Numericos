public class Secante {
    public static double funcion(double x) {
        return Math.pow(x, 3) - x - 2;
    }

    public static void main(String[] args) {
        double x0 = 1, x1 = 2, tolerancia = 0.0001;
        double x2;

           do {
            x2 = x1 - funcion(x1) * (x1 - x0) / (funcion(x1) - funcion(x0));
            x0 = x1;
            x1 = x2;
        } while (Math.abs(funcion(x2)) >= tolerancia);

        System.out.println("La raíz aproximada es: " + x2);
    }
}