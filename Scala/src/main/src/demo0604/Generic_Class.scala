package demo0604

/**
  * 泛型
  */
// 操作Int的类型
class Generic_Class {
  // 定义一个变量
  private var age: Int = 100
  private var content: String = "Hello World"
}


object Generic_Class {
  def main(args: Array[String]): Unit = {
    val g = new Generic_Class
    println(g.age) // 100
    g.age = 200
    println(g.age) // 200
  }
}
