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
   
   m = yi-y0/x1-x0
   
3. Usar la fórmula de la recta para hallar \(y\) en el punto \(x\):
   y(x)=y0+m * (x-x0)
4. Devolver el valor interpolado (y).


## Pseudocódigo del Interpolación Lineal




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

- **Punto 1**: (x_0, y_0) = (2, 4) 
- **Punto 2**: (x_1, y_1) = (6, 10)  
- **Valor de interés**: x = 3

1. **Calcular la pendiente**  
   
   m = {y_1 - y_0} / {x_1 - x_0}
     = {10 - 4} / {6 - 2}
     = {6} / {4}
     = 1.5
  

2. **Calcular el valor interpolado**  
   \[
   y(3) = y_0 + m  (x - x_0)
        = 4 + 1.5  (3 - 2)
        = 4 + 1.5
        = 5.5
   \]
### Resultado esperado
 Valor interpolado ≈ 5.5




# Interpolación Newton 


La **interpolación de Newton** es un método para construir un polinomio que pasa exactamente por un conjunto de puntos \(\{(x_i, y_i)\}_{i=0}^n\). Utiliza las **diferencias divididas** para obtener los coeficientes del polinomio de forma eficiente.



---

##  ¿En qué consiste?

1. **Calcular la tabla de diferencias divididas** \(f[x_i,\dots,x_{i+k}]\).

   ![divided_differences_formula](https://github.com/user-attachments/assets/b4875a1a-9553-4f60-9305-a84e1728d33c)

3. **Construir el polinomio** en forma de Newton:

   ![newton_polynomial_formula](https://github.com/user-attachments/assets/70200795-7ff3-418b-9cb4-7a7fa0a3c9d1)





3. **Evaluar** \(P_n(x)\) en el punto deseado.


---

## Pasos a seguir

1. **Reunir los datos**  
   - Vectores de puntos:  
     ```text
     x = [x₀, x₁, …, xₙ]
     y = [y₀, y₁, …, yₙ]
     ```

2. **Inicializar la tabla de diferencias divididas**  
   - Crear matriz `dd` de tamaño `(n+1) × (n+1)`.  
   - Rellenar la primera columna con los valores de `y`:
     ```text
     Para i = 0 hasta n:
         dd[i][0] = y[i]
     ```

3. **Calcular las diferencias divididas**  
   - Para cada orden `j` desde 1 hasta `n`:
     ```text
     Para j = 1 hasta n:
         Para i = 0 hasta n - j:
             dd[i][j] = (dd[i+1][j-1] - dd[i][j-1]) / (x[i+j] - x[i])
     ```

4. **Construir el polinomio de Newton**  
   - La forma general es:
     ```text
     Pₙ(x) = dd[0][0]
           + dd[0][1]·(x - x₀)
           + dd[0][2]·(x - x₀)(x - x₁)
           + … 
           + dd[0][n]·(x - x₀)…(x - xₙ₋₁)
     ```

5. **Evaluar el polinomio en un punto**  
   - Dado `xEval`, inicializar:
     ```text
     resultado = dd[0][0]
     producto  = 1
     ```
   - Para cada `k` de 1 a `n`:
     ```text
     producto  = producto * (xEval - x[k-1])
     resultado = resultado + dd[0][k] * producto
     ```

6. **Obtener la interpolación**  
   - El valor `resultado` final es \(Pₙ(xEval)\).


## Pseudocódigo del Método de Interpolación Newton 



```plaintext
Función NewtonInterpolation(x[0..n], y[0..n], xEval):
    n = longitud(x) - 1
    // 1. Inicializar diferencias divididas
    Crear dd[0..n][0..n]
    Para i = 0 hasta n:
        dd[i][0] = y[i]

    // 2. Calcular diferencias divididas
    Para j = 1 hasta n:
        Para i = 0 hasta n - j:
            dd[i][j] = (dd[i+1][j-1] - dd[i][j-1]) / (x[i+j] - x[i])

    // 3. Evaluar el polinomio en xEval
    resultado = dd[0][0]
    producto  = 1
    Para k = 1 hasta n:
        producto  = producto * (xEval - x[k-1])
        resultado = resultado + dd[0][k] * producto

    Retornar resultado
```

## Caso de Prueba

x = [9, 12, 15]
y = [15, 21, 18]
xEval = 13.5



- dd[0][0] = 15, dd[1][0] = 21, dd[2][0] = 18

- dd[0][1] = (21 - 15)/(12-9) = 2

- dd[1][1] = (18 - 21)/(15-12) = -1

- dd[0][2] = (-1 - 2)/(15-9) = -0.5

```plaintext
resultado = dd[0][0] = 15
producto = 1
k=1: producto *= (13.5 - 9) = 4.5
      resultado += 2 * 4.5 = 15 + 9 = 24
k=2: producto *= (13.5 - 12) = 4.5 * 1.5 = 6.75
      resultado += (-0.5) * 6.75 = 24 - 3.375 = 20.625
```

### Resultado esperado
Valor interpolado en x = 13.5 es: 20.625


