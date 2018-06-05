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


    // 闭包，内函数中可以访问外函数中的变量
    // factor 接收一个double参数，表示乘以多少倍
    // x 匿名函数，并且作为内函数可以访问外函数的值，即factor
    def mulB(factor: Double) = (x: Double) => x * factor

    // 乘以 3 的方法函数
    val test_01 = mulB(3)
    println(test_01(10))
  }
}
