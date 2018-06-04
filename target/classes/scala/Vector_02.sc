// 使用样本类支持模式匹配，类似 instanceof

class Vehicle

case class Car(name: String) extends Vehicle
case class Bike(name: String) extends Vehicle

// 定义一个Car对象
var aCar: Vehicle = new Car("这是汽车")

aCar match {
  case Car(name) => println("这是一辆汽车")
  case Bike(name) => println("这是一辆自行车")
  case _ => println("其他类型")
}