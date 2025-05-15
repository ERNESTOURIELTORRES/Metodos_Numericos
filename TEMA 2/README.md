##   Métodos Numéricos para Encontrar Raíces



Muchas veces, estas ecuaciones **no pueden resolverse algebraicamente**, por lo que se usan **métodos numéricos** que nos permiten obtener una **solución aproximada**, pero suficientemente precisa.

---

##  Método de Bisección

###  ¿Qué es?

El **método de bisección** es una técnica de búsqueda de raíces **por intervalos**, basada en el **Teorema del Valor Intermedio**, que establece que si una función continua cambia de signo en un intervalo `[a, b]`, entonces existe al menos una raíz en ese intervalo.

###  Pasos Generales

1. Se elige un intervalo `[a, b]` tal que `f(a)` y `f(b)` tengan signos opuestos.
2. Se calcula el punto medio:  
   `m = (a + b)/2`
3. Se evalúa `f(m)`:
   - Si `f(m) = 0`, se encontró la raíz exacta.
   - Si `f(m)` tiene el mismo signo que `f(a)`, se reemplaza `a = m`.
   - Si tiene el mismo signo que `f(b)`, se reemplaza `b = m`.
4. El proceso se repite hasta alcanzar una tolerancia aceptable o un número máximo de iteraciones.
