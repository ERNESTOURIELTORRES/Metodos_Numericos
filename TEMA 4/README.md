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

-- En la formulación general, se busca aproximar:

![Captura de pantalla 2025-05-20 201922](https://github.com/user-attachments/assets/b951aeb6-0ef6-4d5c-828b-2a5aa9445532)


-- Cambio de variable 

![Captura de pantalla 2025-05-20 202202](https://github.com/user-attachments/assets/d2861db7-4d32-49e0-9096-6c7f1d652a8b)

- \( f(x) \): Función a integrar.
- \( a \), \( b \): Límites de integración.
- \( n \): Número de puntos de Gauss (por ejemplo 2, 3, 4 o 5).
- \( \xi_i \): Puntos nodales en \([-1, 1]\), definidos por los ceros del polinomio de Legendre de grado \(n\).
- \( w_i \): Pesos asociados a cada punto \(\xi_i\).
- \( x_i \): Puntos transformados al intervalo original \([a, b]\).

---


## Pseudocódigo del Método de Cuadratura Gaussiana


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
