object Task4 extends App {
    def applyThreeTimes(n: Int, func: Int => Int): Int = {
      Iterator.iterate(n)(func).drop(3).next
    }

    assert(applyThreeTimes(2, n => n * 2) == 16)
    assert(applyThreeTimes(1, n => n + 1) == 4)
}