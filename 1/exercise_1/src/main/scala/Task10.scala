import scala.math.BigDecimal.double2bigDecimal

object Task10 extends App {

  def absFromRange(list: List[BigDecimal]): List[BigDecimal] = {
    list.filter(element => element > -5 && element < 12).map(filteredElement => filteredElement.abs)
  }

  println(absFromRange((-6.0 to 13.0 by 1 toList)))

}
