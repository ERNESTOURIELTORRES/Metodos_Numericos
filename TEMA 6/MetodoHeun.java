public class MetodoHeun {
   
    // Función de calculo de la pendiente inicial
    public static double pendienteInicial(double x, double y) {
        return x + y; // Ejemplo: puedes cambiar esta función según tu problema
    }

    // Método de Heun simple sin corrector
    public static double[] heun(double x, double y, double h) {
        double k1 = pendienteInicial(x, y); //Pendiente inicial 
        double yPredic = y + k1 * h; //Valor de Euler, predictor
        double k2 = pendienteInicial(x + h, yPredic); //Pendiente del punto predictor
        double pendientePromedio = (k1 + k2) / 2; //Pendiente promedio
        double yNuevo = y + pendientePromedio * h; //Valor nuevo para y
        x = x + h;
        return new double[] { x, yNuevo };
    }

    
}
