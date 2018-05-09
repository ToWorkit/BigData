// 抽象类型

// 父类 抽象 -> 交通工具
abstract class Vehicle {
  // 定义一个抽象方法
  def checkType(): String
}

// 子类
class Car extends Vehicle {
  // 实现抽象类方法
  def checkType(): String = {
    "I am a Car"
  }
}

class Bike extends Vehicle {
  // 实现抽象类方法
  def checkType(): String = {
    "I am a Bike"
  }
}

object demo {
  def main(args: Array[String]): Unit = {
    var v1: Vehicle = new Car
    var v2: Vehicle = new Bike

    println(v1.checkType())
    println(v2.checkType())
  }
}