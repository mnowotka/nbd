object Task2 extends App {

  def recursivelyGeneratePolishWeekDayNames(
       list: List[String] = List("Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela")
    ): String = list match {
    case Nil => ""
    case head :: Nil => head
    case head :: tail => head + "," + recursivelyGeneratePolishWeekDayNames(tail)
  }

  def recursivelyGenerateReversedPolishWeekDayNames(
        list: List[String] = List("Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela")
    ): String = list match {
    case Nil => ""
    case head :: Nil => head
    case head :: tail => recursivelyGenerateReversedPolishWeekDayNames(tail) + "," + head
  }

  println(recursivelyGeneratePolishWeekDayNames())
  println(recursivelyGenerateReversedPolishWeekDayNames())

}
