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
