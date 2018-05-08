//继承
/*
1、基本的继承
2、复写父类中的方法
3、使用匿名子类
 */

// 定义父类
class Person(val name: String, val age: Int) {
  // 方法(函数)
  def sayHello(): String = "Hello " + name + " and the age is " + age
}

// 定义子类
// 使用 override 来声明 子类中和 父类相同的参数名
class Employee(override val name: String, override val age: Int, salary: Int) extends Person(name, age) {
  // 重写父类中的sayHello方法
  override def sayHello(): String = "子类中的sayHello的方法"
}

object Demo1 {
  def main(args: Array[String]): Unit = {
    // 创建一个Person对象
    var p = new Person("Tom", 20)
    println(p.sayHello())

    // 创建一个子类
    var p1: Person = new Employee("Job", 25, 1000)
    println(p1.sayHello())

    // 创建一个匿名子类，从Person继承
    var p2: Person = new Person("Jerry", 26) {
      // 在匿名子类中，重写父类的方法
      override def sayHello(): String = "匿名子类中的sayHello方法"
    }
  }
}