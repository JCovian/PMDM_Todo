// Anagrama es cuando dos palabras diferentes se escriben con diferentes letras
// App que comprueba si dos palabras son anagramas

fun main() {
    ejercicio6()
}

private fun ejercicio6() {
    print("Introduzca una palabra: ")
    val palabra1 = readln()
    print("Introduzca otra palabra: ")
    val palabra2 = readln()
    println("¿Las palabras $palabra1 y $palabra2 son un anagrama? = " + esAnagrama(palabra1, palabra2))
}

private fun esAnagrama(palabra1: String, palabra2: String): Boolean {
    if (palabra1.lowercase() == palabra2.lowercase()) {
        return false
    }
    return palabra1.lowercase().toCharArray().sortedArray().contentEquals(palabra2.lowercase().toCharArray().sortedArray())
}