public class NewtonRaphson {
    public static double funcion(double x) {
        return Math.pow(x, 3) - x - 2;
    }

    public static double derivada(double x) {
        return 3 * Math.pow(x, 2) - 1;
    
    }
    