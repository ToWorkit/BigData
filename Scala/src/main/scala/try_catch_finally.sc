try {
  val words_02 = scala.io.Source.fromFile("d:\\b.txt").mkString
} catch {
  case e: java.io.FileNotFoundException => {
    println("File Not Found")
  }
  // 所有异常
  case _: Exception => {
    println("Other Exception")
  }
} finally {
  println("Finally Block")
}