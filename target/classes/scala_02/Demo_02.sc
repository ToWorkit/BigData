// 逆变，一个泛型类接收的泛型参数的值可以是本身的类型是父类的类型
// 语法: 泛型前 -

// 动物
class Animal {}

// 子类 鸟
class Bird extends Animal

// 子类 麻雀
class Sparrow extends Bird

// 定义吃东西的类
class EatSomething[- T](t: T) {}

object Demo_02 {
  def main(args: Array[String]): Unit = {
    // 创建一个鸟吃东西的对象
    var c1: EatSomething[Bird] = new EatSomething[Bird](new Bird)

    // 创建一个麻雀吃东西的对象
    // var c2: EatSomething[Sparrow] = new EatSomething[Sparrow](new Sparrow)
    // 能否将 c1 赋给 c2
    // 原因，尽管Bird是Sparrow的父类，但是EatSomething[Bird]不是EatSomething[Sparrow]的父类
    var c2: EatSomething[Sparrow] = c1
  }
}