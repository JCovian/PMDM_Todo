open class Bebida(val id: Int = 0, val cantidad: Double = 0.0, var precio: Double = 0.0, val marca: String = "") {
    override fun toString(): String {
        if (this.id != 0) {
            return "Id: $id, Cantidad: $cantidad, Precio: $precio, Marca: $marca"
        } else {
            return ""
        }
    }
}

class BebidaAzucarada(id: Int, cantidad: Double, precio: Double, marca: String, val porcentajeAzucar: Double, val promocion: Boolean) :
    Bebida(id, cantidad, precio, marca) {
    init {
        if (this.promocion) {
            this.precio *= 0.9
        }
    }

    override fun toString(): String {
        if (super.toString() != "") {
            return super.toString() + ", Promoción: $promocion"
        } else {
            return ""
        }
    }
}

class AguaMineral(id: Int, cantidad: Double, precio: Double, marca: String, val origen: String) :
    Bebida(id, cantidad, precio, marca) {
    override fun toString(): String {
        if (super.toString() != "") {
            return super.toString() + ", Origen: $origen"
        } else {
            return ""
        }
    }
}

class Almacen(var estanteria: ArrayList<ArrayList<Bebida>> = arrayListOf<ArrayList<Bebida>>()) {
    init {
        if (this.estanteria.none()) {
            for (fila in (0..4)) {
                val columna: ArrayList<Bebida> = arrayListOf<Bebida>()
                this.estanteria.add(columna)
            }
        }
    }

    fun agregarBebida(bebida: Bebida) {
        var encontrado = false
        var i = 0
        while (i < this.estanteria.size && !encontrado) {
            var j = 0
            while (j < 5 && !encontrado) {
                if (this.estanteria.get(i).size < 5) {
                    if (this.estanteria.get(i).size == 0 || !this.bebidaRepetida(bebida)) {
                        this.estanteria.get(i).add(bebida)
                        encontrado = true
                    }
                }
                j++
            }
            i++
        }
        if (encontrado) {
            println("Bebida añadida")
        } else {
            println("No se ha podido añadir la bebida")
        }
    }

    private fun bebidaRepetida(bebida: Bebida): Boolean {
        var repetida = false
        for (i in (0..this.estanteria.size - 1)) {
            for (j in (0..this.estanteria.get(i).size - 1)) {
                if (this.estanteria.get(i).get(j) == bebida) {
                    repetida = true
                    break
                }
            }
            if (repetida) {
                break
            }
        }
        return repetida
    }

    fun eliminarBebida(id: Int) {
        var encontrada = false
        for (fila in (0..this.estanteria.size - 1)) {
            var identificadores = this.estanteria.get(fila).filter { it.id == id }
            if (!identificadores.none() && identificadores.size == 1) {
                this.estanteria.get(fila).remove(identificadores.elementAt(0))
                encontrada = true
            }
        }

        if (encontrada) {
            println("Bebida eliminada")
        } else {
            println("No existe la bebida")
        }
    }

    fun mostrarInformacionBebidas() {
        if (this.estanteria.none()) {
            println("La estantería está vacía")
        } else {
            for (i in (0..this.estanteria.size - 1)) {
                for (j in (0..this.estanteria.get(i).size - 1)) {
                    println(this.estanteria.get(i).get(j).toString())
                }
            }
        }
    }

    fun calcularPrecio(): Double {
        var precioTotal: Double = 0.0
        for (i in (0..this.estanteria.size - 1)) {
            for (j in (0..this.estanteria.get(i).size - 1)) {
                precioTotal += this.estanteria.get(i).get(j).precio
            }
        }
        return precioTotal
    }

    fun calcularPrecio(marca: String): Double {
        var bebidasTotal: ArrayList<Bebida> = arrayListOf<Bebida>()
        for (fila in (0..4)) {
            var listaBebidas =
                this.estanteria.get(fila).filter { it.marca.trim().lowercase() == marca.trim().lowercase() }
            bebidasTotal.addAll(listaBebidas)
        }
        return this.calcularPrecioListaBebidas(bebidasTotal)
    }

    private fun calcularPrecioListaBebidas(lista: ArrayList<Bebida>): Double {
        var precioTotal: Double = 0.0
        lista.forEach {
            precioTotal += it.precio
        }
        return precioTotal
    }

    fun calcularPrecio(columna: Int): Double {
        var precioTotal: Double = 0.0
        if (columna in (0..4)) {
            for (fila in (0..this.estanteria.size - 1)) {
                if (!this.estanteria.get(fila).none()) {
                    precioTotal += this.estanteria.get(fila).get(columna).precio;
                }
            }
        } else {
            println("La columna debe estar entre 0 y 4")
        }
        return precioTotal
    }
}

private fun main(){
    var almacen = Almacen()
    var identificador = 0
    for (i in (0..9)){
        identificador = i + 1
        when(i%2){
            0 -> {
                val bebida = AguaMineral(identificador, 1.5, 2.75, "bezoya", "manantial");
                almacen.agregarBebida(bebida)
            }
            1 -> {
                val bebida = BebidaAzucarada(identificador, 0.33, 1.5, "Coca Cola", 10.0, true);
                almacen.agregarBebida(bebida)
            }
        }
    }
    almacen.mostrarInformacionBebidas()
    println("Precio de todas las bebidas: " + almacen.calcularPrecio())
    almacen.eliminarBebida(4)
    almacen.mostrarInformacionBebidas()
    println("Precio de todas las bebidas: " + almacen.calcularPrecio())
    println("Precio de todas las bebidas de la marca bezoya: " + almacen.calcularPrecio("bezoya"))
    println("Calcular el precio de la columna 0: " + almacen.calcularPrecio(0))
}