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

// Clase que implementa la cuadratura gaussiana
class ResolvedorGauss {
    private double[][] puntosPesos = {
        // n=2
        {-0.5773502691896257, 1.0, 0.5773502691896257, 1.0},
        // n=3
        {-0.7745966692414834, 0.5555555555555556, 0.0, 0.8888888888888888, 0.7745966692414834, 0.5555555555555556},
        // n=4
        {-0.8611363115940526, 0.3478548451374538, -0.3399810435848563, 0.6521451548625461,
         0.3399810435848563, 0.6521451548625461, 0.8611363115940526, 0.3478548451374538},
        // n=5
        {-0.9061798459386640, 0.2369268850561891, -0.5384693101056831, 0.4786286704993665, 0.0, 0.5688888888888889,
         0.5384693101056831, 0.4786286704993665, 0.9061798459386640, 0.2369268850561891}
    };

    public double resolver(EntradaDatosGauss datos) {
        double a = datos.getA();
        double b = datos.getB();
        int n = datos.getN();
        Function<Double, Double> f = datos.getFuncion();
        
        // Mapear de [a,b] a [-1,1]
        double suma = 0.0;
        int index = n - 2;
        
        for (int i = 0; i < n; i++) {
            double xi = puntosPesos[index][2*i];
            double wi = puntosPesos[index][2*i+1];
            
            // Cambio de variable
            double x = ((b - a) * xi + (a + b)) / 2;
            suma += wi * f.apply(x);
        }
        
        return (b - a) / 2 * suma;
    }
}

// Clase para mostrar los resultados
class SalidaResultadosGauss {
    public void mostrarResultado(double resultado) {
        System.out.println("\nEl resultado de la integral es: " + resultado);
    }
}

// Clase principal
public class CuadraturaGaussiana {
    public static void main(String[] args) {
        System.out.println("INTEGRACIÓN POR CUADRATURA GAUSSIANA");
        
        // Obtener datos
        EntradaDatosGauss entrada = new EntradaDatosGauss();
        entrada.obtenerDatos();
        
        // Resolver la integral
        ResolvedorGauss resolvedor = new ResolvedorGauss();
        double resultado = resolvedor.resolver(entrada);
        
        // Mostrar resultados
        SalidaResultadosGauss salida = new SalidaResultadosGauss();
        salida.mostrarResultado(resultado);
    }
}