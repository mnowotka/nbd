class KontoBankowe(private var _stanKonta: BigDecimal) {

  def this() = this(0)

  def wplata(kwota: BigDecimal): Unit = {
    this._stanKonta += kwota.max(0)
  }

  def wyplata(kwota: BigDecimal): BigDecimal = {
    var realnaWyplata = kwota.min(this._stanKonta)
    this._stanKonta -= realnaWyplata
    realnaWyplata
  }

  def stanKonta: BigDecimal = _stanKonta

}

object Task2 extends App {
    val account = new KontoBankowe()
    assert(account.stanKonta == 0)
    account.wplata(200)
    assert(account.stanKonta == 200)
    val money = account.wyplata(100)
    assert(money == 100)
    assert(account.stanKonta == 100)
    val moreMoney = account.wyplata(500)
    assert(moreMoney == 100)
    assert(account.stanKonta == 0)
}