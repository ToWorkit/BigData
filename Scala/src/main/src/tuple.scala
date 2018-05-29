/**
  * 元组
  */
object tuple {
  def main(args: Array[String]): Unit = {
    val a = (1, 2, 3, "Hello")
    // 限制个数
    val a4 = Tuple4(1, 2, 3, 4)

    // 获取值(不是索引)
    println(a._1)
    println(a4._4)
    
  }
}
