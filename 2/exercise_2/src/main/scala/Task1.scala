object Task1 extends App {

  def whatDayIsIt(day: String): String = day match {
    case "Poniedziałek" | "Wtorek" | "Środa" | "Czwartek" | "Piątek" => "Praca"
    case "Sobota" | "Niedziela" => "Weekend"
    case _ => "Nie ma takiego dnia"
  }

  assert(whatDayIsIt("Poniedziałek") == "Praca")
  assert(whatDayIsIt("Wtorek") == "Praca")
  assert(whatDayIsIt("Środa") == "Praca")
  assert(whatDayIsIt("Czwartek") == "Praca")
  assert(whatDayIsIt("Piątek") == "Praca")
  assert(whatDayIsIt("Sobota") == "Weekend")
  assert(whatDayIsIt("Niedziela") == "Weekend")
  assert(whatDayIsIt("Sunday") == "Nie ma takiego dnia")

}
