object Task9 extends App {

  def incrementListElements(list: List[Int]): List[Int] = {
    list map {case (element) => element + 1}
  }

  println(incrementListElements(List(0, 1, 0, -1, 0, 3, -7, 0)))

}
