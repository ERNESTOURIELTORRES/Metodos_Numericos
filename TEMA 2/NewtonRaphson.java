public class NewtonRaphson {
    public static double funcion(double x) {
        return Math.pow(x, 3) - x - 2;
    }

    public static double derivada(double x) {
        return 3 * Math.pow(x, 2) - 1;
    
    }
    public static void main(String[] args) {
        double x = 1.5, tolerancia = 0.0001;
        double h;

        do {
            h = funcion(x) / derivada(x);
            x = x - h;
        } while (Math.abs(h) >= tolerancia);

        System.out.println("La raíz aproximada es: " + x);
    }
}