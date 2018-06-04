package demo0604

object def_ {
  def main(args: Array[String]): Unit = {
    def Foo(name: String): String = "Hello " + name
    println(Foo("World"))

    def Foo_0(): String = "Job"

    // 将函数变为变量的值, 值函数
    var v1 = Foo("Fink")
    var v2 = Foo_0();

    // 将 v1 作为值传递给 v2
    println(Foo(v1)) // Hello Hello Fink


    def fun(x: Int): Int = x * 3
    println(fun(2))
    // 匿名函数
//    (x: Int) => x * 3
    val arr = Array(2, 3, 1).map(x => x * 3)
    arr.foreach(println)
  }
}
