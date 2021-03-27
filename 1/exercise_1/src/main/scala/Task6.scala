object Task6 extends App {

  def printTriple(triple: (Int, Boolean, String)): Unit = {
    println(s"${triple._1}, ${triple._2}, ${triple._3}")
  }

  printTriple(1, false, "Ala ma kota")
}
