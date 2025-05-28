import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.*;

public class EulerODESolver {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Ingresar tamaño del sistema
        System.out.print("Ingrese el número de ecuaciones del sistema: ");
        int n = Integer.parseInt(scanner.nextLine());

        // 2. Nombres de variables
        System.out.println("\nIngrese los nombres de las variables (ejemplo: x y z):");
        String[] varNames = scanner.nextLine().trim().split("\\s+");

        if (varNames.length != n) {
            System.out.println("Error: debe ingresar exactamente " + n + " nombres.");
            return;
        }

        // 3. Ecuaciones
        Expression[] funciones = new Expression[n];
        System.out.println("\nIngrese las ecuaciones (use t y las variables indicadas):");
        for (int i = 0; i < n; i++) {
            System.out.print("d" + varNames[i] + "/dt = ");
            String ecuacion = scanner.nextLine();

            // Construir expresión
            List<String> variables = new ArrayList<>();
            variables.add("t");
            variables.addAll(Arrays.asList(varNames));

            funciones[i] = new ExpressionBuilder(ecuacion)
                .variables(new HashSet<>(variables)) // ← CORREGIDO AQUÍ
                .build();

        }

        // 4. Condiciones iniciales
        double[] condicionesIniciales = new double[n];
        System.out.println("\nIngrese las condiciones iniciales:");
        for (int i = 0; i < n; i++) {
            System.out.print(varNames[i] + "(0) = ");
            condicionesIniciales[i] = Double.parseDouble(scanner.nextLine());
        }

        // 5. Parámetros de simulación
        System.out.print("\nIngrese el paso de tiempo (h): ");
        double h = Double.parseDouble(scanner.nextLine());

        System.out.print("Ingrese el tiempo final de simulación: ");
        double tFinal = Double.parseDouble(scanner.nextLine());

        int pasos = (int) Math.round(tFinal / h) + 1;
        double[][] solucion = new double[n][pasos];
        double[] tiempo = new double[pasos];

        
    }
}
