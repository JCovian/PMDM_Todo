fun main() {
    ejercicio8()
}

private fun ejercicio8() {
    println(comprobarCarrera(listOf(AccionAtleta.CORRER, AccionAtleta.SALTAR, AccionAtleta.CORRER, AccionAtleta.SALTAR, AccionAtleta.CORRER), "_|_|_"))
    println(comprobarCarrera(listOf(AccionAtleta.CORRER, AccionAtleta.CORRER, AccionAtleta.CORRER, AccionAtleta.SALTAR, AccionAtleta.CORRER), "_|_|_"))
    println(comprobarCarrera(listOf(AccionAtleta.CORRER, AccionAtleta.CORRER, AccionAtleta.SALTAR, AccionAtleta.SALTAR, AccionAtleta.CORRER), "_|_|_"))
    println(comprobarCarrera(listOf(AccionAtleta.CORRER, AccionAtleta.CORRER, AccionAtleta.SALTAR, AccionAtleta.SALTAR, AccionAtleta.CORRER), "_|_|_|_"))
    println(comprobarCarrera(listOf(AccionAtleta.CORRER, AccionAtleta.SALTAR, AccionAtleta.CORRER, AccionAtleta.SALTAR), "_|_|_"))
    println(comprobarCarrera(listOf(AccionAtleta.CORRER, AccionAtleta.SALTAR, AccionAtleta.CORRER, AccionAtleta.SALTAR, AccionAtleta.CORRER, AccionAtleta.SALTAR, AccionAtleta.CORRER), "_|_|_"))
    println(comprobarCarrera(listOf(AccionAtleta.SALTAR, AccionAtleta.SALTAR, AccionAtleta.SALTAR, AccionAtleta.SALTAR, AccionAtleta.SALTAR), "|||||"))
    println(comprobarCarrera(listOf(AccionAtleta.SALTAR, AccionAtleta.SALTAR, AccionAtleta.SALTAR, AccionAtleta.SALTAR, AccionAtleta.SALTAR), "||?||"))
}

private enum class AccionAtleta(val accion: String) {
    CORRER("_"),
    SALTAR("|")
}

private fun comprobarCarrera(listaAcciones: List<AccionAtleta>, carrera: String) : Boolean {

    val totalAcciones = if (listaAcciones.count() > carrera.count())  listaAcciones.count() else carrera.count()
    val minAcciones = if (listaAcciones.count() > carrera.count()) carrera.count() else listaAcciones.count()

    val partesCarrera = carrera.toList()

    var carreraAtleta = ""

    for (index in (0 until totalAcciones)) {
        carreraAtleta += if (index >= minAcciones) {
            "?"
        } else {
            val parte = partesCarrera[index]
            when(val estado = listaAcciones[index]) {
                AccionAtleta.CORRER -> if (parte.toString() == estado.accion) estado.accion else "/"
                AccionAtleta.SALTAR -> if (parte.toString() == estado.accion) estado.accion else "x"
            }
        }
    }

    print("$carreraAtleta	")

    return carrera == carreraAtleta
}