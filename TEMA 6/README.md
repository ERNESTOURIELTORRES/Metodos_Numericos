# METODOS NUMERICOS 

En este readme lo veremos en 2 partes la interpolarización y Métodos de regresión: 
- Método de Adams Bashforth 
- Método de Euler
- Método de Heun 

# Método de Adams Bashforth

El método de Adams–Bashforth es un método explícito de integración de ecuaciones diferenciales ordinarias (EDO) basado en fórmulas de diferencias hacia adelante. En particular, la versión de **4 pasos** utiliza los cuatro valores previos de la función para predecir el siguiente valor.



---

##  ¿En qué consiste?
El método de Adams–Bashforth es un integrador explícito para resolver ecuaciones diferenciales ordinarias de la forma:

- dy/dt = f(t, y), y(t₀) = y₀


la fórmula de Adams–Bashforth de orden 4 es:

```plaintext
 yₙ₊₁ = yₙ + (h/24)·[ 55·f(tₙ, yₙ)
– 59·f(tₙ₋₁, yₙ₋₁)
+ 37·f(tₙ₋₂, yₙ₋₂)
– 9·f(tₙ₋₃, yₙ₋₃) ]
```

donde **h** es el tamaño de paso y tₙ = t₀ + n h.

---

## Pasos del método

1. **Inicio**  
   Definir t₀, y₀, tamaño de paso h y número total de pasos n.

2. **Arranque con RK4**  
   Como Adams–Bashforth de 4 pasos requiere los tres valores previos, se usan 3 pasos con Runge–Kutta de orden 4 (RK4) para obtener y₁, y₂ y y₃.

3. **Bucle de predicción**  
   Para cada i = 3,4,…,n–1:
   1. Calcular tₙ₊₁ = tᵢ + h.  
   2. Aplicar la fórmula de 4 pasos para obtener yₙ₊₁.  
   3. Añadir tₙ₊₁ y yₙ₊₁ a las listas de resultados.

4. **Salida**  
   Se obtiene una lista de pares (tᵢ, yᵢ) aproximando la solución en cada paso.


---

## Pseudocódigo del Interpolación Lineal

```plaintext
función AdamsBashforth4(f, t0, y0, h, n):
crear listas T = [t0], Y = [y0]
para i desde 0 hasta 2:
    t = T[último]
    y = Y[último]
    k1 = f(t, y)
    k2 = f(t + h/2, y + h·k1/2)
    k3 = f(t + h/2, y + h·k2/2)
    k4 = f(t + h,     y + h·k3)
    y_next = y + (h/6)·(k1 + 2·k2 + 2·k3 + k4)
    t_next = t + h
    añadir t_next a T
    añadir y_next a Y

// Predicción con Adams–Bashforth de 4 pasos
para i desde 3 hasta n–1:
    t_next = T[i] + h
    y_next = Y[i]
             + (h/24)·(
                 55·f(T[i],   Y[i])
               – 59·f(T[i–1], Y[i–1])
               + 37·f(T[i–2], Y[i–2])
               –  9·f(T[i–3], Y[i–3])
             )
    añadir t_next a T
    añadir y_next a Y

retornar (T, Y)



```

---

## Caso de Prueba

- **Ecuación**:  
  dy/dt = y  
- **Solución exacta**:  
  y(t) = e^{t}
- **Condiciones iniciales**:  
  t₀ = 0, y₀ = 1
- **Paso**:  
  h = 0.2
- **Número de pasos**:  
  n = 20  

### Resultado esperado

|  t  | y_aprox (AB4) |  y_exacta  |
|:---:|:-------------:|:----------:|
| 0.0 |      1.000000 |   1.000000 |
| 0.2 |      1.221400 |   1.221403 |
| 0.4 |      1.491818 |   1.491825 |
| 0.6 |      1.822106 |   1.822119 |
| 0.8 |      2.225360 |   2.225541 |
| 1.0 |      2.717820 |   2.718282 |

Se observa muy buena concordancia con la solución exacta e^t, con un error de orden (O(h^4)) característico de este método.


# Método de Euler

El método de Euler es un procedimiento numérico simple y directo para resolver ecuaciones diferenciales ordinarias (EDO) de primer orden. Es uno de los métodos más básicos y conocidos en análisis numérico, útil para obtener soluciones aproximadas de EDOs cuando no es posible resolverlas de forma analítica.




---

##  ¿En qué consiste?
El método consiste en aproximar la solución de la EDO paso a paso, utilizando la derivada en el punto actual para estimar el valor de la función en el siguiente punto. Si se conoce el valor de y en un punto t, se puede usar la pendiente dada por la función f(t, y) para avanzar hacia el siguiente punto t + h.

La fórmula que define el método de Euler es:

y_siguiente = y_actual + h * f(t_actual, y_actual)

donde:

- y_actual es el valor aproximado de la solución en el tiempo t_actual
- h es el tamaño del paso
- f(t_actual, y_actual) es la derivada evaluada en ese punto
- y_siguiente es el valor estimado de y en el siguiente paso

---

## Pasos del método

1. **Definir la ecuación diferencial**  
   Debes tener una ecuación de la forma:  
   dy/dt = f(t, y)

2. **Establecer condiciones iniciales**  
   Se debe conocer el valor inicial:  
   t0 (tiempo inicial)  
   y0 (valor de y en t0)

3. **Elegir el tamaño del paso (h)**  
   Este valor determina qué tan separados estarán los puntos en el eje t.

4. **Determinar el número de pasos**  
   Depende de hasta qué tiempo deseas aproximar la solución.  
   Por ejemplo:  
   pasos = (t_final - t0) / h

5. **Aplicar la fórmula del método de Euler**  
   Para cada paso i, calcular:  
   y_siguiente = y_actual + h * f(t_actual, y_actual)  
   t_siguiente = t_actual + h

6. **Repetir el proceso**  
   Usar el nuevo valor de y y t como punto de partida para el siguiente paso.

7. **Registrar los valores**  
   Guardar cada par (t, y) calculado para poder graficar o comparar con la solución exacta.


---

## Pseudocódigo del Interpolación Lineal

```plaintext

Proceso Metodo_Euler

    Escribir "Ingrese el número de ecuaciones del sistema:"
    Leer n

    Dimension varNames[n]
    Para i <- 0 Hasta n - 1
        Escribir "Ingrese el nombre de la variable ", i + 1, ":"
        Leer varNames[i]
    Fin Para

    Dimension ecuaciones[n]
    Para i <- 0 Hasta n - 1
        Escribir "Ingrese la ecuación para d", varNames[i], "/dt (en función de t y las variables):"
        Leer ecuaciones[i]
    Fin Para

    Dimension condiciones[n]
    Para i <- 0 Hasta n - 1
        Escribir "Ingrese la condición inicial para ", varNames[i], "(0):"
        Leer condiciones[i]
    Fin Para

    Escribir "Ingrese el paso de tiempo h:"
    Leer h

    Escribir "Ingrese el tiempo final:"
    Leer tFinal

    pasos <- Trunc(tFinal / h) + 1

    Dimension tiempo[pasos]
    Para i <- 0 Hasta pasos - 1
        tiempo[i] <- i * h
    Fin Para

    Dimension solucion[n, pasos]

    Para i <- 0 Hasta n - 1
        solucion[i, 0] <- condiciones[i]
    Fin Para

    Para i <- 1 Hasta pasos - 1
        t <- tiempo[i - 1]
        Para j <- 0 Hasta n - 1
            // Evaluar f_j en t y valores anteriores
            // Aquí se debe reemplazar por la evaluación real de la expresión matemática
            derivada <- Evaluar(ecuaciones[j], t, solucion[0, i - 1], solucion[1, i - 1], ...)
            solucion[j, i] <- solucion[j, i - 1] + h * derivada
        Fin Para
    Fin Para

    Escribir "Resultados:"
    Para i <- 0 Hasta pasos - 1
        Escribir "t = ", tiempo[i], " -->"
        Para j <- 0 Hasta n - 1
            Escribir varNames[j], "(t) = ", solucion[j, i]
        Fin Para
    Fin Para

FinProceso

```

---

## Caso de Prueba
- Queremos resolver la ecuación diferencial ordinaria:

dy/dt = y

- La condicion iniciaa es:
y(0) = 1

- Usaremos el método de Euler con los siguientes parámetros:

1. Paso de tiempo: h = 0.2

2. Tiempo final: t = 1.0

El número de pasos se calcula como:
- pasos = (1.0 - 0.0) / 0.2 + 1 = 6

Para cada paso i:

y[i+1] = y[i] + h * f(t[i], y[i])

- Donde f(t, y) = y

### Resultado esperado


| Paso | t     | y (Euler)  | f(t,y) = y |
|------|-------|------------|------------|
| 0    | 0.0   | 1.000000   | 1.000000   |
| 1    | 0.2   | 1.200000   | 1.200000   |
| 2    | 0.4   | 1.440000   | 1.440000   |
| 3    | 0.6   | 1.728000   | 1.728000   |
| 4    | 0.8   | 2.073600   | 2.073600   |
| 5    | 1.0   | 2.488320   | 2.488320   |

- La solución exacta es: y(t) = e^t

| t     | y (Euler) | y (Exacta) | Error absoluto |
|-------|-----------|------------|----------------|
| 0.0   | 1.000000  | 1.000000   | 0.000000       |
| 0.2   | 1.200000  | 1.221403   | 0.021403       |
| 0.4   | 1.440000  | 1.491825   | 0.051825       |
| 0.6   | 1.728000  | 1.822119   | 0.094119       |
| 0.8   | 2.073600  | 2.225541   | 0.151941       |
| 1.0   | 2.488320  | 2.718282   | 0.229962       |