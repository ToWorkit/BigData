package demo0606

/**
  * 协变：一个泛型类接收的泛型参数的参数的值可以是本身的类型或者是子类的类型
  */
class demo_01 {}

// 动物
class Animal {}
// 子类 鸟
class Bird extends Animal
// 子类 麻雀
class  Sparrow extends Bird

// 类 动作 吃东西 协变
class EatSomething[+ T](t: T) {}

object demo_01 {
  def main(args: Array[String]): Unit = {
    // 创建一个鸟吃东西的对象
    var c1: EatSomething[Bird] = new EatSomething[Bird](new Bird)

    // 创建一个动物吃东西的对象
    // EatSomething 必须是协变否则会报错
    // 协变后就可以完成将一个子类Bird付给父类Animal
    var c2: EatSomething[Animal] = c1
  }
}