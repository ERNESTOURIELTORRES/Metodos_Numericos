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


# Método de Correlación 

El **método de correlación y regresión lineal** permite cuantificar la relación lineal entre dos variables (\(X\) e \(Y\)) y ajustar una línea recta que mejor describa sus datos.  




---

##  ¿En qué consiste?

- La **regresión lineal** obtiene la ecuación de la recta \(Y = \beta_0 + \beta_1 X\).  
- El **coeficiente de correlación** \(r\) mide la fuerza y dirección de la relación lineal (va de –1 a +1).
![correlacion_intercept_formula](https://github.com/user-attachments/assets/5772e1db-c364-4df3-83de-fbc3bebb05a1)
![correlacion_r_formula](https://github.com/user-attachments/assets/e9c79aea-a649-425a-b943-39e55385cdaf)
![correlacion_slope_formula](https://github.com/user-attachments/assets/c35e59c5-5b8a-4446-a193-34c4a925fd26)






---

## Pasos a seguir

1. **Recolectar datos.** 
2. **Calcular** sumas y sumas de cuadrados.
3. **Determinar** los coeficientes de la recta de regresión:  
   - Pendiente
   - Intercepto  
4. **Calcular** el coeficiente de correlación \(r\).  
5. **Interpretar**:  
   - r>0: relación positiva.  
   - r<0: relación negativa.  
   - r=0: relación nula.

## Pseudocódigo del Método Correlación  



```plaintext
Función CorrelaciónRegresión(X[1..n], Y[1..n]):
    // 1. Inicializar sumas
    sumaX  ← 0;  sumaY  ← 0
    sumaXY ← 0;  sumaX2 ← 0; sumaY2 ← 0

    // 2. Acumular
    Para i = 1 hasta n:
        sumaX  ← sumaX  + X[i]
        sumaY  ← sumaY  + Y[i]
        sumaXY ← sumaXY + X[i]*Y[i]
        sumaX2 ← sumaX2 + X[i]^2
        sumaY2 ← sumaY2 + Y[i]^2

    // 3. Calcular coeficientes de regresión
    β1 ← (n * sumaXY - sumaX * sumaY) /
          (n * sumaX2 - sumaX^2)
    β0 ← (sumaY - β1 * sumaX) / n

    // 4. Calcular coeficiente de correlación
    r ← (n * sumaXY - sumaX * sumaY) /
         sqrt((n * sumaX2 - sumaX^2) * (n * sumaY2 - sumaY^2))

    Retornar (β0, β1, r)
```

## Caso de Prueba

|  i  |  $x_i$  |  $y_i$  |
| :-: | :-----: | :-----: |
|  1  |     1   |     2   |
|  2  |     2   |     3   |
|  3  |     3   |     5   |







```plaintext

n    = 3
Σx   = 1 + 2 + 3 = 6
Σy   = 2 + 3 + 5 = 10
Σxy  = 1*2 + 2*3 + 3*5 = 23
Σx²  = 1 + 4 + 9      = 14
Σy²  = 4 + 9 + 25     = 38

β1 = (3*23 - 6*10) / (3*14 - 6^2)
   = (69 - 60) / (42 - 36)
   = 9 / 6
   = 1.5

β0 = (Σy - β1*Σx) / n
   = (10 - 1.5*6) / 3
   = (10 - 9) / 3
   = 0.3333


   r = (3*23 - 6*10) / sqrt((42 - 36)*(3*38 - 10^2))
  = 9 / sqrt(6 * 14)
  = 9 / √84
  ≈ 0.9818


```

### Resultado esperado
- Ecuación de regresión: Y = 0.3333 + 1.5000 * X
- Coeficiente de correlación r ≈ 0.9818
- Interpretación: Relación positiva fuerte

# Método Minimos Cuadrados 

El **método de mínimos cuadrados** es una técnica estadística que ajusta una recta y = a + b x a un conjunto de datos \(\{(x_i, y_i)\}_{i=1}^n\) de modo que la suma de los cuadrados de las distancias verticales entre los puntos y la recta sea mínima.


---

##  ¿En qué consiste?

1. Reunir los pares de datos \((x_i, y_i)\).  
2. Suponer un modelo lineal \(y = a + b x\).  
3. Encontrar los coeficientes \(a\) (ordenada al origen) y \(b\) (pendiente) que minimicen  
   S(a,b) = ∑(yᵢ – (a + b xᵢ))²
4. Resolver las ecuaciones normales para obtener \(a\) y \(b\).
![minimos_a_formula](https://github.com/user-attachments/assets/42c68907-52c8-491b-a8a6-c4751f2a6d43)
![minimos_b_formula](https://github.com/user-attachments/assets/82b6df76-37ca-4e4c-98f8-ba550c2617b3)
![minimos_s_formula](https://github.com/user-attachments/assets/ccae8043-6803-491b-926b-76a1314162d6)

   






---

## Pasos a seguir
1. **Reunir datos**  
   - Vectores x[1..n], y[1..n].  
2. **Calcular sumatorias**  
   ```text
   sum_x  = Σ x_i
   sum_y  = Σ y_i
   sum_x2 = Σ x_i^2
   sum_xy = Σ x_i * y_i

   Calcular pendiente 
𝑏 usando la fórmula de arriba.

Calcular intercepto 
𝑎

Formar la ecuación de regresión:

ŷ=𝑎+𝑏𝑥

## Pseudocódigo del Método Minimos Cuadrados



```plaintext
Función AjusteLinealMinimosCuadrados(x[1..n], y[1..n]):
    // 1. Inicializar sumas
    sum_x  ← 0
    sum_y  ← 0
    sum_x2 ← 0
    sum_xy ← 0

    // 2. Acumular valores
    Para i = 1 hasta n:
        sum_x  ← sum_x  + x[i]
        sum_y  ← sum_y  + y[i]
        sum_x2 ← sum_x2 + x[i]^2
        sum_xy ← sum_xy + x[i]*y[i]

    // 3. Calcular coeficientes
    b ← (n*sum_xy - sum_x*sum_y) / (n*sum_x2 - sum_x^2)
    a ← (sum_y - b*sum_x) / n

    Retornar (a, b)
```

## Caso de Prueba

| $i$ | $x_i$ | $y_i$ |
| :-: | :---: | :---: |
|  1  |   1   |   2   |
|  2  |   2   |  2.8  |
|  3  |   3   |  3.6  |
|  4  |   4   |  4.5  |








```plaintext

n    = 4
Σx   = 1 + 2 + 3 + 4 = 10
Σy   = 2 + 2.8 + 3.6 + 4.5 = 12.9
Σx²  = 1 + 4 + 9 + 16 = 30
Σxy  = 1*2 + 2*2.8 + 3*3.6 + 4*4.5 = 2 + 5.6 + 10.8 + 18 = 36.4


b = (4*36.4 - 10*12.9) / (4*30 - 10^2)
  = (145.6 - 129) / (120 - 100)
  = 16.6 / 20
  = 0.83

a = (12.9 - 0.83*10) / 4
  = (12.9 - 8.3) / 4
  = 4.6 / 4
  = 1.15


ŷ = 1.15 + 0.83·x



```

### Resultado esperado
La recta de regresión es: y = 1.1500 + 0.8300 x


