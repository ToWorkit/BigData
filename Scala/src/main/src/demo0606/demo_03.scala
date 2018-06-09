package demo0606

class demo_03 {}

/**
  * 隐式转换函数，以implicit关键字申明的带有带个参数的函数
  * @param name
  */
// 水果
class Fruit(name: String) {
  def getFruitName(): String = name
}
// 猴子
class Monkey(f: Fruit) {
  def say() = println("Monkey like " + f.getFruitName())
}

object demo_03 {
  /**
    * 需要
    *   可以直接调用 f.say()
    *   将 Fruit 对象转换成 Monkey 对象
    */
  // 解决方法，定义一个隐式转换函数(规则)， 将Fruit对象转为Monkey对象
  implicit def fruit2monkey(f: Fruit): Monkey = new Monkey(f)

  def main(args: Array[String]): Unit = {
    var f: Fruit = new Fruit("辣椒")
    f.say()
  }
}

