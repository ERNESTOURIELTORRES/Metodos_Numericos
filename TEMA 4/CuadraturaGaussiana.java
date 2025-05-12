import java.util.Scanner;
import java.util.function.Function;

// Clase para introducir los datos
class EntradaDatosGauss {
    private double a, b;
    private int n; // Número de puntos de Gauss
    private Function<Double, Double> funcion;

    public void obtenerDatos() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Introduce la función a integrar (use 'x' como variable):");
        String funcionStr = scanner.nextLine();
        
        this.funcion = x -> evaluarExpresion(funcionStr.replace("x", String.valueOf(x)));
        
        System.out.println("Introduce el límite inferior de integración (a):");
        this.a = scanner.nextDouble();
        
        System.out.println("Introduce el límite superior de integración (b):");
        this.b = scanner.nextDouble();
        
        System.out.println("Introduce el número de puntos de Gauss (2, 3, 4 o 5):");
        this.n = scanner.nextInt();
        
        if (n < 2 || n > 5) {
            System.out.println("Número de puntos no soportado. Usando 5 puntos por defecto.");
            this.n = 5;
        }
    }
    
    private double evaluarExpresion(String expr) {
        // Implementación básica de evaluación de expresiones
        // Para producción, usar una librería como exp4j
        return new Object() {
            int pos = -1, ch;
            
            void nextChar() {
                ch = (++pos < expr.length()) ? expr.charAt(pos) : -1;
            }
            
            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }
            
            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < expr.length()) throw new RuntimeException("Carácter inesperado: " + (char)ch);
                return x;
            }
            
            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }
            
            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if (eat('*')) x *= parseFactor();
                    else if (eat('/')) x /= parseFactor();
                    else if (eat('^')) x = Math.pow(x, parseFactor());
                    else return x;
                }
            }
      