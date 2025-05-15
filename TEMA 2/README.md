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

### Caso de Prueba


Función: f(x) = x^3 - x - 2

Intervalo: a = 1, b = 2

f(1) = -2
f(2) = 4
f(1) * f(2) = -8 < 0 → Hay cambio de signo → Se puede aplicar el método de bisección.

Tolerancia: 0.0001

Resultado esperado:
Después de varias iteraciones, la raíz aproximada encontrada es:
1.5214
---

# Método de la Regla Falsa

---

##  ¿En qué consiste?

La **Regla Falsa** (o método de la **falsa posición**) es un método numérico para encontrar raíces de funciones continuas en un intervalo [a, b] donde ocurre un cambio de signo, es decir, **f(a)·f(b) < 0**. A diferencia del método de bisección, **la fórmula para la raíz no divide el intervalo por la mitad**, sino que calcula un punto donde se cruza la recta que une los extremos del intervalo (una especie de interpolación lineal).

**c = a - [f(a) * (b - a)] / [f(b) - f(a)]**


---

## 🔢 Pasos del Método

1. Verifica que la función sea continua en el intervalo [a, b] y que `f(a) * f(b) < 0`.
2. Calcula el punto `c` usando la fórmula de la regla falsa.
3. Evalúa `f(c)`:
   - Si `f(c) = 0`, entonces `c` es la raíz exacta.
   - Si `f(a) * f(c) < 0`, actualiza `b = c`.
   - Si `f(b) * f(c) < 0`, actualiza `a = c`.
4. Repite el proceso hasta que el valor absoluto de `f(c)` sea menor a una tolerancia establecida.
5. El valor de `c` será la **raíz aproximada**.

---

## 🧠 Pseudocódigo del Método de la Regla Falsa

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

### Caso de prueba 
Función: f(x) = x^3 - x - 2

Intervalo inicial:
a = 1
b = 2

Evaluaciones iniciales:
f(1) = -2
f(2) = 4
f(1) * f(2) = -8 < 0 → Hay cambio de signo → ✅ Se puede aplicar la Regla Falsa.

Tolerancia:
0.0001

Resultado esperado:
Después de varias iteraciones, la raíz aproximada encontrada es:
1.5214
---