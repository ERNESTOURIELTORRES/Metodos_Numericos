# METODOS NUMERICOS

En este readme veremos los metodos siguientes : 
- Método de bisección 
- Método de la Regla Falsa
- Método de Punto Fijo
- Método de Newton-Raphson
- Método de Secante 



# Método de Bisección

El **Método de Bisección** es una técnica numérica usada para encontrar **raíces de ecuaciones no lineales**. Es uno de los métodos más simples y confiables, siempre que se cumplan ciertas condiciones.

---

##  ¿En qué consiste?

El método parte de un intervalo `[a, b]` donde se **garantiza que hay una raíz**, es decir, que la función cambia de signo:  
**f(a) · f(b) < 0**

Luego, se repite un proceso de división del intervalo hasta que se aproxima suficientemente a la raíz.

---

##  Pasos del Método

1. **Verifica** que `f(a) * f(b) < 0` (hay un cambio de signo → existe al menos una raíz en el intervalo).
2. Calcula el **punto medio** del intervalo:  
   `m = (a + b) / 2`
3. Evalúa `f(m)`:
   - Si `f(m) = 0`: has encontrado la raíz exacta.
   - Si `f(a) * f(m) < 0`: la raíz está entre `a` y `m` → actualiza `b = m`.
   - Si `f(m) * f(b) < 0`: la raíz está entre `m` y `b` → actualiza `a = m`.
4. **Repite** el proceso hasta que la **diferencia entre `a` y `b` sea menor a una tolerancia** deseada (es decir, hasta que la aproximación sea suficientemente precisa).
---


## Pseudocódigo del Método de Bisección

```plaintext
Inicio
  Definir función f(x):
    f(x) = x^3 - x - 2

  a ← 1
  b ← 2
  tolerancia ← 0.0001

  Si f(a) * f(b) ≥ 0 Entonces
    Mostrar "No se puede aplicar el método."
    Terminar

  Mientras (b - a) ≥ tolerancia hacer
    c ← (a + b) / 2

    Si f(c) = 0 Entonces
      Salir del ciclo (la raíz exacta fue encontrada)
    Sino si f(c) * f(a) < 0 Entonces
      b ← c
    Sino
      a ← c
  Fin Mientras

  Mostrar "La raíz aproximada es: ", (a + b) / 2
Fin
```

## Caso de Prueba


Función: f(x) = x^3 - x - 2

Intervalo: a = 1, b = 2

f(1) = -2
f(2) = 4
f(1) * f(2) = -8 < 0 → Hay cambio de signo → Se puede aplicar el método de bisección.

Tolerancia: 0.0001

Resultado esperado:
Después de varias iteraciones, la raíz aproximada encontrada es:
1.5214


# Método de la Regla Falsa

---

##  ¿En qué consiste?

La **Regla Falsa** (o método de la **falsa posición**) es un método numérico para encontrar raíces de funciones continuas en un intervalo [a, b] donde ocurre un cambio de signo, es decir, **f(a)·f(b) < 0**. A diferencia del método de bisección, **la fórmula para la raíz no divide el intervalo por la mitad**, sino que calcula un punto donde se cruza la recta que une los extremos del intervalo (una especie de interpolación lineal).

**c = a - [f(a) * (b - a)] / [f(b) - f(a)]**


---

##  Pasos del Método

1. Verifica que la función sea continua en el intervalo [a, b] y que `f(a) * f(b) < 0`.
2. Calcula el punto `c` usando la fórmula de la regla falsa.
3. Evalúa `f(c)`:
   - Si `f(c) = 0`, entonces `c` es la raíz exacta.
   - Si `f(a) * f(c) < 0`, actualiza `b = c`.
   - Si `f(b) * f(c) < 0`, actualiza `a = c`.
4. Repite el proceso hasta que el valor absoluto de `f(c)` sea menor a una tolerancia establecida.
5. El valor de `c` será la **raíz aproximada**.

---

##  Pseudocódigo del Método de la Regla Falsa

```plaintext
Inicio
  Definir función f(x):
    f(x) = x^3 - x - 2

  a ← 1
  b ← 2
  tolerancia ← 0.0001

  Si f(a) * f(b) ≥ 0 Entonces
    Mostrar "No se puede aplicar el método."
    Terminar

  Repetir
    c ← a - [f(a) * (b - a)] / [f(b) - f(a)]

    Si f(c) = 0 Entonces
      Salir del ciclo (raíz exacta encontrada)
    Sino si f(c) * f(a) < 0 Entonces
      b ← c
    Sino
      a ← c

  Mientras |f(c)| ≥ tolerancia

  Mostrar "La raíz aproximada es: ", c
Fin

```
---



## Caso de prueba 
Función: f(x) = x^3 - x - 2

Intervalo inicial:
a = 1
b = 2

Evaluaciones iniciales:
f(1) = -2
f(2) = 4
f(1) * f(2) = -8 < 0 → Hay cambio de signo →  Se puede aplicar la Regla Falsa.

Tolerancia:
0.0001

Resultado esperado:
Después de varias iteraciones, la raíz aproximada encontrada es:
1.5214 



# Método punto fijo

---

##  ¿En qué consiste?

El **método del Punto Fijo** es una técnica iterativa para encontrar raíces de ecuaciones no lineales. Se basa en reescribir la ecuación `f(x) = 0` en la forma `x = g(x)` y luego utilizar esa función `g(x)` para generar una sucesión que converge a una raíz de la ecuación original.

**\[x_{n+1} = g(x_n)\]**

La sucesión de valores \( x_0, x_1, x_2, \ldots \) se genera aplicando la función `g(x)` repetidamente.

---

##  Pasos del Método

1. Despeja la ecuación `f(x) = 0` en la forma `x = g(x)`.
2. Elige una aproximación inicial `x₀`.
3. Calcula `x₁ = g(x₀)`, `x₂ = g(x₁)`, etc.
4. Repite el proceso hasta que la diferencia entre dos iteraciones consecutivas sea menor a una **tolerancia** establecida:  
   \[
   |x_{n+1} - x_n| < \text{tolerancia}
   \]
5. El valor de `xₙ` se considera la **raíz aproximada**.

**Nota:** Para que el método funcione, la función `g(x)` debe cumplir ciertas condiciones de convergencia (por ejemplo, que \( |g'(x)| < 1 \)).

---

##  Pseudocódigo del Método de la Regla Falsa

```plaintext
Inicio
  Definir función g(x):
    g(x) = ∛(x + 2)

  x ← 1.5
  tolerancia ← 0.0001

  Repetir
    x1 ← g(x)
    
    Si |x1 - x| < tolerancia Entonces
      Salir del ciclo
    FinSi

    x ← x1
  Hasta que se cumpla la condición

  Mostrar "La raíz aproximada es: ", x1
Fin

```
---



## Caso de prueba 
Función original: f(x) = x^3 - x - 2

Despeje usado: x = ∛(x + 2) → g(x)

Valor inicial:
x = 1.5

Tolerancia:
0.0001

Iteraciones:
x₁ = ∛(1.5 + 2) = ∛(3.5) ≈ 1.518
x₂ = ∛(1.518 + 2) ≈ 1.5212
...
Se detiene cuando |xₙ₊₁ - xₙ| < 0.0001

Resultado esperado:
Después de varias iteraciones, la raíz aproximada encontrada es:
1.5214

# Método de Newton-Raphson

---

##  ¿En qué consiste?

El **método de Newton-Raphson** es una técnica iterativa para encontrar raíces de funciones reales. Utiliza tanto la función como su derivada para aproximar la raíz de manera rápida y eficiente.

**x_{n+1} = x_n - \frac{f(x_n)}{f'(x_n)}**

---

##  Pasos del Método

1. Elige una aproximación inicial \( x_0 \).
2. Calcula la siguiente aproximación usando la fórmula:
   \[
   x_{n+1} = x_n - \frac{f(x_n)}{f'(x_n)}
   \]
3. Repite el paso anterior hasta que:
   \[
   |x_{n+1} - x_n| < \text{tolerancia}
   \]
4. El valor de \( x_n \) se considera la raíz aproximada.

 **Nota:** Este método requiere que la derivada \( f'(x) \) no sea cero en el punto de evaluación.


---

##  Pseudocódigo del Método de la Regla Falsa

```plaintext
Inicio
  Definir función f(x):
    f(x) = x^3 - x - 2

  Definir derivada f'(x):
    f'(x) = 3x^2 - 1

  x ← 1.5
  tolerancia ← 0.0001

  Repetir
    h ← f(x) / f'(x)
    x ← x - h
  Hasta que |h| < tolerancia

  Mostrar "La raíz aproximada es: ", x
Fin

```
---



## Caso de prueba 
Función: f(x) = x^3 - x - 2
Derivada: f'(x) = 3x^2 - 1

Valor inicial:
x = 1.5

Tolerancia:
0.0001

Iteraciones:
h₀ = f(1.5)/f'(1.5) = (3.375 - 1.5 - 2) / (3*(1.5)^2 - 1)
   ≈ -0.142857
x₁ = 1.5 - h₀ ≈ 1.6429

Continuar iterando hasta que |h| < 0.0001...

Resultado esperado:
Después de varias iteraciones, la raíz aproximada encontrada es:
1.5214
