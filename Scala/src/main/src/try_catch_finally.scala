object try_catch_finally {
  def main(args: Array[String]): Unit = {
    try {
      // 注意查看懒值和直接查找的区别
//      val words = scala.io.Source.fromFile("d:\\wadsa.yz").mkString
      lazy val z = scala.io.Source.fromFile("d:\\wadsa.yz").mkString
    } catch {
      // io异常
      case e: java.io.FileNotFoundException => {
        println("File Not Found")
      }
      // 所有的异常
      case _: Exception => {
        println("Other Ex")
      }
    } finally {
      println("All")
    }
  }
}
