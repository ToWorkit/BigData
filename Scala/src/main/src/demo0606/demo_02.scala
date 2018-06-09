package demo0606

/**
  * 逆变，泛型类型接收的泛型参数的值可以是本身的类型或者其父类
  * - T
  */
/*class demo_02 {}

// 动物
class Animal_01 {}

// 子类 鸟
class Bird extends Animal_01

// 子类 麻雀
class Sparrow extends Bird

// 定义吃东西的类 逆变
class EatSomething[-T](t: T) {}*/

/*object demo_02 {
  def main(args: Array[String]): Unit = {
    // 创建一个鸟吃东西的对象
    var c1: EatSomething[Bird] = new EatSomething[Bird](new Bird)
    // 创建一个麻雀吃东西的对象
//    var c2: EatSomething[Sparrow] = new EatSomething[Sparrow](new Sparrow)
    // 非逆变的情况下尝试将 c1 赋值给 c2
    var c2:EatSomething[Sparrow] = c1
  }
}*/

