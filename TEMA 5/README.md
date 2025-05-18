# METODOS NUMERICOS 

En este readme lo veremos en 2 partes la interpolarización y Métodos de regresión: 
- Interpolación Lineal
- Interpolación Newton
- Método de regresión Correlación
- Método de regresión Minimo Cuadrados


# Interpolación Lineal

La **interpolación lineal** es un método para estimar valores intermedios de una función conocida en dos puntos. Dado un par de puntos \((x_0, y_0)\) y \((x_1, y_1)\), se asume que la función entre ellos es una línea recta, y se calcula el valor aproximado \(y\) para cualquier \(x\) en el intervalo \([x_0, x_1]\).


---

##  ¿En qué consiste?

Consiste en trazar la recta que une los dos puntos conocidos y luego usar la ecuación de esa recta para calcular el valor de \(y\) correspondiente a un \(x\) dado. La pendiente \(m\) de la recta viene dada por:
![interpolation_formula](https://github.com/user-attachments/assets/ba90de8d-df43-4622-a971-4d9cb75efe76)
![slope_formula](https://github.com/user-attachments/assets/c19bb582-5fb9-4109-a963-6b1d95d255a8)


---

## Pasos del método

1. Obtener dos puntos conocidos \((x_0, y_0)\) y \((x_1, y_1)\).  
2. Calcular la pendiente:
   \[
   m = \frac{y_1 - y_0}{x_1 - x_0}
   \]
3. Usar la fórmula de la recta para hallar \(y\) en el punto \(x\):
   \[
   y = y_0 + m \, (x - x_0)
   \]
4. Devolver el valor interpolado \(y\).


## Pseudocódigo del Método de Gauss

```plaintext
## ⚙️ Pseudocódigo

```plaintext
Función Interpolar(x0, y0, x1, y1, x):
    // Calcular pendiente
    m ← (y1 − y0) / (x1 − x0)
    // Calcular valor interpolado
    y ← y0 + m * (x − x0)
    Retornar y
```

## Caso de Prueba


Interpolar entre los puntos conocidos:

- **Punto 1**: \((x_0, y_0) = (2, 4)\)  
- **Punto 2**: \((x_1, y_1) = (6, 10)\)  
- **Valor de interés**: \(x = 3\)

1. **Calcular la pendiente**  
   \[
   m = \frac{y_1 - y_0}{x_1 - x_0}
     = \frac{10 - 4}{6 - 2}
     = \frac{6}{4}
     = 1.5
   \]

2. **Calcular el valor interpolado**  
   \[
   y(3) = y_0 + m \cdot (x - x_0)
        = 4 + 1.5 \times (3 - 2)
        = 4 + 1.5
        = 5.5
   \]
### Resultado esperado
 Valor interpolado ≈ 5.5




