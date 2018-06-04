/**
  * 继承
  * 使用匿名子类复写父类中的方法
  */
// 父类
class Person(val name: String, val age: Int) {
  // 方法(函数)
  def sayHello(): String = "Hello " + name + " and the age is " + age;
}

// 定义子类
// 使用 override 声明子类中和父类相同的参数名 重写
class Employee(override val name: String, override val age: Int, salary: Int) extends Person(name, age) {
  // 重写父类的方法 {} = 写法都可以
  override def sayHello(): String = {
    super.sayHello()
  }
}

object demo {
  def main(args: Array[String]): Unit = {
    // 创建父类对象
    val p = new Person("Per", 90)
    println(p.sayHello())

    // 创建子类对象
    val p1 = new Employee("Job", 25, 99999)
    println(p1.sayHello())

    // 创建一个匿名子类，从Person继承
    val p2 = new Person("Kin", 23) {
      // 在匿名子类中重写父类的方法
      override def sayHello(): String = "匿名"
    }
    println(p2.sayHello())
  }
}