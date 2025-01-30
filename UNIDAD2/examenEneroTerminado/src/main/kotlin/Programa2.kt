/*
Programa 2
Jose Aquilino Covián Ornia
 */

fun main() {
    for (i in 1..100) {
        when {
            i % 3 == 0 && i % 5 == 0 -> println("triqui")  // Múltiplos de 3 y 5
            i % 3 == 0 -> println("tri")  // Múltiplos de 3
            i % 5 == 0 -> println("qui")  // Múltiplos de 5
            else -> println(i)  // Demás números
        }
    }
}