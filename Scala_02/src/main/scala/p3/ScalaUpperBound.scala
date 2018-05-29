package p3

// 上界的例子

// 父类：代表所有的交通工具
class Vehicle {
  // 方法
  def drive() = {println("Driving")}
}

// 定义两个子类
class Car extends Vehicle {
  // 子类 override
  override def drive(): Unit = {println("Car Driving")}
}
class Bike extends Vehicle {
  override def drive(): Unit = {println("Bike Driving")}
}
class Apple{}

object ScalaUpperBound {
  // 定义一个泛型函数
  def takeVehicle[T <: Vehicle](v: T) = {v.drive()}

  def main(args: Array[String]): Unit = {
    // 定义一个交通工具
    var v1: Vehicle = new Vehicle
    takeVehicle(v1)

    var v2: Car = new Car
    takeVehicle(v2)

    // 能否使用Apple error
//    var v3: Apple = new Apple
//    takeVehicle(v3)
  }
}
