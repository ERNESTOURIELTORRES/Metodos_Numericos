# METODOS NUMERICOS

En este readme veremos los metodos siguientes : 
- Cuadratica Gaussiana
- Diferenciación 3 puntos
- Diferenciación 5 puntos 
- Integración Simpson 1/3
- Integración Simpson 3/8
- Intgración Trapecio 

# Método Cuadratura Gaussiana 

La **cuadratura gaussiana** consiste en aproximar la integral de una función f(x) en un intervalo, típicamente de [−1,1], mediante una suma ponderada de los valores de la función en ciertos puntos específicos llamados nodos.

---

##  ¿En qué consiste?

A diferencia de métodos como el trapecio o Simpson, donde los puntos están equiespaciados, en la cuadratura gaussiana estos puntos y sus respectivos pesos se eligen cuidadosamente para maximizar la precisión del método.

- En la formulación general, se busca aproximar:

![Captura de pantalla 2025-05-20 202412](https://github.com/user-attachments/assets/06d43a6a-55b9-4399-8d8b-434376d9173b)


- Cambio de variable 

![Captura de pantalla 2025-05-20 202202](https://github.com/user-attachments/assets/d2861db7-4d32-49e0-9096-6c7f1d652a8b)

---

##  Pasos del Método

1. Reescribe la integral en el intervalo [−1,1]
2. Selecciona el número de puntos nnn (grados de precisión)
3. Obtén los nodos xi​ y pesos wi
 - Para el valor de nnn seleccionado:
- Los nodos xi​ son las raíces del polinomio de Legendre Pn(x)


- Los pesos wi​ están tabulados y dependen de nnn.
4. Evalúa la función en los nodos transformados
5. Aplica la fórmula de cuadratura


- \( f(x) \): Función a integrar.
- \( a \), \( b \): Límites de integración.
- \( n \): Número de puntos de Gauss (por ejemplo 2, 3, 4 o 5).
- \( \xi_i \): Puntos nodales en \([-1, 1]\), definidos por los ceros del polinomio de Legendre de grado \(n\).
- \( w_i \): Pesos asociados a cada punto \(\xi_i\).
- \( x_i \): Puntos transformados al intervalo original \([a, b]\).

---


## Pseudocódigo del Método de Cuadratura Gaussiana


```plaintext
INICIO DEL PROGRAMA

IMPRIMIR "INTEGRACIÓN POR CUADRATURA GAUSSIANA"

IMPRIMIR "Ingresa la función f(x):"
LEER funcionStr

// Evaluar la función (en pseudocódigo suponemos que existe una función para eso)
DEFINIR funcion(x) COMO evaluar la expresión funcionStr, reemplazando "x" por el valor dado

IMPRIMIR "Ingresa el límite inferior (a):"
LEER a

IMPRIMIR "Ingresa el límite superior (b):"
LEER b

IMPRIMIR "Número de puntos (2, 3, 4 o 5):"
LEER n

SI n < 2 O n > 5 ENTONCES
    IMPRIMIR "Número no válido, se usará 5 puntos por defecto"
    n ← 5
FIN SI

// Tabla de puntos y pesos para n = 2 a 5
DEFINIR puntosPesos COMO MATRIZ [
    // n = 2
    [-0.5773502692, 1.0, 0.5773502692, 1.0],
    // n = 3
    [-0.7745966692, 0.5555555556, 0.0, 0.8888888889, 0.7745966692, 0.5555555556],
    // n = 4
    [-0.8611363116, 0.3478548451, -0.3399810436, 0.6521451549, 0.3399810436, 0.6521451549, 0.8611363116, 0.3478548451],
    // n = 5
    [-0.9061798459, 0.2369268851, -0.5384693101, 0.4786286705, 0.0, 0.5688888889, 0.5384693101, 0.4786286705, 0.9061798459, 0.2369268851]
]

suma ← 0
index ← n - 2  // índice en la tabla

PARA i DESDE 0 HASTA n - 1 HACER
    xi ← puntosPesos[index][2 * i]
    wi ← puntosPesos[index][2 * i + 1]
    
    // Cambiar de intervalo [-1, 1] a [a, b]
    x ← ((b - a) * xi + (a + b)) / 2
    fx ← funcion(x)

    suma ← suma + wi * fx
FIN PARA

resultado ← (b - a) / 2 * suma

IMPRIMIR "El resultado de la integral es: ", resultado

FIN DEL PROGRAMA
```

## Caso de Prueba


Este caso de prueba ilustra cómo se utiliza el programa para calcular una integral definida mediante el método de Cuadratura Gaussiana.

---

### Entradas

| Parámetro         | Valor                         |
|------------------|-------------------------------|
| Función          | `x^2`                         |
| Límite inferior  | `a = 0`                       |
| Límite superior  | `b = 1`                       |
| Número de puntos | `n = 2`                       |

---

###  Cálculo Teórico

La integral de `f(x) = x^2` en el intervalo `[0, 1]` es:

```math
∫₀¹ x² dx = [x³ / 3]₀¹ = (1³ / 3) - (0³ / 3) = 1/3 ≈ 0.333333
```

- Intervalo: a = 0, b = 1
- Número de puntos: n = 2
- Puntos y pesos de Gauss-Legendre para n = 2:
  - `x₁ = -0.5773502692`, `w₁ = 1.0`
  - `x₂ =  0.5773502692`, `w₂ = 1.0`

- Cambio de variable:
  - Fórmula: `x'_i = ((b - a) * x_i + (a + b)) / 2`
  - `x₁' = ((1 - 0) * -0.5773502692 + (0 + 1)) / 2 = 0.2113248654`
  - `x₂' = ((1 - 0) *  0.5773502692 + (0 + 1)) / 2 = 0.7886751346`

###  Evaluaciones de la función `f(x) = x^2`

- `f(x₁') = (0.2113248654)^2 ≈ 0.0446581987`
- `f(x₂') = (0.7886751346)^2 ≈ 0.6220084679`

### 🧾 Resultado

- Fórmula de integración de Cuadratura Gaussiana:
  
  ```math
  ∫ₐᵇ f(x) dx ≈ (b - a) / 2 × Σ [wᵢ × f(xᵢ')]
  ```


## Resultado esperado
```plaintext
Ingresa la función f(x): x^2
Ingresa el límite inferior (a): 0
Ingresa el límite superior (b): 1
Número de puntos (2, 3, 4 o 5): 2

El resultado de la integral es: 0.3333
```

# Método de Diferenciación 3 puntos 


El **Método de Diferenciación de 3 Puntos** es una técnica de diferenciación numérica usada para aproximar la derivada de una función en un punto, basándose en los valores de la función en tres puntos cercanos.

---

## ¿En qué consiste ?

Este método utiliza una fórmula centrada que emplea los valores de la función en los puntos 
\( x_0 - h \), \( x_0 \) y \( x_0 + h \).

La fórmula para la derivada aproximada es:

![Captura de pantalla 2025-05-20 205746](https://github.com/user-attachments/assets/cbe86609-be47-44de-b6be-3eeb25042674)

- Donde:
- \( f'(x_0) \) es la derivada aproximada en el punto \( x_0 \).
- \( h \) es un paso pequeño.
- \( f(x_0 + h) \) y \( f(x_0 - h) \) son los valores de la función en puntos cercanos a \( x_0 \).



Esta fórmula tiene un error de orden \( O(h^2) \), lo que indica buena precisión cuando \( h \) es pequeño.

---

## Pasos para resolver

1. Definir la función \( f(x) \).
2. Elegir el punto \( x_0 \) donde se quiere calcular la derivada.
3. Seleccionar un valor pequeño para el paso \( h \).
4. Calcular \( f(x_0 + h) \) y \( f(x_0 - h) \).
5. Aplicar la fórmula:

6. Obtener el valor aproximado de la derivada en \( x_0 \).

---

## Pseudocódigo Diferenciación 3 puntos 

```text
Entrada: función f, punto x0, paso h

Calcular f1 = f(x0 + h)
Calcular f2 = f(x0 - h)

Derivada ≈ (f1 - f2) / (2 * h)

Mostrar resultado de la derivada
```


## Caso de prueba y resultado

### Datos de entrada:

- Función:  f(x) = x^2 + \sin(x) 
- Punto donde se calcula la derivada:  x_0 = 1.0 
- Paso:  h = 0.01 

### Cálculo realizado:


- f'(1.0) \approx \frac{f(1.0 + 0.01) - f(1.0 - 0.01)}{2 \times 0.01}


### Resultado obtenido:

- f'(1.0000) ≈ 2.5403



# Método de Diferenciación Numérica: 5 Puntos

El **método de diferenciación de 5 puntos** es una técnica de derivación numérica centrada que permite estimar la primera derivada de una función en un punto dado. Utiliza cinco evaluaciones de la función, distribuidas de manera simétrica alrededor del punto central, lo cual proporciona una aproximación más precisa que otros métodos como el de 3 puntos o 2 puntos.

---

## ¿En qué consiste?

Este método se basa en una fórmula centrada que utiliza los valores de la función en \(x_0 - 2h\), \(x_0 - h\), \(x_0 + h\), y \(x_0 + 2h\), con un paso \(h\) determinado por el usuario.

![Captura de pantalla 2025-05-20 213157](https://github.com/user-attachments/assets/2d608173-2b37-408c-bd8a-2f068b795c78)

| Símbolo           | Significado                                                                 |
|-------------------|------------------------------------------------------------------------------|
| `f'(x₀)`           | Aproximación de la derivada de la función en el punto `x₀`.                 |
| `f(x)`             | Función que se desea derivar.                                               |
| `x₀`               | Punto en el que se desea calcular la derivada.                              |
| `h`                | Tamaño del paso: distancia entre los puntos alrededor de `x₀`.              |
| `f(x₀ + 2h)`       | Valor de la función en el punto dos pasos a la derecha de `x₀`.             |
| `f(x₀ + h)`        | Valor de la función en el punto un paso a la derecha de `x₀`.               |
| `f(x₀ - h)`        | Valor de la función en el punto un paso a la izquierda de `x₀`.             |
| `f(x₀ - 2h)`       | Valor de la función en el punto dos pasos a la izquierda de `x₀`.     




---

## Pasos para resolver

1. **Definir la función** que se va a derivar, escrita en términos de \(x\).
2. **Elegir el punto \(x_0\)** donde se desea estimar la derivada.
3. **Elegir un valor para \(h\)**, el tamaño de paso (debe ser pequeño).
4. **Evaluar la función** en los puntos: \(x_0 - 2h\), \(x_0 - h\), \(x_0 + h\), \(x_0 + 2h\).
5. **Aplicar la fórmula** para obtener la derivada aproximada.
6. **Mostrar el resultado.**

---

## Pseudocódigo 5 puntos

```plaintext
Inicio
    Mostrar "Método de Diferenciación de 5 Puntos"
    
    Leer funcion como cadena (por ejemplo: exp(x)*cos(x))
    Leer x0 (punto donde se desea derivar)
    Leer h (tamaño del paso)
    
    Evaluar f(x0 + 2h)
    Evaluar f(x0 + h)
    Evaluar f(x0 - h)
    Evaluar f(x0 - 2h)

    derivada ← [ -f(x0 + 2h) + 8*f(x0 + h) - 8*f(x0 - h) + f(x0 - 2h) ] / (12*h)
    
    Mostrar derivada con 12 decimales
Fin

```

# Caso de Prueba

Queremos calcular la derivada de la función:

**f(x) = exp(x) · cos(x)**

En el punto:

- x₀ = 1  
- Paso h = 0.01  

---

### Fórmula Utilizada

Derivada centrada de 5 puntos:
f'(x₀) = [ -f(x₀ + 2h) + 8·f(x₀ + h) - 8·f(x₀ - h) + f(x₀ - 2h) ] / (12·h)


---

### Evaluación de los puntos

Calculamos los valores necesarios:

```plaintext

f(1.02) = exp(1.02) · cos(1.02) = 2.7730 · 0.5178 ≈ 1.4365
f(1.01) = exp(1.01) · cos(1.01) = 2.7456 · 0.5359 ≈ 1.4709
f(0.99) = exp(0.99) · cos(0.99) = 2.6906 · 0.5715 ≈ 1.5373
f(0.98) = exp(0.98) · cos(0.98) = 2.6645 · 0.5888 ≈ 1.5680

```


---

## Sustitución en la fórmula

Sustituyendo en la fórmula:

- f'(1) = [ -1.4365 + 8·1.4709 - 8·1.5373 + 1.5680 ] / (12·0.01)


Realizando paso a paso:

```plaintext

= [ -1.4365 + 11.7672 - 12.2984 + 1.5680 ] / 0.12
= [ -0.3997 ] / 0.12
= -3.3308

```


---

## Resultado Final

- f'(1) ≈ -3.3308

La derivada aproximada de `f(x) = exp(x) · cos(x)` en `x = 1` usando el método de 5 puntos con `h = 0.01` es **-3.3308**.

# Método de Integración Simpson 1/3

El método de Simpson 1/3 es una técnica numérica para aproximar el valor de una integral definida. Se basa en aproximar la función a integrar mediante segmentos de parábolas que pasan por puntos de la función.

Este método es más preciso que los métodos de rectángulos o trapecios cuando la función es suave, y requiere que el número de intervalos sea **par**.

---

##  ¿En qué consiste?

El intervalo \[a, b\] se divide en **n** subintervalos iguales (donde **n** es par), con ancho:

- h = (b - a) / n


Luego, se evalúa la función en los extremos y puntos internos. La fórmula general del método Simpson 1/3 es:

∫[a to b] f(x) dx ≈ (h / 3) × [ f(x₀) + 4·f(x₁) + 2·f(x₂) + 4·f(x₃) + ... + 2·f(x_{n-2}) + 4·f(x_{n-1}) + f(xₙ) ]

Donde:

- **x₀ = a**,  
- **xₙ = b**,  
- **x₁, x₂, ..., xₙ₋₁** son los puntos intermedios,  
- **h** es el ancho del subintervalo.

---

##  Pasos para resolver una integral con Simpson 1/3

1. Definir la función f(x) a integrar.
2. Elegir los límites de integración **a** y **b**.
3. Elegir el número de subintervalos **n** (debe ser par).
4. Calcular el valor de **h = (b - a) / n**.
5. Evaluar f(x) en los puntos: x₀, x₁, ..., xₙ.
6. Aplicar la fórmula del método Simpson 1/3.
7. Calcular el resultado.

---

##  Pseudocódigo del método Simpson 1/3

```pseudocode
Entrada:
  - f(x): función a integrar
  - a: límite inferior
  - b: límite superior
  - n: número de subintervalos (debe ser par)

Si n no es par:
  n ← n + 1

h ← (b - a) / n
suma ← f(a) + f(b)

Para i desde 1 hasta n - 1 hacer:
  x ← a + i * h
  Si i es par:
    suma ← suma + 2 * f(x)
  Sino:
    suma ← suma + 4 * f(x)

resultado ← (h / 3) * suma
Retornar resultado

```

# Caso de prueba

Calcular:

```plaintext

∫[1 a 2] (x^2 + 3x + 2) dx

Con n = 4 subintervalos.

Paso 1: Definir la función

f(x) = x² + 3x + 2

Paso 2: Calcular h

h = (b - a) / n = (2 - 1) / 4 = 0.25

Paso 3: Evaluar f(x) en los puntos
Los puntos son:

x₀ = 1.00   → f(x₀) = 1² + 3·1 + 2 = 6  
x₁ = 1.25   → f(x₁) = 1.5625 + 3.75 + 2 = 7.3125  
x₂ = 1.50   → f(x₂) = 2.25 + 4.5 + 2 = 8.75  
x₃ = 1.75   → f(x₃) = 3.0625 + 5.25 + 2 = 10.3125  
x₄ = 2.00   → f(x₄) = 4 + 6 + 2 = 12

Paso 4: Aplicar fórmula de Simpson 1/3

Resultado ≈ (h / 3) × [f(x₀) + 4·f(x₁) + 2·f(x₂) + 4·f(x₃) + f(x₄)]
Resultado ≈ (0.25 / 3) × [6 + 4·7.3125 + 2·8.75 + 4·10.3125 + 12]
Resultado ≈ 0.0833 × [6 + 29.25 + 17.5 + 41.25 + 12]
Resultado ≈ 0.0833 × 106
Resultado ≈ 8.833
```
## Resultado final

La integral de f(x) = x² + 3x + 2 en el intervalo [1, 2] es aproximadamente: 8.833

# Método de Integración Numérica: Simpson 3/8


El método de Simpson 3/8 es una técnica de integración numérica que permite aproximar el valor de una integral definida de una función continua. Es una extensión del método de Simpson 1/3 y se basa en utilizar polinomios de tercer grado (cúbicos) para aproximar la función dentro de intervalos igualmente espaciados.

Este método es especialmente útil cuando la función que se quiere integrar no tiene una primitiva conocida o resulta muy difícil de integrar analíticamente.

---

# ¿En que consiste?

El método de Simpson 3/8 consiste en dividir el intervalo de integración [a, b] en n subintervalos iguales, donde n debe ser múltiplo de 3. Luego, se aplica la siguiente fórmula:

Integral aproximada de f(x) desde a hasta b:

(3 * h / 8) * [ f(a) + 3 * sumatoria de f(x_i) con i no múltiplo de 3 + 2 * sumatoria de f(x_i) con i múltiplo de 3 + f(b) ]

donde:

h es el ancho de cada subintervalo, calculado como (b - a) / n

x_i representa los puntos intermedios, es decir, x_i = a + i * h para i = 1 hasta n - 1

---

# Pasos para integración simpson 3/8

1. Ingresar la función f(x) que se desea integrar.

2. Ingresar el límite inferior (a) y el límite superior (b) de la integral.

3. Ingresar el número de subintervalos (n). Este debe ser múltiplo de 3. Si no lo es, se ajusta al siguiente múltiplo de 3.

4. Calcular el valor de h como (b - a) / n.

5. Evaluar f(x) en todos los puntos del intervalo.

6. Aplicar la fórmula del método de Simpson 3/8.

7. Mostrar el resultado aproximado de la integral.


# Pseucódigo integración Simspson 3/8 


```plaintext


Inicio
    Mostrar mensaje de bienvenida
    Leer función f(x) como cadena
    Leer a (límite inferior)
    Leer b (límite superior)
    Leer n (número de subintervalos)
    
    Si n no es múltiplo de 3 entonces
        Ajustar n al siguiente múltiplo de 3

    Calcular h = (b - a) / n
    Calcular suma = f(a) + f(b)

    Para i desde 1 hasta n - 1 hacer
        Calcular x_i = a + i * h
        Si i es múltiplo de 3 entonces
            suma = suma + 2 * f(x_i)
        Sino
            suma = suma + 3 * f(x_i)
        FinSi
    FinPara

    Calcular resultado = (3 * h / 8) * suma
    Mostrar resultado
Fin

```

# Caso de prueba 

```plaintext

Función a integrar: f(x) = x^3 + 2*x + 1
Límite inferior: a = 0
Límite superior: b = 3
Número de intervalos: n = 6 (ya es múltiplo de 3)

Pasos del cálculo:

h = (3 - 0) / 6 = 0.5

Evaluar f(x) en los puntos:
x0 = 0
x1 = 0.5
x2 = 1.0
x3 = 1.5
x4 = 2.0
x5 = 2.5
x6 = 3.0

Calcular cada f(x):

f(0) = 0^3 + 2*0 + 1 = 1

f(0.5) = (0.5)^3 + 2*0.5 + 1 = 1.625

f(1.0) = 1^3 + 2*1 + 1 = 4

f(1.5) = (1.5)^3 + 2*1.5 + 1 = 7.875

f(2.0) = 8 + 4 + 1 = 13

f(2.5) = 15.625 + 5 + 1 = 21.625

f(3.0) = 27 + 6 + 1 = 34

Clasificar:

f(a) + f(b) = f(0) + f(3) = 1 + 34 = 35

f(x_i) con i no múltiplo de 3: i = 1, 2, 4, 5
=> f(0.5), f(1.0), f(2.0), f(2.5) = 1.625 + 4 + 13 + 21.625 = 40.25
=> 3 * 40.25 = 120.75

f(x_i) con i múltiplo de 3: i = 3
=> f(1.5) = 7.875
=> 2 * 7.875 = 15.75

Suma total = 35 + 120.75 + 15.75 = 171.5

Resultado = (3 * 0.5 / 8) * 171.5 = 0.1875 * 171.5 = 32.71875

```


## Resultado de la integral:

La integral aproximada de f(x) = x^3 + 2x + 1 desde 0 hasta 3 es 32.71875 utilizando el método de Simpson 3/8 con 6 intervalos.



