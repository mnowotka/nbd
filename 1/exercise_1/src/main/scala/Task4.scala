object Task4 extends App {

  def generatePolishWeekDayNamesUsingFoldl(): String = {
    val polishWeekDays = List("Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela")
    polishWeekDays.foldLeft(""){
      (acc, day) =>
        acc + (if(day == polishWeekDays.head) day else ", " + day)
    }
  }

  def generatePolishWeekDayNamesUsingFoldr(): String = {
    val polishWeekDays = List("Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela")
    polishWeekDays.foldRight(""){
      (day, acc) =>
        day + (if(day == polishWeekDays.last) acc else ", " + acc)
    }
  }

  def generatePolishWeekDayNamesStartingWithLetterPUsingFoldl(): String = {
    val polishWeekDays = List("Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela")
    polishWeekDays.foldLeft(""){
      (acc, day) =>
        if(day.startsWith("P"))
          acc + (if(day == polishWeekDays.head) day else ", " + day)
        else
          acc
    }
  }


  println(generatePolishWeekDayNamesUsingFoldl())
  println(generatePolishWeekDayNamesUsingFoldr())
  println(generatePolishWeekDayNamesStartingWithLetterPUsingFoldl())
}
