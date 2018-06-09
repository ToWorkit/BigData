package demo0606

/**
  * 上界 s <: T s 必须是类型T的子类(或本身，自己也可以认为是自己的子类)
  * 下界 u :< T u 必须是类型T的父类(或本身，自己也可以认为是自己的父类)
  */
class Bounds_ {

}

// 定义父类，Vehicle代表所有的交通工具
class Vehicle {
  // 方法 驾驶
  def drive () = println("Driving")
}

// 定义两个子类 Car 和 Bike
class Car extends Vehicle {
  // 重写父类方法
  override def drive(): Unit = println("Car")
}
class Bike extends Vehicle {
  override def drive(): Unit = println("Bike")
}

object Vehicle {
  // 上界 T 必须是 Vehicle 的子类
  def takeVehicle[T <: Vehicle](v: T) = v.drive()

  // main方法中的takeVehicle方法接收的参数T的上界是Vehicle，表示只能是它或者其他子类
  def main(args: Array[String]): Unit = {
    // 定义一个交通工具的对象
    var v: Vehicle = new Vehicle
    takeVehicle(v)

    var c: Car = new Car
    takeVehicle(c)
  }
}