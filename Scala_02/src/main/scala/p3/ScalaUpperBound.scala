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
  override def drive(): Unit = super.drive()
}

object ScalaUpperBound {

}
