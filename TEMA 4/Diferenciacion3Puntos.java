import java.util.Scanner;
import java.util.function.Function;

public class Diferenciacion3Puntos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("DIFERENCIACIÓN NUMÉRICA (MÉTODO DE 3 PUNTOS)");
        System.out.println("---------------------------------------------");
        
        // Entrada de la función
        System.out.println("Introduzca la función (use 'x' como variable, ej: x^2 + sin(x)):");
        String funcionStr = scanner.nextLine();
        Function<Double, Double> f = x -> evaluarFuncion(funcionStr.replace("x", String.valueOf(x)));
        
        // Entrada del punto de evaluación
        System.out.println("Introduzca el punto donde calcular la derivada (x0):");
        double x0 = scanner.nextDouble();
        
        // Entrada del tamaño de paso
        System.out.println("Introduzca el tamaño del paso (h):");
        double h = scanner.nextDouble();
        
        // Cálculo de la derivada usando fórmula de 3 puntos centrada
        double derivada = (f.apply(x0 + h) - f.apply(x0 - h)) / (2 * h);
        
        // Resultado
        System.out.println("\nRESULTADO:");
        System.out.printf("f'(%.4f) ≈ %.8f%n", x0, derivada);
        System.out.println("Fórmula utilizada: [f(x+h) - f(x-h)] / (2h)");
    }
    
  