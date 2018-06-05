package demo0604

/**
  * 抽象类型
  */
// 抽象父类
abstract class Extend_Vehicle {
  // 定义一个抽象方法
  def checkType(): String
}

// 子类 必须重写抽象方法
class Car extends Extend_Vehicle {
  override def checkType(): String = "I Am A Car"
}

class Bike extends Extend_Vehicle {
  override def checkType(): String = "I Am A Bike"
}

object demo {
  def main(args: Array[String]): Unit = {
    var v1 = new Car
    var v2 = new Bike

    println(v1.checkType() + " --- " + v2.checkType())
  }
}
