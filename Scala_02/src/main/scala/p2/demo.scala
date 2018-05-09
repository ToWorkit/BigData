package p2

/*
* 有点像接口, 也有点像抽象类, 支持多重继承
* */

trait Hello {
  // 定义抽象字段
  var id: Int
  var name: String

  // 方法: 可以是抽象的 也可以不是抽象的
  def sayHello(): String = "Hello " + name
}

// 定义第二个trait
trait Action {
  // 定义一个抽象方法
  def getActionName(): String
}

// 定义一个子类 从上面的两个trait继承
// extends with
class Student(var id: Int, var name: String) extends Hello with Action {
  // 实现Action中的getActionName
  def getActionName(): String = "Action is Running "
}

object Demo {
  def main(args: Array[String]): Unit = {
    // 创建一个学生对象
    var s = new Student(2, "Job")
    println(s.sayHello())
    println(s.getActionName())
  }
}