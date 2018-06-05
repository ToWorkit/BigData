package demo0604

/**
  * 模式匹配
  */
class match_case {

}

object match_case {
  def main(args: Array[String]): Unit = {
    // 根据一个变量的值，判断是做加法运算还是减法运算
    var ch1 = "-"
    var flag = 8
    // 匹配ch1
    ch1 match {
      case "+" => flag += 1
      case "-" => flag -= -1
      case _ => flag = 0
    }
    println(flag) // 9

    // 守卫模式，匹配某种类型的所有值
    // 匹配所有的数字
    var ch2 = '6'
    var digit: Int = 0
    ch2 match {
      case '+' => println("+")
      case '-' => println("-")
      // 判断是否是数字，转为 10进制
      case _ if Character.isDigit(ch2) => digit = Character.digit(ch2, 10)
      case _ => println("其他类型")
    }
    println(digit + 1)

    // 3. 模式匹配中使用变量(字符)
    var str3 = "Hello World"
    str3(7) match {
      case '+' => println("这是一个加号")
      case '-' => println("这是一个减号")
      case ch => println("这个字符是：" + ch)
    }

    // 类型的模式
    // Any 任意的类型
    var v4: Any = 100
    v4 match {
      case x: Int => println("整数")
      case s: String => println("字符串")
      case _ => println("其他类型")
    }

    // 数组和列表
    var myArr = Array(1, 2, 3)
    myArr match {
      case Array(0) => println("0")
      case Array(x, y) => println("该数组中包含两个元素，和为： " + ( x + y))
      case Array(x, y, z) => println("该数组中包含三个元素，和为：" + (x + y + z))
      // _* 任意多
      case Array(x, _*) => println("这是一个数组")
    }

    var myList = List(1, 2, 3)
    myList match {
      case List(0) => println("0")
      case List(x, y) => println("该List中包含两个元素，和是：" + (x + y))
      case List(x, y, z) => println("该List中包含三个元素，和是: " + (x + y + z))
      case List(x, _*) => println("這是一個List")
    }

    // 使用样本支持模式匹配，类似 instanceof
    // 判断一个对象是否是某个类的对象
    class Test
    case class Car(name: String) extends Test
    case class Bike(name: String) extends Test
    // 定义一个Car 对象
    var tCar: Test = new Car("汽车")

    tCar match {
      case Car(name) => println("汽车_")
      case Bike(name) => println("自行车")
      case _ => println("其他类型")
    }
  }
}