import java.util.Scanner;
import java.util.function.Function;

public class Diferenciacion5Puntos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("DIFERENCIACIÓN NUMÉRICA (MÉTODO DE 5 PUNTOS)");
        System.out.println("---------------------------------------------");
        
        // Entrada de la función
        System.out.println("Introduzca la función (use 'x' como variable, ej: exp(x)*cos(x)):");
        String funcionStr = scanner.nextLine();
        Function<Double, Double> f = x -> evaluarFuncion(funcionStr.replace("x", String.valueOf(x)));
        
        // Entrada del punto de evaluación
        System.out.println("Introduzca el punto donde calcular la derivada (x0):");
        double x0 = scanner.nextDouble();
        
        // Entrada del tamaño de paso
        System.out.println("Introduzca el tamaño del paso (h):");
        double h = scanner.nextDouble();
        
        // Cálculo de la derivada usando fórmula de 5 puntos centrada
        double derivada = (-f.apply(x0 + 2*h) + 8*f.apply(x0 + h) - 8*f.apply(x0 - h) + f.apply(x0 - 2*h)) / (12 * h);
        
        // Resultado
        System.out.println("\nRESULTADO:");
        System.out.printf("f'(%.4f) ≈ %.12f%n", x0, derivada);
        System.out.println("Fórmula utilizada: [-f(x+2h) + 8f(x+h) - 8f(x-h) + f(x-2h)] / (12h)");
    }
    
  