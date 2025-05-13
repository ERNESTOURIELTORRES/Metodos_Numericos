
public class Biseccion {
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
         while ((b - a) >= tolerancia) {
            c = (a + b) / 2;
            if (funcion(c) == 0.0)
                break;
            else if (funcion(c) * funcion(a) < 0)
                b = c;
            else
                a = c;
        }

        System.out.println("La raíz aproximada es: " + (a + b) / 2);
    }
}
