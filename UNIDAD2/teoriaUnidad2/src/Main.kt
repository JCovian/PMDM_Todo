import java.util.Scanner
import kotlin.concurrent.thread

fun main() {
    //tiposVariables()
    //variablesSinTipo()
    //variablesConTipo()
    //concatenacion()
    //obtenerNombreMesWhen(6)
    //obtenerNombreCuatrimestre(3)
    //obtenerNombreCuatrimestreVariable(7)
    //obtenerNombreCuatrimestrePrintln(10)
    //obtenerTipoVariable(15f)
    //entradaConScanner()
    //entradaReadLine()
    //arrays()
    //listasInmutables()
    //listasMutables()
    //mapasInmutables()
    //mapasMutables()
    //rangos()
    //nullsafety()
    //funcionLambda()
    //clases()
    //herencia()
    //enumerados()
    dataClases()
}

fun tiposVariables(){
    // Variables numéricas
    var numeroFavorito: Int =  -358
    var numeroLong: Long = 478558526
    var numeroFloat: Float = 1.98f
    var numeroDouble: Double = 1.98
    // Variables de texto
    var numeroChar: Char = '1'
    numeroChar = '@'
    numeroChar = ' '
    var cadena: String = "Una frase"
    // Variables booleanas
    var valorVerdadero: Boolean = true
    var valorFalso: Boolean = false
    // Anulación de variables
    var nombre: String? = null
}

fun variablesSinTipo(){
    var num1: Int = 10
    var num2: Int = 5
    print("Suma: ")
    println(num1 + num2)
    print("Resta: ")
    println(num1 - num2)
    print("Multimplicación: ")
    println(num1 * num2)
    print("División: ")
    println(num1 / num2)
    print("El módulo (resto): ")
    println(num1 % num2)
}

fun variablesConTipo(){
    var num1: Float = 10.5f
    var num2: Int = 5
    print("Suma: ")
    var resultado = num1 + num2
    println(resultado)
}

fun concatenacion(){
    val saludo: String = "Hola, me llamo"
    val nombre: String = "Jose"
    println("$saludo $nombre")
    val introduccion = "El resultado de"
    val plus = "más"
    val primerNumero: Int = 2
    val segundoNumero: Int = 5
    println("$introduccion $primerNumero $plus $segundoNumero es: ${primerNumero + segundoNumero}")
    println(introduccion + " " + primerNumero + " " + plus + " " + segundoNumero + " es: " + (primerNumero + segundoNumero))
}

fun obtenerNombreMesWhen(numeroMes: Int){
    when (numeroMes){
        1 -> println("Enero")
        2 -> println("Febrero")
        3 -> println("Marzo")
        4-> println("Abril")
        5 -> println("Mayo")
        6 -> println("Junio")
        7 -> println("Julio")
        8 -> println("Agosto")
        9 -> println("Septiembre")
        10 -> println("Octubre")
        11 -> println("Nombre")
        12 -> println("Diciembre")
        else -> {
            println("Introduzca un valor entre 1 y 12")
        }
    }
}

fun obtenerNombreCuatrimestre(numeroMes: Int){
    when(numeroMes){
        1, 2, 3, 4 -> println("Cuatrimestre - 1")
        in 5  ..  8 -> println("Cuatrimestre - 2")
        9, 10, 11, 12 -> println("Cuatrimestre - 3")
        !in (1 .. 12) -> println("Número de mes incorrecto")
    }
}

fun obtenerNombreCuatrimestreVariable(numeroMes: Int){
    val trimestre = when(numeroMes){
        1, 2, 3, 4 -> "Cuatrimestre - 1"
        in 5  ..  8 -> "Cuatrimestre - 2"
        9, 10, 11, 12 -> "Cuatrimestre - 3"
        !in (1 .. 12) -> "Número de mes incorrecto"
        else -> "Número de mes incorrecto"
    }
    println(trimestre)
}

fun obtenerNombreCuatrimestrePrintln(numeroMes: Int){
    println(when(numeroMes){
        1, 2, 3, 4 -> "Cuatrimestre - 1"
        in 5  ..  8 -> "Cuatrimestre - 2"
        9, 10, 11, 12 -> "Cuatrimestre - 3"
        !in (1 .. 12) -> "Número de mes incorrecto"
        else -> "Número de mes incorrecto"
    })
}

fun obtenerTipoVariable(valor: Any) {
    when(valor){
        is String -> println("Variable de tipo String")
        is Boolean -> println("Variable de tipo Boolean")
        !is Int -> println("Variable no es de tipo Integer")
    }
}

fun entradaConScanner(){
    //Inicializar Scanner
    val scanner = Scanner(System.`in`)

    //Leer una linea entera
    val linea = scanner.nextLine()
    println(linea)

    //Leer un String
    val string = scanner.next()
    println(string)

    //Leer un número
    val numero = scanner.nextInt()
    println(numero)
}

fun entradaReadLine(){
    //Leer un string
    println("Introduce nombre:")
    var nombre = readln()

    //Lee un string y lo convierte a entero
    println("Introduce edad:")
    val edad = readln().toInt()

    println("Hola, $nombre tienes $edad años")
}

fun arrays(){
    val semana = arrayOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo")
    println(semana.get(0))
    println(semana[1])
    //println(semana[7]) Produce excepcion
    semana.set(0, "L")
    println(semana[0])
    semana.set(7, "nuevo día") //-> produce excepcion
    //Recorrer por la posición
    for(posicion in semana.indices) {
        println(semana.get(posicion))
    }
    //Recorrer obteniendo posición y valor
    for ((posicion, valor) in semana.withIndex()){
        println("La posición  $posicion contiene el valor $valor")
    }
    //Recorrer obteniendo el valor
    for (valor in semana){
        println(valor)
    }
}

fun listasInmutables(){
    val listaDias: List<String> = listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo")
    println(listaDias.get(2))
    println("Tamaño de la lista = " + listaDias.size)
    println("Primer elemento: " + listaDias.first())
    println("Último elemento: " + listaDias.last())
}

fun listasMutables() {
    val listaDias: MutableList<String> = mutableListOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes")
    println(listaDias)
    //Otra forma de crear listas mutables
    val semana: ArrayList<String> = arrayListOf()
    listaDias.add("Domingo")
    println(listaDias)
    listaDias.add(5, "Sábado")
    println(listaDias)
    if (semana.none()) {
        println("Lista vacía")
    }
    println(semana.firstOrNull())
    println(listaDias.elementAtOrNull(4))
    //Obtener días de la semana que empiezan por M
    val resultado = listaDias.filter { it[0].lowercase() == 'M'.lowercase() }
    print(resultado)
    //Recorriendo listas con forEach
    val nuevaLista: MutableList<String> = mutableListOf()
    listaDias.forEach{nuevaLista.add(it + "-")}
    println(nuevaLista)
}

fun mapasInmutables(){
    val mapa = mapOf(1 to "uno", 2 to "dos", 3 to "tres")
    println(mapa.entries)
    println(mapa.keys)
    println(mapa.values)
    println(mapa.count())
    println(mapa[1])
    println(mapa.getValue(1))
}

fun mapasMutables() {
    val mapa = mutableMapOf<Int, String>()
    mapa.put(1, "uno")
    mapa.put(2, "dos")
    mapa.put(3, "tres")
    println(mapa)
    mapa.put(1,"one")
    println(mapa)
    for (numero in mapa) {
        println("${numero.key} - ${numero.value}")
    }
}

fun rangos() {
    for (x in 0..10) {
        println(x)
    }

    for (x in 0 until 10) {
        println(x)
    }

    for (x in 0..10 step 2) {
        println(x)
    }

    for (x in 10 downTo 0) {
        println(x)
    }

    var x = 0
    while (x < 10) {
        println(x)
        x++
        //break
    }
}

fun nullsafety() {
    var cadena: String? = "Esto es una cadena"
    cadena = null
    //Hay que añadir el interrogante para que sepa que acepta nulos
    println(cadena?.length)
    var cadena2: String? = "Esto es otra cadena"
    cadena2 = null
    cadena2.let { println(it) } ?: run {println(cadena2)}
}

fun funcionLambda() {
    //{parametro1: tipo, parametro2: tipo -> parametro1 + parametro2}
    val listaNumeros = arrayListOf(0, 1, 2, 3 , 4, 5, 6)
    val listaNumerosFiltrados = listaNumeros.filter { miEntero: Int ->
        if (miEntero == 1) {
            return@filter true
        }
        miEntero > 4
    }
    println(listaNumerosFiltrados)

    var resultado = {a: Int, b: Int -> a + b}
    println(resultado(2,3))
}

private fun funcionLambda2() {
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

private fun clases() {
    /*val persona1 = Contacto(1, "Sin correo")
    persona1.categoria = "Prueba"
    println("${persona1.id} - ${persona1.email} - ${persona1.categoria}")*/
    funcionClases()
}

private fun funcionClases(){
    val claseAnid = Principal.ClaseAnidada()
    val suma = claseAnid.sumar(5, 3)
    println("El resultado de la suma es $suma")
    val claseInterna = Principal().ClaseInterna()
    val sumarDos = claseInterna.sumarDos(8)
    println("El resultado de la suma es $sumarDos")
}

class Principal(){
    private val uno = 1

    class ClaseAnidada() {
        fun sumar( num1: Int, num2: Int): Int {
            return num1 + num2 //+ uno
        }
    }

    inner class ClaseInterna {
        fun sumarDos(num: Int): Int{
            return num + uno
        }
    }
}

class Contacto(val id: Int, val email: String){
    var categoria: String = ""
        get(){
            return "Categoria " + this.id
        }
        set(value){
            field = value
        }
}

private fun herencia() {
    val programador = Programador("Jose", 49, "Kotlin")
    programador.trabajar()
    programador.imprimirLenguaje()
    val diseinador = Diseniador("Dulce", 42)
    diseinador.trabajar()
    val persona3 = Persona("Sofian", 84)
    persona3.jugar()
    persona3.stream()
}

abstract class Trabajo() {
    abstract fun irATrabajar()
}

interface Juego{
    val juego: String
    fun jugar()
    fun stream() {
        println("Está jugado en streaming")
    }
}

open class Persona(nombre: String, edad: Int): Trabajo(), Juego {
    open fun trabajar(){
        println("Esta persona está trabajando")
    }

    override fun irATrabajar() {
        println("Esta persona va al trabajo")
    }

    //Interface
    override val juego: String = "Among Us"
    override fun jugar(){
        println("Está jugando al juego $juego")
    }
}

class Programador(val nombre: String, val edad: Int, val lenguaje: String): Persona(nombre, edad) {
    override fun trabajar() {
        println("Esta persona está programando")
    }
    fun imprimirLenguaje() {
        println("Este trabajador está programando en $lenguaje")
    }
}

class Diseniador(val nombre: String, val edad: Int): Persona(nombre, edad){
    override  fun trabajar(){
        println("Estre trabajador está diseñando")
        super.trabajar()
    }
}

private fun enumerados(){
    val dia = diasSemana.MIERCOLES
    println("nombre: " + diasSemana.LUNES.name)
    println("orden:" + diasSemana.LUNES.ordinal)
    println("letra:" + diasSemana.LUNES.letra)

    for(diaSemana in diasSemana.entries) {
        println(diaSemana.letra)
    }
}

enum class diasSemana(val letra: Char){
    LUNES('L'),
    MARTES('M'),
    MIERCOLES('X'),
    JUEVES('J'),
    VIERNES('V');

    fun getFinDeSemana(){
        println("Los dias que forman el fin de semana son el Sábado y el Domingo")
    }
}

interface LimiteTarjeta{
    fun obtenerLimiteCredito(): Int
}

enum class TipoTarjeta: LimiteTarjeta{
    PLATA {
        override fun obtenerLimiteCredito() = 1000
    },
    ORO {
        override fun obtenerLimiteCredito(): Int {
            return 2000
        }
    }
}

data class Usuario(var nombre: String = "Pepe", var id: Int = 1) {
    var edad: Int = 0
}

private fun dataClases(){
    val usuarioVacio = Usuario()
    val usuario = Usuario("Jose", 2)
    println(usuario.toString())
    var usuario2 = usuario.copy()
    usuario2.id = 3
    println(usuarioVacio)
    println(usuario.toString())
    println(usuario2.toString())
    println("El usuario vacío se llama ${usuarioVacio.nombre}")
    //val nombre = usuario.nombre
    //val identificador = usuario.id
    //val (nombre, identificador) = usuario
    val (nombre, _) = usuario //Utilizo el _ para saltar la propiedad que no nos interese
    //Kotlin permite acceder a los datos de la data class por su orden de componente
    //Solo se accede a los componentes que están dentro del constructor
    //También afecta a la hora de comparar objetos, solo tiene en cuenta los del constructor
    val identify = usuario.component2()
    //Comparacion de objetos
    var usu1 = Usuario("Pepito", 1)
    usu1.edad = 21
    var usu2 = Usuario()
    var usu3 = Usuario("Pepito", 1)
    usu3.edad = 5
    if (usu1 == usu2) {
        println("Usuarios iguales")
    }  else {
        println("Usuarios son distintos")
    }
    if (usu1 == usu3) {
        println("Usuarios iguales")
    }  else {
        println("Usuarios son distintos")
    }
}