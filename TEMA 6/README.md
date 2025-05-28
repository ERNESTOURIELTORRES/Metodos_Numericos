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


---

## Pasos del método



---

## Pseudocódigo del Interpolación Lineal



---

## Caso de Prueba


### Resultado esperado


