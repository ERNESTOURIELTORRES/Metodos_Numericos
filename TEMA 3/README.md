# METODOS NUMERICOS

En este readme veremos los metodos siguientes : 
- Gauss
- Gauss Jordan 
- Gauss Seidel 
- Jacobi 

# Método de Gauss

El **método de Gauss** o **eliminación gaussiana** es una técnica algebraica utilizada para resolver sistemas de ecuaciones lineales. El objetivo es transformar la matriz aumentada del sistema en una forma escalonada (triangular superior), para luego aplicar sustitución regresiva y obtener las soluciones.

---

##  ¿En qué consiste?

1. **Triangular la matriz aumentada** mediante operaciones fila:
   - Intercambio de filas
   - Multiplicación por constantes
   - Suma o resta de filas
2. **Sustitución regresiva** desde la última ecuación hacia la primera para encontrar las incógnitas.

Un sistema de ecuaciones lineales con \( n \) incógnitas puede escribirse como:




Esto se representa como una **matriz aumentada**:

![Captura de pantalla 2025-05-18 100122](https://github.com/user-attachments/assets/f9d66cff-a4dc-48a4-be91-c4a805ec8f9c)


![gaus](https://github.com/user-attachments/assets/7133d94f-3739-4f5d-b835-b47dc48e64f4)




---


## Pseudocódigo del Método de Gauss

```plaintext
## ⚙️ Pseudocódigo

```plaintext
Inicio
  Leer número de ecuaciones n
  Crear matriz aumentada de tamaño n x (n+1)

  Por cada fila i de 0 a n-1
    Buscar el pivote más grande en columna i
    Intercambiar filas si es necesario

    Por cada fila k desde i+1 hasta n-1
      factor ← matriz[k][i] / matriz[i][i]
      Por cada columna j desde i hasta n
        matriz[k][j] ← matriz[k][j] - factor * matriz[i][j]

  Sustitución regresiva:
  Por i desde n-1 hasta 0
    solución[i] ← matriz[i][n]
    Por j desde i+1 hasta n-1
      solución[i] ← solución[i] - matriz[i][j] * solución[j]
    solución[i] ← solución[i] / matriz[i][i]

  Mostrar solución
Fin
```

## Caso de Prueba

Sistema de ecuaciones:

2x + 3y + z = 1  
4x + y - 2z = 2  
-2x + 5y + 2z = 3

Representación como matriz aumentada:

[  2   3   1  |  1 ]  
[  4   1  -2  |  2 ]  
[ -2   5   2  |  3 ]

Proceso:

1. Convertir a forma escalonada (triangular superior)
2. Usar sustitución regresiva



x1 = 1.0000  
x2 = -1.0000  
x3 = 2.0000

### Resultado esperado
x1 = 1.0000  
x2 = -1.0000  
x3 = 2.0000






# Método de Gauss-Jordan 

El **método de Gauss-Jordan** es una extensión del método de Gauss que permite **resolver sistemas de ecuaciones lineales** transformando la matriz aumentada en una matriz identidad. 

---

##  ¿En qué consiste?

A diferencia del método de Gauss, en Gauss-Jordan también se eliminan los elementos por encima del pivote, dejando solo unos en la diagonal principal y ceros en el resto de la matriz de coeficientes.

**[A | b]  →  [I | x]**

- A es la matriz de coeficientes del sistema

- b es el vector de términos independientes

- I es la matriz identidad

- x es el vector de soluciones del sistema
---

##  Pasos del Método

1. Pedir al usuario la cantidad de ecuaciones del sistema.

2. Leer la matriz aumentada de tamaño n x (n+1).

Para cada fila:

3. Normalizar el pivote para que sea igual a 1.

4. Hacer ceros en la columna del pivote tanto por debajo como por encima.

5. La solución estará en la última columna de la matriz resultante.
---


## Pseudocódigo del Método de Bisección

```plaintext
Entrada: matriz aumentada de tamaño n x (n+1)

Para cada fila i de 0 a n-1:
    Dividir toda la fila i entre el valor del pivote matriz[i][i]

    Para cada fila k de 0 a n-1:
        Si k ≠ i:
            factor = matriz[k][i]
            Para cada columna j de 0 a n:
                matriz[k][j] = matriz[k][j] - factor * matriz[i][j]

Salida: Última columna contiene las soluciones del sistema

```

## Caso de Prueba


x + y + z = 6  
2y + 5z = -4  
2x + 5y - z = 27


[1  1  1 | 6 ]  
[0  2  5 | -4]  
[2  5 -1 | 27]



### Resultado esperado
x = 5  
y = 3  
z = -2

# Método de Gauss Seidel

El método de Gauss-Seidel es un método iterativo utilizado para resolver sistemas de ecuaciones lineales de la forma:

- 𝐴⋅𝑥=𝑏

donde:

- 𝐴 es una matriz de coeficientes.

- 𝑥 es el vector de incógnitas.

- 𝑏 es el vector de resultados.

Este método es útil especialmente cuando el sistema es grande y disperso, y puede converger rápidamente si la matriz cumple ciertas condiciones (como ser diagonalmente dominante).

---

##  ¿En qué consiste?

Consiste en resolver iterativamente cada una de las incógnitas del sistema utilizando las últimas actualizaciones disponibles en cada paso, es decir, se va reemplazando cada componente de 
𝑥 tan pronto como se calcula.

Dado un sistema con 𝑛 ecuaciones y 𝑛 incógnitas, la fórmula general para actualizar la 𝑖-ésima variable en la 𝑘-ésima iteración es:

![Captura de pantalla 2025-05-18 102640](https://github.com/user-attachments/assets/d61363fe-0bb3-4ffd-bb69-ef53edb4a0c1)


---

##  Pasos del Método
1. Inicializar el vector solución `x` con ceros (o un valor inicial).
2. Establecer una **tolerancia `tol`** y un **máximo de iteraciones `maxIter`**.
3. Repetir hasta alcanzar la tolerancia o el número máximo de iteraciones:
    - Para cada fila `i`, calcular el nuevo valor de `x[i]`.
    - Usar los valores más recientes disponibles.
4. Comparar el cambio entre las iteraciones actuales y anteriores.
5. Si el cambio es menor que `tol`, se ha alcanzado la convergencia.

---


## Pseudocódigo del Método de Bisección

```plaintext
Entrada: matriz A[n][n], vector b[n], tolerancia tol, máximo de iteraciones maxIter
Salida: vector x[n] solución del sistema

Inicializar x[n] con ceros
Inicializar xOld[n]

Para iter = 0 hasta maxIter - 1:
    Copiar x en xOld

    Para i = 0 hasta n-1:
        sum = 0
        Para j = 0 hasta n-1:
            Si j ≠ i:
                sum += A[i][j] * x[j]
        x[i] = (b[i] - sum) / A[i][i]

    maxDiff = 0
    Para i = 0 hasta n-1:
        maxDiff = máximo entre maxDiff y |x[i] - xOld[i]|

    Si maxDiff < tol:
        Imprimir "Convergencia alcanzada en iter + 1 iteraciones"
        Salir del bucle

Retornar x

```
---

## Caso de Prueba


1. 4x +  y +  z =  7
2. x + 3y +  z =  8
3. x +  y + 5z =  9


Matriz A:


- [ 4  1  1 ]
- [ 1  3  1 ]
- [ 1  1  5 ]


 Vector B:


- [ 7 ]
- [ 8 ]
- [ 9 ]


 Parametros :


Tolerancia: 1e-6

Iteraciones máximas: 100





### Resultado esperado

x ≈ 1.000000
y ≈ 2.000000
z ≈ 1.000000

# Método de Jacobi 
El **método de Jacobi** es un método iterativo utilizado para resolver sistemas de ecuaciones lineales de la forma:

A · x = b


Donde:
- `A` es la matriz de coeficientes.
- `x` es el vector de incógnitas.
- `b` es el vector de términos independientes.

Es especialmente útil cuando la matriz `A` es **diagonalmente dominante** o **simétrica definida positiva**, ya que esto garantiza la convergencia del método.


---

##  ¿En qué consiste?

El método consiste en calcular iterativamente los valores de las incógnitas utilizando únicamente los valores de la iteración anterior. A diferencia del método de Gauss-Seidel, **Jacobi no actualiza los valores intermedios hasta terminar toda la iteración**.

![Captura de pantalla 2025-05-18 102640](https://github.com/user-attachments/assets/d61363fe-0bb3-4ffd-bb69-ef53edb4a0c1)


---

##  Pasos del Método

1. Ingresar la matriz `A`, el vector `b` y un valor inicial para `x`.
2. Definir una **tolerancia** y un **número máximo de iteraciones**.
3. Repetir el siguiente proceso hasta que:
    - Se alcance la tolerancia deseada, o
    - Se supere el número máximo de iteraciones.
4. Para cada componente `x[i]`, calcular el nuevo valor `xNew[i]` sin modificar `x`.
5. Comprobar si la diferencia entre `xNew` y `x` es menor que la tolerancia para todas las variables.
6. Si se alcanza la tolerancia, el proceso ha convergido.

---


## Pseudocódigo del Método de Bisección

```plaintext
Entrada: matriz A[n][n], vector b[n], vector inicial x[n], tolerancia tol, máximo de iteraciones maxIter
Salida: vector x[n] solución aproximada

Inicializar vector xNew[n]
iteraciones = 0

Mientras iteraciones < maxIter:
    iteraciones += 1

    Para i = 0 hasta n-1:
        suma = 0
        Para j = 0 hasta n-1:
            Si j ≠ i:
                suma += A[i][j] * x[j]
        xNew[i] = (b[i] - suma) / A[i][i]

    esConvergente = true
    Para i = 0 hasta n-1:
        Si |xNew[i] - x[i]| > tol:
            esConvergente = false
            Romper

    Copiar xNew en x

    Si esConvergente:
        Imprimir "Convergencia alcanzada en iteraciones"
        Salir del bucle

Si no esConvergente:
    Imprimir "No se alcanzó convergencia"

Retornar x

```
---

## Caso de Prueba


1. 4x +  y +  z =  7
2. x + 3y +  z =  8
3. x +  y + 5z =  9



Matriz A:


[ 4  1  1 ]
[ 1  3  1 ]
[ 1  1  5 ]



 Vector B:


[ 7 ]
[ 8 ]
[ 9 ]


Vector Inicial :

[ 0 ]
[ 0 ]
[ 0 ]


Tolerancia: 1e-6
Iteraciones máximas: 100








### Resultado esperado

x ≈ 1.000000
y ≈ 2.000000
z ≈ 1.000000

