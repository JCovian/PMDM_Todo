fun main() {
    ejercicio7()
}

private fun ejercicio7() {
    println("{a + b [c] * (2x2)}}}} ¿está balanceada? " + expresionBalanceada("{a + b [c] * (2x2)}}}}"))
    println("{ [ a * ( c + d ) ] - 5 } ¿está balanceada? " + expresionBalanceada("{ [ a * ( c + d ) ] - 5 }"))
    println("{ a * ( c + d ) ] - 5 } ¿está balanceada? " + expresionBalanceada("{ a * ( c + d ) ] - 5 }"))
    println("{a^4 + (((ax4)} ¿está balanceada? " + expresionBalanceada("{a^4 + (((ax4)}"))
    println("{ ] a * ( c + d ) + ( 2 - 3 )[ - 5 } ¿está balanceada? " + expresionBalanceada("{ ] a * ( c + d ) + ( 2 - 3 )[ - 5 }"))
    println("{{{{{{(}}}}}} ¿está balanceada? " + expresionBalanceada("{{{{{{(}}}}}}"))
    println("(a ¿está balanceada? " + expresionBalanceada("(a"))
}

private fun expresionBalanceada(expresion: String): Boolean {

    val mapa = mapOf("{" to "}", "[" to "]", "(" to ")")
    val pila = arrayListOf<String>()

    expresion.forEach {
        val simbolo = it.toString()
        val contieneClave = mapa.containsKey(simbolo)

        if (contieneClave || mapa.containsValue(simbolo)) {
            if (contieneClave) {
                pila.add(simbolo)
            } else if (pila.isEmpty() || simbolo != mapa[pila.removeLast()]) {
                return false
            }
        }
    }

    return pila.isEmpty()
}