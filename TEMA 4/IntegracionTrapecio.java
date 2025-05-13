import java.util.Scanner;
import java.util.function.Function;

// Clase para introducir los datos
class EntradaDatosTrapecio {
    private double a, b;
    private int n; // Número de intervalos
    private Function<Double, Double> funcion;

    public void obtenerDatos() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("INTEGRACIÓN POR EL MÉTODO DEL TRAPECIO");
        System.out.println("Introduce la función a integrar (use 'x' como variable):");
        String funcionStr = scanner.nextLine();
        
        this.funcion = x -> evaluarExpresion(funcionStr.replace("x", String.valueOf(x)));
        
        System.out.println("Introduce el límite inferior de integración (a):");
        this.a = scanner.nextDouble();
        
        System.out.println("Introduce el límite superior de integración (b):");
        this.b = scanner.nextDouble();
        
        System.out.println("Introduce el número de intervalos (n):");
        this.n = scanner.nextInt();
    }
    
    