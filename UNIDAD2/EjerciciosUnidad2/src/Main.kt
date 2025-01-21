import kotlin.concurrent.thread

fun main() {
    println("Introduzca número de notifiacaciones: ")
    var datoInt: Int = readln().toInt()
    var datoString = readln()
    ejercicio1(datoInt)
    println("Introduzca edad: ")
    datoInt = readln().toInt()
    println("Introduzca día de la semana: ")
    datoString = readln()
    ejercicio2(datoInt, datoString)
    println("Introduzca temperatura: ")
    var datoFloat = readln().toFloat()
    ejercicio3(datoFloat)
    convertirTemperatura(27.0, "Celsius", "Fahrenheit", {9.0 / 5.0 * it + 32})
    convertirTemperatura(350.0, "Kelvin", "Celsius", {it - 273.15})
    convertirTemperatura(10.0, "Fahrenheit", "Kelvin", {5.0 / 9.0 * (it -32) + 273.15})

    funcionLambda()
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

fun ejercicio3(temperatura: Float){
    var resultado = String.format("%.2f", ((9.0 * temperatura) / 5) + 32)
    println("$temperatura grados Celsius son $resultado grados Fahrenheit")
    resultado = String.format("%.2f", temperatura - 273.15)
    println("$temperatura grados Kelvin son $resultado grados Celsius")
    resultado = String.format("%.2f", ((5.0 * (temperatura - 32)) / 9) + 273.15)
    println("$temperatura grados Fahrenheit son $resultado grados Kelvin")
}

fun convertirTemperatura(medidaInicial: Double, escalaInicial: String, escalaFinal: String, formulaConversion: (Double) -> Double) {

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