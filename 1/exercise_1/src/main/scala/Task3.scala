import scala.annotation.tailrec

object Task3 extends App {

  @tailrec
  def tailRecursivelyGeneratePolishWeekDayNames(
       list: List[String] = List("Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela"),
       acc: String = ""
    ): String = {
    if (list == Nil) acc
    else tailRecursivelyGeneratePolishWeekDayNames(
      list.tail,
      if(acc != "") acc + "," + list.head else list.head)
  }


  println(tailRecursivelyGeneratePolishWeekDayNames())

}
