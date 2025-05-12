import java.util.Scanner;
import java.util.function.Function;

// Clase para introducir los datos
class EntradaDatosSimpson13 {
    private double a, b;
    private int n; // Número de intervalos (debe ser par)
    private Function<Double, Double> funcion;

    public void obtenerDatos() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("INTEGRACIÓN POR EL MÉTODO DE SIMPSON 1/3");
        System.out.println("Introduce la función a integrar (use 'x' como variable):");
        String funcionStr = scanner.nextLine();
        
        this.funcion = x -> evaluarExpresion(funcionStr.replace("x", String.valueOf(x)));
        
        System.out.println("Introduce el límite inferior de integración (a):");
        this.a = scanner.nextDouble();
        
        System.out.println("Introduce el límite superior de integración (b):");
        this.b = scanner.nextDouble();
        
        System.out.println("Introduce el número de intervalos (debe ser par):");
        this.n = scanner.nextInt();
        
        // Ajustar n para que sea par
        if (n % 2 != 0) {
            int nuevoN = n + 1;
            System.out.println("El número de intervalos debe ser par. Ajustando a " + nuevoN);
            this.n = nuevoN;
        }
    }
    
    private double evaluarExpresion(String expr) {
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

// Clase que implementa el método de Simpson 1/3
class ResolvedorSimpson13 {
    public double resolver(EntradaDatosSimpson13 datos) {
        double a = datos.getA();
        double b = datos.getB();
        int n = datos.getN();
        Function<Double, Double> f = datos.getFuncion();
        
        double h = (b - a) / n;
        double suma = f.apply(a) + f.apply(b);
        
        // Aplicar la regla de Simpson 1/3
        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            if (i % 2 == 0) {
                suma += 2 * f.apply(x);
            } else {
                suma += 4 * f.apply(x);
            }
        }
        
        return (h / 3) * suma;
    }// Clase para mostrar los resultados
class SalidaResultadosSimpson13 {
    public void mostrarResultado(double resultado) {
        System.out.println("\nEl resultado de la integral es: " + resultado);
    }
}

// Clase principal
public class IntegracionSimpson13 {
    public static void main(String[] args) {
        // Obtener datos
        EntradaDatosSimpson13 entrada = new EntradaDatosSimpson13();
        entrada.obtenerDatos();
        
        // Resolver la integral
        ResolvedorSimpson13 resolvedor = new ResolvedorSimpson13();
        double resultado = resolvedor.resolver(entrada);
        
        // Mostrar resultados
        SalidaResultadosSimpson13 salida = new SalidaResultadosSimpson13();
        salida.mostrarResultado(resultado);
    }
}

}

