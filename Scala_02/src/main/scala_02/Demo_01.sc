// 协变， 一个泛型类接收的泛型参数的值可以是本身的类型或者是子类的类型
// 语法，在泛型前 +

// 动物
class Animal {}

// 子类 鸟
class Bird extends Animal

// 子类 麻雀
class Sparrow extends Bird

// 类: 吃东西(动作)
class EatSomething[+ T](t: T) {}

object Demo_01 {
  def main(args: Array[String]): Unit = {
    // 创建一个鸟吃东西的对象
    var c1: EatSomething[Bird] = new EatSomething[Bird](new Bird)

    // 创建一个动物吃东西的对象
    // var c2: EatSomething[Animal] = new EatSomething[Animal](new Animal)
    // 遇到的问题，可否将 c1 赋给 c2
    // 原因，虽然Bird是Animal的子类，但是EatSomething[Bird]不是EatSomething[Animal]的子类
    // 把 EatSomething[Bird] 变成 EatSomething[Animal] 子类
    // 变为 协变 [+ T] 之后就不报错了
    var c2: EatSomething[Animal] = c1

    var c3: EatSomething[Sparrow] = new EatSomething[Sparrow](new Sparrow)
    var c4: EatSomething[Animal] = c3
  }
}