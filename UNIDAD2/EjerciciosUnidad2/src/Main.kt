import kotlin.concurrent.thread

fun main() {
    //Ejercicio 1
    println("*** EJERCICIO 1 ***")
    println("Introduzca número de notifiacaciones: ")
    var datoInt: Int = readln().toInt()
    ejercicio1(datoInt)

    //Ejercicio 2
    println("*** EJERCICIO 2 ***")
    println("Introduzca edad: ")
    datoInt = readln().toInt()
    println("Introduzca día de la semana: ")
    var datoString = readln()
    ejercicio2(datoInt, datoString)

    //Ejercicio 3
    println("*** EJERCICIO 3 ***")
    ejercicio3(27.0, "Celsius", "Fahrenheit")
    ejercicio3(350.0, "Kelvin", "Celsius")
    ejercicio3(10.0, "Fahrenheit", "Kelvin")

    //Ejercicio 3 con entrada estandard
    println("*** EJERCICIO 3 ENTRADA ESTANDARD ***")
    println("Temperatura")
    val valorInicial = readln().toDouble()
    println("Escala inicial")
    val escalaInicial = readln()
    println("Escala final")
    val escalaFinal = readln()
    convertirTemperatura(valorInicial, escalaInicial, escalaFinal)

    //Ejercicio 3 con lambda
    println("*** EJERCICIO 3 LAMBDA ***")
    convertirTemperaturaLambda(27.0, "Celsius", "Fahrenheit", { 9.0 / 5.0 * it + 32 })
    convertirTemperaturaLambda(350.0, "Kelvin", "Celsius", {it - 273.15})
    convertirTemperaturaLambda(10.0, "Fahrenheit", "Kelvin", {5.0 / 9.0 * (it -32) + 273.15})

    //funcionLambda()
}

fun ejercicio1(notificaciones: Int) {
    val mensaje = when(notificaciones){
        in -2147483647..-1 -> "ERROR! Valor fuera de rango"
        in 0..99 -> "Tienes $notificaciones notificaciones"
        !in 0..99 -> "¡Tu teléfono está explotando! Tienes 99+ notificaciones"
        else -> "No tienes notificaciones"
    }
    println(mensaje)
}

fun ejercicio2(edad: Int, diaSemana: String){
    val precioInfantil: Double = 5.4
    val precioGeneral: Double = 8.5
    val precioGeneralDto: Double = 6.7
    val precioSenior: Double = 5.0

    val precio = when(edad) {
        in 0..12 -> precioInfantil
        in 13..60 -> {
            if (diaSemana!="Lunes") precioGeneral else precioGeneralDto
        }
        in 61..100 -> precioSenior
        else -> -1
    }
    if (precio == -1) {
        println("Precio no válido para esa edad")
    }else {
        println("El precio de la entrada al cine para una persona de $edad años el $diaSemana es de $precio euros.")
    }
}

fun ejercicio3(medidaInicial: Double, escalaInicial: String, escalaFinal: String){
    var medidaFinal: Double = when (escalaInicial) {
        "Celsius" -> 9.0 / 5.0 * medidaInicial + 32
        "Kelvin" -> medidaInicial - 273.15
        else -> 5.0 / 9.0 * (medidaInicial - 32) + 273.15
    }
    var medidaFinalString = String.format("%2f", medidaFinal)
    println("$medidaInicial grados $escalaInicial son $medidaFinalString grados $escalaFinal")
}

fun convertirTemperatura(medidaInicial: Double, escalaInicial: String, escalaFinal: String){
    var medidaFinal: Double = when (escalaInicial) {
        "Celsius" -> 9.0 / 5.0 * medidaInicial + 32
        "Kelvin" -> medidaInicial - 273.15
        "Fahrenheit" -> 5.0 / 9.0 * (medidaInicial - 32) + 273.15
        else -> 999.99
    }
    var medidaFinalString = String.format("%2f", medidaFinal)
    println("$medidaInicial grados $escalaInicial son $medidaFinalString grados $escalaFinal")
}

fun convertirTemperaturaLambda(medidaInicial: Double, escalaInicial: String, escalaFinal: String, formulaConversion: (Double) -> Double) {
    val medidaFinalString = String.format("%.2f", formulaConversion(medidaInicial))
    println("$medidaInicial grados $escalaInicial son $medidaFinalString grados $escalaFinal")
}

private fun funcionLambda() {
    miFuncionAsincrona("Patricia", {
        println(it)
    })

    var i = 0
    while (i < 20) {
        println("Primer mensaje por pantalla")
        println("Segundo mensaje por pantalla")
        println("Tercer mensaje por pantalla")
    }
}

private fun miFuncionAsincrona(nombre: String, funcionHola: (String) -> Unit) {
    val miNombre = "Hola $nombre!!"
    thread {
        Thread.sleep(0,1)
        funcionHola(miNombre)
    }
}