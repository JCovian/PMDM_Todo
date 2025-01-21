/*Crea la clase ‘Cuenta’ con los siguientes atributos:
Titular: obligatorio.
Cantidad: opcional.
Crea dos constructores que cumplan lo anterior.
Tendrá dos métodos:
Ingresar: se ingresa una cantidad a la cuenta. Si la cantidad introducida es negativa, no se hará nada.
Retirar: se retira una cantidad de la cuenta. Si la cantidad después de la retirada de efectivo es negativa,
la cantidad de la cuenta pasa a ser 0.
*/

data class Cuenta(val titular: String, var cantidad: Float){
    constructor(tit: String) : this(tit, 0f)

    fun ingresar(cantidad: Float) {
        if (cantidad > 0) {
            this.cantidad += cantidad
        }
    }

    fun retirar(cantidad: Float) {
        var saldo = this.cantidad - cantidad
        if (saldo < 0) {
            saldo = 0f
        }
    }
}

class Main {

}