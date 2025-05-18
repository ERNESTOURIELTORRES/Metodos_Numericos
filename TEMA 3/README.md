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


