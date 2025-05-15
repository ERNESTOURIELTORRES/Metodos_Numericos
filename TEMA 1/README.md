#  Investigación de Errores y Desbordamiento de Números

Este repositorio contiene apuntes y conceptos clave sobre los **errores en cálculos numéricos** y los **desbordamientos de números** en herramientas como **Excel** y lenguajes de programación como **Java**. Es útil para estudiantes de ciencias, matemáticas, ingeniería y programación.

---

##  Contenido

###  Desbordamiento de Números

####  En Excel
- Al introducir números muy pequeños (por ejemplo, `0.0000000000000000000000001`), Excel redondea automáticamente a `0`.
- Esto se debe a las **limitaciones de precisión decimal** del programa.

#### En Java
- Cada tipo de dato tiene un límite. Por ejemplo:
  - `byte`: puede almacenar de **-128 a 127**.
  - Al intentar almacenar un valor fuera de este rango, se produce un **desbordamiento**.
- Solución: usar tipos más grandes como `int` o `long`.

---

###  Tipos de Errores Numéricos

####  Error Absoluto
- Diferencia directa entre valor real y valor aproximado.
- **Fórmula:**  
  `Error Absoluto = |Valor real - Valor aproximado|`

####  Error Relativo
- Proporción del error absoluto con respecto al valor real.
- **Fórmula:**  
  `Error Relativo = Error absoluto / Valor real`

####  Error de Redondeo
- Ocurre al redondear por límites en la representación decimal.
- Ejemplo: calculadoras o software que no representan todos los dígitos.

####  Error de Truncamiento
- Proviene de interrumpir una operación matemática infinita (como una serie) y usar solo una parte. 

####  Error de Propagación
- Acumulación de errores pequeños durante una serie de operaciones.

####  Error Sistemático
- Se repite constantemente debido a fallos en instrumentos o métodos.
- Ejemplo: balanza mal calibrada.

####  Error Aleatorio
- Impredecible y variable. Puede depender de factores externos o del observador.

---

##  Conclusión

Comprender los errores y los límites de representación numérica es esencial para:
- Tomar mejores decisiones al modelar o simular datos.
- Evitar fallos en cálculos científicos, financieros o computacionales.

---

##  Créditos

- Apuntes de clase y tarea sobre errores y desbordamiento.