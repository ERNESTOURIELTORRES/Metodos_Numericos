import java.util.Scanner;
import java.util.function.Function;

public class IntegracionSimpson38 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("INTEGRACIÓN NUMÉRICA (MÉTODO DE SIMPSON 3/8)");
        System.out.println("---------------------------------------------");
        
        // Entrada de la función
        System.out.println("Introduzca la función a integrar (use 'x' como variable, ej: x^3 + 2*x + 1):");
        String funcionStr = scanner.nextLine();
        Function<Double, Double> f = x -> evaluarFuncion(funcionStr.replace("x", String.valueOf(x)));
        
        // Entrada de límites
        System.out.println("Introduzca el límite inferior de integración (a):");
        double a = scanner.nextDouble();
        
        System.out.println("Introduzca el límite superior de integración (b):");
        double b = scanner.nextDouble();
        
        // Entrada de intervalos
        System.out.println("Introduzca el número de intervalos (debe ser múltiplo de 3):");
        int n = scanner.nextInt();
        
        // Ajustar n si no es múltiplo de 3
        if (n % 3 != 0) {
            n = n + (3 - n % 3);
            System.out.println("Ajustando a " + n + " intervalos (múltiplo de 3)");
        }
        
        // Cálculo de la integral
        double h = (b - a) / n;
        double suma = f.apply(a) + f.apply(b);
        
        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            suma += (i % 3 == 0) ? 2 * f.apply(x) : 3 * f.apply(x);
        }
        
        double integral = (3 * h / 8) * suma;
        
        // Resultado
        System.out.println("\nRESULTADO:");
        System.out.printf("∫f(x)dx desde %.4f hasta %.4f ≈ %.8f%n", a, b, integral);
        System.out.println("Número de intervalos utilizados: " + n);
        System.out.println("Fórmula utilizada: (3h/8) [f(a) + 3Σf(x_impar) + 2Σf(x_mult3) + f(b)]");
    }
    
  