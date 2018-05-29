object call_by_name_value {
  def main(args: Array[String]): Unit = {
    // call by value 对函数的实参求值，仅仅只求一次
    def t_value(x: Int, y: Int): Int = x + x

    // call by name 函数的实参每次在函数体内部(必须在函数体内部有调用)被调用的时候，都会求一次值
    def t_name(x: Int, y: => Int): Int = x + x

    /**
      * 应用
      * x call by value  foo(1 + 2, 2)  3, 2
      * x call by name foo(1 + 2, 2)  1 + 2, 2
      */
    // x 为 call by value，y 是 call by name
    def bar(x: Int, y: => Int): Int = 1
    // 定义一个死循环
    def loop():Int = loop
    // 值为1，必须在函数体内被调用才会执行
    bar(1, loop)
    // bar(loop, 1) 死循环

    /**
      * 参数
      */
    // 默认参数
    def foo01(name: String = "Hello", age: Int = 20) = name + "is " + age
    println(foo01())
  }
}
