abstract class Osoba(val imie: String, val nazwisko: String) {
  def podatek: BigDecimal
}

trait Pracownik extends Osoba {
  var pensja: BigDecimal = 0
  override def podatek: BigDecimal = this.pensja * 0.2
}

trait Student extends Osoba {
  override def podatek: BigDecimal = 0
}

trait Nauczyciel extends Pracownik {
  override def podatek: BigDecimal = this.pensja * 0.1
}

object Task5 extends App {
   var student = new Osoba("Jan", "Kowalski") with Student
   assert(student.podatek == 0)
   var worker = new Osoba("Marian", "Wisniewski") with Pracownik
   worker.pensja = 10000
   assert(worker.podatek == 2000)
   var teacher = new Osoba("Alfred", "Einstein") with Nauczyciel
   teacher.pensja = 10000
   assert(teacher.podatek == 1000)
   var studentThenWorker = new Osoba("Adrian", "Kujonowski") with Student with Pracownik
   studentThenWorker.pensja = 10000
   assert(studentThenWorker.podatek == 2000)
   var workerThenStudent = new Osoba("Marcin", "Pracowity") with Pracownik with Student
  workerThenStudent.pensja = 10000
   assert(workerThenStudent.podatek == 0)
}