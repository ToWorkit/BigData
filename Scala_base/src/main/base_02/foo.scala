object foo {
  def main(args: Array[String]): Unit = {
    val res = fo(5)
    println(res)
    val res_1 = fo_1(5)
    println(res_1)
  }
  // 尾调用优化递归
  def fo(x: Int, y: Int = 1):Int = {
    if(x <= 1)
      y
    else
      fo(x - 1, x * y)
  }
  // 递归
  def fo_1(x: Int): Int = {
    if (x <= 1)
      1
    else
      x * fo_1(x - 1)
  }
}
