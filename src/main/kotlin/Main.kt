typealias DoubleConvercation = (Double) -> Double
fun convert (x:Double, converter: DoubleConvercation): Double {
    val result = converter(x)
    println("$x is converted to $result")
    return result
}
fun getConvinLamb (str: String): DoubleConvercation {
    if (str == "CinF") {
        return {it * 1.8 + 32}
    }
    else if (str == "KginPoun"){
        return {it * 2.204623}
    }
    else if (str == "PinTon")
        return {it / 2000.0}
    else {
        return {it}
    }
}
fun combine (Lam1: DoubleConvercation, Lam2: DoubleConvercation): DoubleConvercation{
    return {x: Double -> Lam2(Lam1(x))}
}
fun main(args: Array<String>) {
    val groceryList = listOf(
        Grocery("Apple", 0.5),
        Grocery("Banana", 0.3),
        Grocery("Orange", 0.4)
    )
    println("Conv 2.5 kg to P: ${getConvinLamb("KginPoun") (2.5)}")
    val am = getConvinLamb("KginPoun")
    val pi = getConvinLamb("PinTon")
    val a = getConvinLamb("CinF")
    val o = combine(am, pi)
    val value = 17.4
    search(groceryList) { it.weight > 0.4 }
    println ("${convert(value,a)}")
    println("$value number is ${convert(value,o)} nice")
}
fun search (list: List<Grocery>, criteria: (g: Grocery) -> Boolean) {
    for (l in list) {
        if (criteria(l)){
            println("${l.name} meets the criteria.")
        }
    }
}

data class Grocery(val name: String, val weight: Double)

