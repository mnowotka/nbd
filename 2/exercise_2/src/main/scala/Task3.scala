object Task3 extends App {
  case class Osoba(imie: String, Nazwisko: String)

  def greetPerson(person: Osoba): String = person match {
    case Osoba("Tomasz", "Pieciukiewicz") => "Dzień dobry Panie Profesorze!"
    case Osoba("Aleksander", "Kolata") => "Siema, rozwiązałeś zadania z NBD?"
    case Osoba(imie, nazwisko) => s"Witaj ${imie} ${nazwisko}, miłego dnia!"
  }

  assert(greetPerson(Osoba("Tomasz", "Pieciukiewicz")) == "Dzień dobry Panie Profesorze!")
  assert(greetPerson(Osoba("Aleksander", "Kolata")) == "Siema, rozwiązałeś zadania z NBD?")
  assert(greetPerson(Osoba("Jan", "Kowalski")) == "Witaj Jan Kowalski, miłego dnia!")

}