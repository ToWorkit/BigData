/**
  * 懒值，定义时不会执行，使用的时候才会执行
  */
object Lazy {
  def main(args: Array[String]): Unit = {
    // 使用lazy读取当前还不存在的文件
    val words = scala.io.Source.fromFile("d:\\a.txt").mkString
    println(words)
    // shell中比较明显
    lazy val words_1 = scala.io.Source.fromFile("d:\\wws.txt").mkString
    // 报错
    println(words_1)
  }
}
