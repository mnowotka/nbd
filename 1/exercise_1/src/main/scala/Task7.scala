object Task7 extends App {

  def boringOptionExample(key: String): Option[BigDecimal] = {
    val products = Map("spam" -> BigDecimal(2.5), "eggs" -> BigDecimal(3.2), "ham" -> BigDecimal(4.3))
    products.get(key)
  }

  println(boringOptionExample("non-existent-key").getOrElse(0))
  println(boringOptionExample("ham").getOrElse(0))
}
