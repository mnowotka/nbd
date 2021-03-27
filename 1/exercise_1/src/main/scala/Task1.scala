object Task1 extends App {

  def generatePolishWeekDayNames(): String = {
    val polishWeekDays = List("Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela")
    val delimiter = ","
    var result: String = ""
    for (day <- polishWeekDays) {
      result += day
      if(day != polishWeekDays.last) {
        result += delimiter
      }
    }
    result
  }

  def generatePolishWeekDayNamesStartingWithLetterP(): String = {
    val polishWeekDays = List("Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela")
    val polishWeekDaysStartingWithLetterP = for { day <- polishWeekDays if day.startsWith("P")} yield day
    val delimiter = ","
    var result: String = ""
    for (day <- polishWeekDaysStartingWithLetterP) {
      result += day
      if(day != polishWeekDaysStartingWithLetterP.last) {
        result += delimiter
      }
    }
    result
  }

  def generatePolishWeekDayNamesUsingWhileLoop(): String = {
    val polishWeekDays = List("Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela")
    val delimiter = ","
    var result: String = ""
    var i = 0
    val length = polishWeekDays.size
    while(i < length){
      result += polishWeekDays(i)
      if (i != length - 1) {
        result += delimiter
      }
      i += 1
    }
    result
  }

  println(generatePolishWeekDayNames())
  println(generatePolishWeekDayNamesStartingWithLetterP())
  println(generatePolishWeekDayNamesUsingWhileLoop())

}
