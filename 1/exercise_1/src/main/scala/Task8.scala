object Task8 extends App {

  def recursivelyRemoveZeros(list: List[Int]): List[Int] =  list match {
    case Nil => List()
    case 0 :: tail => recursivelyRemoveZeros(tail)
    case head :: tail => List(head) ++ recursivelyRemoveZeros(tail)
  }

  println(recursivelyRemoveZeros(List(0, 1, 0, -1, 0, 3, -7, 0)))

}
