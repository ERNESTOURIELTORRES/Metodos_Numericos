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

El método de Gauss-Jordan es una extensión del método de Gauss que permite resolver sistemas de ecuaciones lineales transformando la matriz aumentada en una matriz identidad. 
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

