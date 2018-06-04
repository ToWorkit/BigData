// 水果
class Fruit(name: String) {
  def getFruitName(): String = {name}
}

// 猴子 Monkey
class Monkey(f: Fruit) {
  def say() = {println("Monkey like " + f.getFruitName())}
}

object Demo_03 {

  // 解决方案，定义一个隐式转换函数(规则): Fruit对象 -----> monkey对象
  implicit def fruit2Monkey(f: Fruit): Monkey = {new Monkey(f)}

  def main(): Unit = {
    var f: Fruit = new Fruit("香蕉")
    // 需求为可直接调用 f.say 方法
    // 问题 将Fruit对象 转换成 Monkey对象
    f.say()
  }
}
Demo_03.main()