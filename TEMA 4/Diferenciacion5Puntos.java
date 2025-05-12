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
    
    // Método evaluarFuncion() idéntico al de la clase Diferenciacion3Puntos
    private static double evaluarFuncion(String expr) {
        // Implementación básica de evaluación de expresiones
        try {
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
                
                double parseFactor() {
                    if (eat('+')) return parseFactor();
                    if (eat('-')) return -parseFactor();
                    
                    double x;
                    int startPos = this.pos;
                    if (eat('(')) {
                        x = parseExpression();
                        eat(')');
                    } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                        while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                        x = Double.parseDouble(expr.substring(startPos, this.pos));
                    } else if (ch >= 'a' && ch <= 'z') {
                        while (ch >= 'a' && ch <= 'z') nextChar();
                        String func = expr.substring(startPos, this.pos);
                        x = parseFactor();
                        switch (func) {
                            case "sqrt": x = Math.sqrt(x); break;
                            case "sin": x = Math.sin(x); break;
                            case "cos": x = Math.cos(x); break;
                            case "tan": x = Math.tan(x); break;
                            case "log": x = Math.log(x); break;
                            case "exp": x = Math.exp(x); break;
                            default: throw new RuntimeException("Función desconocida: " + func);
                        }
                    } else {
                        throw new RuntimeException("Carácter inesperado: " + (char)ch);
                    }
                    return x;
                }
            }.parse();
        } catch (Exception e) {
            throw new RuntimeException("Error al evaluar la función: " + e.getMessage());
        }
    }
}// ESTOS CODIGOS FUERON ECHOS EN EQUIPO 