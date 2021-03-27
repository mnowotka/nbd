object Task5 extends App {

  def mapOverMap(): Map[String, BigDecimal] = {
    val products = Map("spam" -> BigDecimal(2.5), "eggs" -> BigDecimal(3.2), "ham" -> BigDecimal(4.3))
    products map {case (key, value) => (key, value * 0.9)} // mapValues
  }

  println(mapOverMap())
}
