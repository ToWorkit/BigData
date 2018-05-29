// 具体看图分析value和name
// call by value 对函数的实参求值，仅仅只求一次
def test01(x:Int, y:Int):Int = x + x
// cal by name 函数的实参每次在函数体内部被调用(必须在函数体内有调用)的时候，都会求值
def test02(x:Int, y: => Int):Int = x + x

/**
  * value 和 name 举例
  */
// x 为 call by value, y 为 call by name
def bar(x:Int, y: => Int):Int = 1
// 定义一个死循环
def loop():Int = loop

bar(1, loop) // 值为1，因为call by name 必须在函数体内有被调用才会执行
// bar(loop, 1) // 死循环


/**
  * 参数
  */
// 默认参数
def fun01(name: String = "Hello ", test: String = "World") = name + test
fun01()

// 代名参数
def fun02(name: String = "Hello", age: Int = 20): String = name + " this age of " + age
fun02()
fun02(age = 24)

// 可变参数
var result = 0
def fun03(args: Int*): Int = {
  for(i <- args) result += i
  result
}
fun03(1, 2, 3) // 6
fun03(args = 3, 1, 4, 5) // 因为result是全局变量 19