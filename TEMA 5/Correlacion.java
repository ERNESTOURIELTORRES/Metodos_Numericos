import java.util.Scanner; 

public class Correlacion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 

        int n; 
        double xi, yi;
        double sumaX = 0, sumaY = 0, sumaXY = 0, sumaX2 = 0, sumaY2 = 0;
        double coefPendiente, coefIntercepto, r;

        System.out.print("Ingrese el número de datos: ");
        n = scanner.nextInt();

      
        double[] datosX = new double[n];
        double[] datosY = new double[n];

       
        for (int i = 0; i < n; i++) {
            System.out.println("Dato " + (i + 1));
            System.out.print("Ingrese X[" + (i + 1) + "]: ");
            xi = scanner.nextDouble();
            System.out.print("Ingrese Y[" + (i + 1) + "]: ");
            yi = scanner.nextDouble();

       
            datosX[i] = xi;
            datosY[i] = yi;
        
            sumaX += xi;
            sumaY += yi;
            sumaXY += xi * yi;
            sumaX2 += xi * xi;
            sumaY2 += yi * yi;
        }

       
        coefPendiente = (n * sumaXY - sumaX * sumaY) / (n * sumaX2 - sumaX * sumaX);
       
        coefIntercepto = (sumaY - coefPendiente * sumaX) / n;
        r = (n * sumaXY - sumaX * sumaY) /
            Math.sqrt((n * sumaX2 - sumaX * sumaX) * (n * sumaY2 - sumaY * sumaY));

     
        System.out.println("-----------------------------------------");
        System.out.printf("Ecuación de regresión: Y = %.4f + %.4f * X%n", coefIntercepto, coefPendiente);
        System.out.printf("Coeficiente de correlación r = %.4f%n", r);


        System.out.println("Interpretación:");
        if (r > 0) {
            System.out.println("Relación positiva.");
        } else if (r < 0) {
            System.out.println("Relación negativa."); 
        } else {
            System.out.println("La correlación es nula.");
        }
        scanner.close(); 
    }
}


 