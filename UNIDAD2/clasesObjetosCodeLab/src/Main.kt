import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class SmartDevices(val name: String, val category: String) {

    val deviceStatus = "online"

    fun turnOn() {
        //println("Dispositivo encendido")
        println("encendido")
    }
    fun turnOff() {
        //println("Dispositivo apagado")
        println("apagado")

    }
}

class SmartDevices2(val name: String, val category: String) {
    var deviceStatus = "online"

    constructor(name: String, category: String, statusCode: Int) : this(name, category) {
        deviceStatus = when (statusCode) {
            0 -> "apagado"
            1 -> "encendido"
            else -> "desconocido"
        }
    }
}

open class SmartDevices3(val name: String, val category: String) {
    var deviceStatus = "online"
        protected set

    open val deviceType = "desconocido"

    open fun turnOn() {
        println("Dispositivo encendido")
    }
    open fun turnOff() {
        println("Dispositivo apagado")
    }
}

class SmartTvDevice(deviceName: String, deviceCategory: String) : SmartDevices3(name = deviceName, category = deviceCategory) {
    override val deviceType = "Smart TV"

    /*private var speakerVolume = 2
        set(value) {
            if(value in 0..100) {
                field = value
            }
        }*/
    private var speakerVolume by RangeRegulator(initialValue = 2, minValue = 0, maxValue = 100)
    /*private var channelNumber = 1
        set(value) {
            if(value in 0..200) {
                field = value
            }
        }*/
    private var channelNumber by RangeRegulator(initialValue = 1, minValue = 0, maxValue = 200)
    fun increaseSpeakerVolume() {
        speakerVolume++
        println("El volúmen ha sido incrementado a $speakerVolume")
    }
    fun nextChannel() {
        channelNumber++
        println("Número de canal incrementado al $channelNumber")
    }
    override fun turnOn() {
        //deviceStatus = "on"
        super.turnOn()
        println("$name está encendido. El volumen está en $speakerVolume y en canal es el $channelNumber")
    }
    override fun turnOff() {
        //deviceStatus = "off"
        super.turnOff()
        println("$name está apagado.")
    }
}

class SmartLightDevice(deviceName: String, deviceCategory: String) : SmartDevices3(name = deviceName, category = deviceCategory) {
    override val deviceType = "Smart Light"

    /*private var brightnessLevel = 0
        set(value) {
            if(value in 0..100){
                field = value
            }
        }*/
    private var brightnessLevel by RangeRegulator(initialValue = 0, minValue = 0, maxValue = 100)
    fun increaseBrightness(){
        brightnessLevel++
        println("Brillo incrementado a $brightnessLevel")
    }
    override fun turnOn(){
        //deviceStatus = "on"
        super.turnOn()
        brightnessLevel = 2
        println("$name encendido. El brillo está a $brightnessLevel")
    }
    override fun turnOff(){
        //deviceStatus = "off"
        super.turnOff()
        brightnessLevel = 0
        println("$name apagado. El brillo esá a $brightnessLevel")
    }
}

//La clase SmartHome HAS-A dispositivo smart TV
class SmartHome(
    val smartTvDevice: SmartTvDevice,
    val smartLightDevice: SmartLightDevice
) {
    var deviceTurnOnCount = 0
        private set
    fun turnOnTv(){
        deviceTurnOnCount++
        smartTvDevice.turnOn()
    }
    fun turnOffTv(){
        deviceTurnOnCount--
        smartTvDevice.turnOff()
    }
    fun increaseVolume(){
        smartTvDevice.increaseSpeakerVolume()
    }
    fun changeTvChannelToNext() {
        smartTvDevice.nextChannel()
    }
    fun turnOnLight(){
        deviceTurnOnCount++
        smartLightDevice.turnOn()
    }
    fun turnOffLight() {
        deviceTurnOnCount--
        smartLightDevice.turnOff()
    }
    fun increaseLightBrigtness() {
        smartLightDevice.increaseBrightness()
    }
    fun turnOffAllDevices() {
        turnOffTv()
        turnOffLight()
    }
}

class RangeRegulator(
    initialValue: Int,
    private val minValue: Int,
    private val maxValue: Int
): ReadWriteProperty<Any?, Int>{

    var fielData = initialValue

    override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return fielData
    }
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        if (value in minValue..maxValue) {
            fielData = value
        }
    }
}

fun main() {
    val smartDevice = SmartDevices("Android TV", "Entretenimiento")
    println("Nombre del dispositivo: ${smartDevice.name}")
    smartDevice.turnOn()
    smartDevice.turnOff()
    var dispositivoInteligente: SmartDevices3 = SmartTvDevice("Android Tv", "Entretenimiento")
    dispositivoInteligente.turnOn()
    dispositivoInteligente = SmartLightDevice("Google Light", "Utility")
    dispositivoInteligente.turnOn()
}