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
    }private double evaluarExpresion(String expr) {
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

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public int getN() {
        return n;
    }

    public Function<Double, Double> getFuncion() {
        return funcion;
    }
}

    
    