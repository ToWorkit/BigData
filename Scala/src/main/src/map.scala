/**
  * 映射 就是一个 <key, value> 的map集合
  */
object map {
  def main(args: Array[String]): Unit = {
    // 不可变映射Map
    val scores = Map("A" -> 1, "B" -> 2, "C" -> 3)
    println(scores("B"))

    // 可变映射Map
    val chinese = scala.collection.mutable.Map("a" -> 10, "b" -> 11, "c" -> 12)
    chinese += "d" -> 14
    println(chinese)

    // 判断某个值是否存在
    if(chinese.contains("c")) {
      chinese("c") = 20
    } else {
      -1
    }
    println(chinese)
    // 判断简写
    val bl = chinese.getOrElse("d", -1)
    println(bl) // 14
  }
}
