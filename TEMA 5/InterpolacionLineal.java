class InterpolacionLineal {
    public static double interpolar(double x0, double y0, double x1, double y1, double x) {
        double m = (y1 - y0) / (x1 - x0);
        return y0 + m * (x - x0);
    }
}
public class Main {
    public static void main(String[] args) {
        double resultado = InterpolacionLineal.interpolar(2, 4, 6, 10, 3);
        System.out.println("Valor interpolado: " + resultado);
    }
}


