# Evaluador de Expresiones Postfijas

> Laboratorio de Estructuras de Datos — Universidad Autónoma del Caribe

---

## Descripción

Implementación en Java de un **evaluador de expresiones aritméticas en notación postfija** (Reverse Polish Notation / RPN). El programa recorre la expresión carácter por carácter y utiliza una **pila (`Stack`)** para gestionar los operandos, aplicando cada operador en el momento en que aparece.

**Ejemplo:**

| Notación infija | Notación postfija | Resultado |
|---|---|---|
| (2×3) + (5×4) − 9 | `23*54*+9-` | 17 |
| 2 + (3×1) − 9 | `231*+9-` | -4 |
| (4+2) × 3 | `42+3*` | 18 |

---

## Métodos Principales

| Método | Descripción |
|---|---|
| `evaluar(String)` | Evalúa la expresión postfija y retorna el resultado entero |
| `operar(int, int, char)` | Aplica un operador aritmético a dos operandos |
| `trazarEvaluacion(String)` | Imprime la traza paso a paso en consola |
| `main(String[])` | Ejecuta los casos de prueba predefinidos |

---

## Casos de Prueba

| Expresión | Equivalente infija | Esperado | Estado |
|---|---|---|---|
| `"23*54*+9-"` | (2×3)+(5×4)−9 | 17 | correcto |
| `"231*+9-"` | 2+(3×1)−9 | -4 | correcto |
| `"52/"` | 5÷2 | 2 | correcto |
| `"93-2*"` | (9−3)×2 | 12 | correcto |
| `"42+3*"` | (4+2)×3 | 18 | correcto |

---

## Cómo Ejecutar

### Requisitos
- Java Development Kit (JDK) 8 o superior

### Compilar y ejecutar

```bash
# Compilar
javac EvaluadorPostfijo.java

# Ejecutar
java EvaluadorPostfijo
```

---

##  Complejidad

| Aspecto | Valor |
|---|---|
| Complejidad temporal | O(n) — recorre la expresión una sola vez |
| Complejidad espacial | O(n) — en el peor caso todos los tokens son operandos |

---

## Limitaciones

- Solo acepta **dígitos individuales** (0–9); no soporta números de más de un dígito.
- No maneja **espacios en blanco** dentro de la expresión.
- La **división es entera** (trunca hacia cero).
- Operadores soportados: `+` `-` `*` `/`

---

## Video Explicativo

> [https://youtu.be/ckaH6U18jfQ?si=uFql6UCZ78ff9cLC](https://youtu.be/ckaH6U18jfQ?si=uFql6UCZ78ff9cLC)

---

## 👥 Integrantes

| Nombre |
|---|
| Jostin Hoyos |
| Mateo Pinto |
| Andreys Yancy |

---

## 📖 Referencias

- Cormen, T. H. et al. *Introduction to Algorithms* (4.ª ed.). MIT Press, 2022.
- Weiss, M. A. *Data Structures and Algorithm Analysis in Java* (3.ª ed.). Pearson, 2012.
- Oracle Java SE Docs — [`java.util.Stack`](https://docs.oracle.com/javase/8/docs/api/java/util/Stack.html)

---

*Laboratorio de Estructuras de Datos — Universidad Autónoma del Caribe*
