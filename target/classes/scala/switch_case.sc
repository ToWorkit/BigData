// 模式匹配

// 1. Scala中的switch ... case ...
// 根据一个变量的值，判断是做加法运算，还是减法运算
var ch1 = "-"
// flag < 0 减法 > 0 加法
var flag = 0
ch1 match {
  case "+" => flag = 1
  case "-" => flag = -1
  case _ => flag = 0
}
println(flag)

// 2. Scala 的守卫: 匹配某种类型的所有值
// 匹配的所有的数字
var ch2 = '6'
var digit: Int = -1
ch2 match {
  case '+' => println("这是一个加号")
  case '-' => println("这是一个减号")
  // 是否是数字                                转为 10 进制
  case _ if Character.isDigit(ch2) => digit = Character.digit(ch2, 10)
  case _ => println("其他类型")
}

// 3. 模式匹配中使用变量（字符）
var str3 = "Hello World"
str3(7) match {
  case '+' => println("这是一个加号")
  case '-' => println("这是一个减号")
  case ch => println("这个字符是：" + ch)
}

// 4. 类型的模式
// Any: 任意的类型，类似Java中的Object
var v4: Any = 100
v4 match {
  case x: Int => println("这是一个整数")
  case s: String => println("这是一个字符串")
  case _ => println("其他类型")
}

// 5. 数组和列表
var myArray = Array(1, 2, 3)
myArray match {
  case Array(0) => println("0")
  case Array(x, y) => println("该数组中包含两个元素，和是: " + (x + y))
  case Array(x, y, z) => println("该数组中包含三个元素，和是: " + (x + y + z))
  case Array(x, _*) => println("这是一个数组")
}

var myList = List(1, 2, 3)
myList match {
  case List(0) => println("0")
  case List(x, y) => println("该List中包含两个元素，和是：" + (x + y))
  case List(x, y, z) => println("该数组中包含三个元素，和是: " + (x + y + z))
  case List(x, _*) => println("這是一個List")
}

