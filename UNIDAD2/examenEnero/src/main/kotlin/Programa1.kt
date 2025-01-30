/*
Programa 1
Jose Aquilino Covián Ornia
 */
data class Contacto(val nombre: String, val telefono: String)

class AgendaTelefonica(private val maxContactos: Int = 10) {
    private val contactos = mutableListOf<Contacto>()

    // Agregar un contacto
    fun agregarContacto(contacto: Contacto): Boolean {
        if (contactos.size >= maxContactos) {
            println("La agenda está llena. No se puede agregar más contactos.")
            return false
        }
        if (contactos.any { it.nombre == contacto.nombre }) {
            println("Ya existe un contacto con el nombre '${contacto.nombre}'.")
            return false
        }
        contactos.add(contacto)
        println("Contacto '${contacto.nombre}' agregado con éxito.")
        return true
    }

    // Eliminar un contacto
    fun eliminarContacto(nombre: String): Boolean {
        val contactoEliminar = contactos.find { it.nombre == nombre }
        return if (contactoEliminar != null) {
            contactos.remove(contactoEliminar)
            println("Contacto '${nombre}' eliminado con éxito.")
            true
        } else {
            println("No se encontró un contacto con el nombre '$nombre'.")
            false
        }
    }

    // Buscar un contacto por nombre
    fun buscarContacto(nombre: String): Contacto? {
        return contactos.find { it.nombre == nombre }
    }

    // Mostrar todos los contactos
    fun mostrarContactos() {
        if (contactos.isEmpty()) {
            println("No hay contactos que mostrar.")
        } else {
            println("Agenda telefónica:")
            contactos.forEach { println("${it.nombre}: ${it.telefono}") }
        }
    }

    // Comprobar existencia de un contacto NO FUNCIONA
    fun existeContacto(agenda: AgendaTelefonica, nombre: String): Boolean {
        val contactoBuscado = agenda.buscarContacto(nombre)
        //return if (contactoBuscado != null) true else false
        if (contactoBuscado != null) {
            return true
        } else {
            return false
        }
    }

    // Comprobar huecos libres
    fun existenHuecos() {

    }

    // Mostra menú por pantalla
    fun menu() {
        println("**** MENU AGENDA ***")
        println("1. Añadir Contacto")
        println("2. Listar contactos")
        println("3. Buscar contacto")
        println("4. Existe contacto")
        println("5. Eliminar contacto")
        println("6. Contactos disponibles")
        println("7. Agenda llena")
        println("8. Salir")
        println("*********************")
    }
}

fun main() {
    val agenda = AgendaTelefonica()
    var salir = false
    var nombre = ""
    var telefono = ""
    while (!salir) {
        agenda.menu()
        var opc = readln().toInt()
        when (opc) {
            1 -> {
                println("Introduzca el nombre: ")
                nombre = readln()
                println("Introduzca número de teléfono: ")
                telefono = readln()
                agenda.agregarContacto(Contacto(nombre, telefono))
            }
            2 -> agenda.mostrarContactos()
            3 -> {
                println("Introduzca nombre: ")
                val contactoBuscado = agenda.buscarContacto(readln())
                if (contactoBuscado != null) {
                    println("Contacto encontrado: ${contactoBuscado.nombre}, telefono: ${contactoBuscado.telefono}")
                } else {
                    println("No se encontró el contacto.")
                }
            }
            4 -> {
                println("Introduzca nombre: ")
                if(agenda.existeContacto(agenda, readln())) "Existe contacto" else "No existe contacto"
            }
            5 -> {
                println("Introduzca nombre: ")
                agenda.eliminarContacto(readln())
            }
            6 -> print("")
            7 -> print("")
            8 -> salir = true
            else -> println("Esa opción no existe")
        }
    }
}
