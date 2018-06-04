/**
  * 构造器
  *   主构造器
  *     和类的申明在一起，只能有一个主构造器
  *   辅助构造器
  *     多个辅助构造器，必须通过关键字 this 实现
  */
class Student_03(val stuName: String, val stuAge: Int) {
  // 定义一个辅助构造器
  def this(age: Int) = {
    // 调用主构造器
    this("No Name", age)
  }
}

object Student_03 {
  def main(args: Array[String]): Unit = {
    // 使用主构造器创建学生对象
    var s = new Student_03("lo", 21)
    println(s.stuName + "\t" + s.stuAge)

    // 使用辅助构造器创建学生对象
    var s1 = new Student_03(25)
    println(s1.stuName + "\t" + s1.stuAge)
  }
}
