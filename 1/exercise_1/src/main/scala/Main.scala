object Main {

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

  def main(args: Array[String]) {
    println(generatePolishWeekDayNames())
    println(generatePolishWeekDayNamesStartingWithLetterP())
  }
}