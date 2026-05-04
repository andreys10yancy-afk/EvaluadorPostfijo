/**
 * Clase: EvaluadorPostfijo
 *
 * Evalúa expresiones aritméticas en notación postfija (RPN).
 * En RPN los operadores van después de sus operandos, lo que
 * elimina la necesidad de paréntesis y simplifica la evaluación
 * mediante una pila.
 *
 * Ejemplo: "23*54*+9-" equivale a (2×3)+(5×4)−9 = 17
 *
 * Restricciones:
 *   - Solo acepta dígitos individuales (0–9).
 *   - Operadores soportados: +  -  *  /
 *   - La división es entera (trunca hacia cero).
 */
import java.util.Stack;

public class EvaluadorPostfijo {

    /**
     * Evalúa una expresión postfija y devuelve su resultado entero.
     *
     * Algoritmo:
     *   1. Recorrer cada carácter de la expresión.
     *   2. Si es dígito → convertirlo a entero y apilarlo.
     *   3. Si es operador → desapilar dos operandos, operar y apilar el resultado.
     *   4. Al terminar, la pila debe contener exactamente un elemento: el resultado.
     *
     * @param expresion  Cadena con la expresión en notación postfija.
     * @return           Resultado entero de la evaluación.
     * @throws IllegalArgumentException  Si la expresión es inválida (pocos operandos,
     *                                   carácter no reconocido o pila mal formada).
     */
    public static int evaluar(String expresion) {

        // Pila que almacena los operandos durante la evaluación
        Stack<Integer> pila = new Stack<>();

        // Recorremos la expresión carácter por carácter
        for (int i = 0; i < expresion.length(); i++) {
            char c = expresion.charAt(i);

            if (Character.isDigit(c)) {
                // Convertimos el carácter dígito a su valor numérico entero.
                // Ejemplo: '5' - '0' = 53 - 48 = 5
                pila.push(c - '0');

            } else if (c == '+' || c == '-' || c == '*' || c == '/') {

                // Verificamos que haya al menos dos operandos disponibles en la pila
                if (pila.size() < 2) {
                    throw new IllegalArgumentException(
                        "Expresión inválida: no hay suficientes operandos para el operador '" + c + "'"
                    );
                }

                // Desapilamos en orden inverso: 'b' sale primero (tope),
                // 'a' sale después. El orden importa en resta y división.
                int b = pila.pop(); // segundo operando (tope de la pila)
                int a = pila.pop(); // primer operando (debajo del tope)

                // Aplicamos la operación y apilamos el resultado
                int resultado = operar(a, b, c);
                pila.push(resultado);

            } else {
                // Carácter inesperado: ni dígito ni operador válido
                throw new IllegalArgumentException(
                    "Carácter no reconocido en la expresión: '" + c + "'"
                );
            }
        }

        // Una expresión postfija bien formada deja exactamente un elemento en la pila
        if (pila.size() != 1) {
            throw new IllegalArgumentException(
                "Expresión inválida: quedaron " + pila.size() + " elementos en la pila."
            );
        }

        // El único elemento restante es el resultado final
        return pila.pop();
    }

    /**
     * Aplica un operador aritmético a dos operandos enteros.
     *
     * El orden es: a  op  b  (igual que en la expresión original).
     * Ejemplo: operar(9, 3, '-') → 9 - 3 = 6
     *
     * @param a   Primer operando (extraído segundo de la pila).
     * @param b   Segundo operando (extraído primero / tope de la pila).
     * @param op  Operador: '+', '-', '*' o '/'.
     * @return    Resultado entero de a op b.
     * @throws ArithmeticException       Si op es '/' y b es 0 (división entre cero).
     * @throws IllegalArgumentException  Si op no es un operador conocido.
     */
    private static int operar(int a, int b, char op) {
        switch (op) {
            case '+': return a + b;   // Suma
            case '-': return a - b;   // Resta
            case '*': return a * b;   // Multiplicación
            case '/':
                // Prevenimos la división entre cero antes de operar
                if (b == 0) throw new ArithmeticException("División entre cero.");
                return a / b;   // División entera (trunca hacia cero)
            default:
                throw new IllegalArgumentException("Operador desconocido: " + op);
        }
    }

    /**
     * Imprime en consola la traza paso a paso de la evaluación postfija.
     *
     * Muestra una tabla con cuatro columnas:
     *   Paso | Carácter | Acción realizada | Estado de la pila
     *
     * Útil para verificar manualmente la ejecución o para fines didácticos.
     * Nota: no realiza validaciones tan exhaustivas como evaluar(); puede lanzar
     * EmptyStackException si la expresión está mal formada.
     *
     * @param expresion  Cadena con la expresión en notación postfija.
     */
    public static void trazarEvaluacion(String expresion) {

        // Pila auxiliar usada solo para la traza visual
        Stack<Integer> pila = new Stack<>();

        // Encabezado de la tabla con formato de columnas fijas
        System.out.printf("%-5s %-10s %-20s %-15s%n",
            "Paso", "Carácter", "Acción", "Pila");
        System.out.println("-".repeat(55));

        int paso = 1; // Contador de pasos para la tabla

        for (char c : expresion.toCharArray()) {
            String accion;

            if (Character.isDigit(c)) {
                // Es un operando: se apila su valor numérico
                pila.push(c - '0');
                accion = "Push(" + (c - '0') + ")";

            } else {
                // Es un operador: desapilamos dos valores, operamos y apilamos el resultado
                int b = pila.pop(); // tope → segundo operando
                int a = pila.pop(); // siguiente → primer operando
                int res = operar(a, b, c);
                pila.push(res);

                // Descripción legible de la operación realizada en este paso
                accion = "Pop→" + a + " " + c + " " + b + "=" + res + " Push";
            }

            // Imprimimos la fila correspondiente al paso actual
            System.out.printf("%-5d %-10s %-20s %-15s%n",
                paso++, "'" + c + "'", accion, pila.toString());
        }

        // Al finalizar, la pila contiene únicamente el resultado
        System.out.println("\nResultado final: " + pila.pop());
    }

    /**
     * Punto de entrada del programa.
     *
     * Ejecuta un conjunto de casos de prueba predefinidos, compara cada
     * resultado obtenido con el valor esperado e imprime el veredicto.
     * Finalmente muestra la traza completa de la primera expresión.
     *
     * @param args  Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {

        // Expresiones postfijas de prueba
        String[] casos = {
            "23*54*+9-",   // (2×3)+(5×4)−9 = 17
            "231*+9-",    // 2+(3×1)−9 = -4
            "52/",         // 5÷2 = 2 (división entera)
            "93-2*",       // (9−3)×2 = 12
            "42+3*",       // (4+2)×3 = 18
        };

        // Resultados esperados para verificar la corrección de cada caso
        int[] esperados = {17, -4, 2, 12, 18};

        System.out.println("===== Evaluador de Expresiones Postfijas =====\n");

        // Iteramos sobre cada caso de prueba
        for (int i = 0; i < casos.length; i++) {
            try {
                int resultado = evaluar(casos[i]);

                // Comparamos el resultado obtenido con el esperado
                String estado = (resultado == esperados[i]) ? "✓ CORRECTO" : "✗ ERROR";

                System.out.printf(
                    "Expresión: %-15s → Resultado: %3d  (Esperado: %3d)  %s%n",
                    "\"" + casos[i] + "\"", resultado, esperados[i], estado
                );

            } catch (Exception e) {
                // Capturamos cualquier excepción y la mostramos sin abortar el programa
                System.out.printf(
                    "Expresión: %-15s → Excepción: %s%n",
                    "\"" + casos[i] + "\"", e.getMessage()
                );
            }
        }

        // Traza detallada de la primera expresión para visualizar el flujo de la pila
        System.out.println("\n=== Traza paso a paso: \"23*54*+9-\" ===");
        trazarEvaluacion("23*54*+9-");
    }
}