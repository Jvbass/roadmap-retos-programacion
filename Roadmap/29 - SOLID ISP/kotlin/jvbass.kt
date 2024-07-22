/*
* El Principio SOLID de Segregación de Interfaces se basa en que las clases que implementan interfaces
* sólo deben implementar métodos que sean utiles para esa clase, si no se da uso a un metodo que esta
* declarada en la interfaz se debe segregar en otra interfaz.
*
* En una fruteria, hay frutas que se venden por unidad y otras que se venden por kilo por lo tanto,
* el sistema informatico de la fruteria debe dar la posiblidad de calcular el precio de las frutas
* por peso o por unidad. El sistema debe devolver el valor de la fruta y el total.
* */

/****** Forma incorrecta *****/
//interfaz con todos los metodos para calcular el precio de las frutas
interface CalulatePrice {
    fun getPrice(): Double
    fun getTotalByKilo(Kilo: Double): Double
    fun getTotalByUnit(Unit: Double): Double
}

// La cereza se vende por kilo, por lo que el metodo getTotalByUnit no nos sirve
class Cereza : CalulatePrice {
    override fun getPrice(): Double {
        return 100.0
    }

    override fun getTotalByKilo(Kilo: Double): Double {
        return Kilo * getPrice()
    }

    // El metodo getTotalByUnit no sirve para calcular el precio de la cereza
    override fun getTotalByUnit(Unit: Double): Double {
        return Unit * getPrice()
    }
}

// El melon se vende por unidad, por lo que el metodo getTotalByKilo no nos sirve
class Melon : CalulatePrice {
    override fun getPrice(): Double {
        return 200.0
    }

    override fun getTotalByUnit(Unit: Double): Double {
        return Unit * getPrice()
    }

    // El metodo getTotalByKilo no sirve para calcular el precio del melon
    override fun getTotalByKilo(Kilo: Double): Double {
        return Kilo * getPrice()
    }
}

/****** Forma Correcta *****/
// Interfaz que obtiene el precio de la fruta
interface Price {
    fun getPrice(): Double
}

// interfaz que calcula el precio de la fruta por kilo
interface CalculateByKilo : Price {
    // cuando una interfaz implementa otra interfaz, los metodos de la otra interfaz son implicitos
    fun getTotalByKilo(Kilo: Double): Double
}

// interfaz que calcula el precio de la fruta por unidad
interface CalculateByUnit : Price {
    fun getTotalByUnit(Unit: Double): Double
}

// La Guinda se vende por kilo, implementamos la interfaz CalculateByKilo
class Guinda : CalculateByKilo {
    override fun getPrice(): Double {
        return 200.0
    }

    override fun getTotalByKilo(Kilo: Double): Double {
        return Kilo * getPrice()
    }
}

// La Lechuga se vende por unidad, implementamos la interfaz CalculateByUnit
class Lechuga : CalculateByUnit {
    override fun getPrice(): Double {
        return 100.0
    }

    override fun getTotalByUnit(Unit: Double): Double {
        return Unit * getPrice()
    }
}



/* DIFICULTAD EXTRA (opcional):
* Crea un gestor de impresoras.
* Requisitos:
* 1. Algunas impresoras sólo imprimen en blanco y negro.
* 2. Otras sólo a color.
* 3. Otras son multifunción, pueden imprimir, escanear y enviar fax.
* Instrucciones:
* 1. Implementa el sistema, con los diferentes tipos de impresoras y funciones.
* 2. Aplica el ISP a la implementación.
* 3. Desarrolla un código que compruebe que se cumple el principio.
*/

//Interfaces segregadas
interface BlackAndWhitePrinter {
    fun printBlackAndWhite()
}

interface ColorPrinter {
    fun printColor()
}

interface MultiFunctionPrinter : BlackAndWhitePrinter, ColorPrinter {
    fun scan()
    fun sendFax()
}

//Clases que representan una impresora
class AcerBlackAndWhite : BlackAndWhitePrinter {
    override fun printBlackAndWhite() {
        println("Imprimiendo en blanco y negro")
    }
}

class AcerColor : ColorPrinter {
    override fun printColor() {
        println("Imprimiendo a color")
    }
}

class AcerMultiFunction : MultiFunctionPrinter {
    override fun printBlackAndWhite() {
        println("Imprimiendo en blanco y negro")
    }

    override fun printColor() {
        println("Imprimiendo a color")
    }

    override fun scan() {
        println("Escanenado")
    }

    override fun sendFax() {
        println("Enviando fax")
    }
}

fun main() {
    println("\n**************Ejercicio Principal**************")
    val guinda = Cereza()
    val lechuga = Melon()

    val guindaWeight = 2.5
    val lechugaUnits = 2.0

    // Mostramos el resumen de la venta
    println(
        "Valor del kilo de la guinda: ${guinda.getPrice()} \nTotal a pagar por la guinda: ${
            guinda.getTotalByKilo(
                guindaWeight
            )
        }"
    )
    println(
        "Valor por unidad del lechuga: ${lechuga.getPrice()} \nTotal a pagar por el lechuga: ${
            lechuga.getTotalByUnit(
                lechugaUnits
            )
        }"
    )
    // Ejercicio Extra
    println("\n**************Ejercicio Extra**************")
    println("\nImpresoras")
    AcerBlackAndWhite().printBlackAndWhite()
    AcerColor().printColor()

    println("\nImpresora multifuncional")
    AcerMultiFunction().printColor()
    AcerMultiFunction().printBlackAndWhite()
    AcerMultiFunction().scan()
    AcerMultiFunction().sendFax()
}