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



