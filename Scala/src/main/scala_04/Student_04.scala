class Student_04(val stuName: String) {

}
// 伴生对象
object Student_04 {
  // apply 在创建类的对象时可以不写new关键字 (Array 就是直接创建省去了 new)
  def apply(stuName: String) = {
    println("调用到了apply方法")
    // 调用主构造器
    new Student_04(stuName)
  }

  def main(args: Array[String]): Unit = {
    // 创建学生对象
    var s = new Student_04("Link")
    println(s.stuName)

    // 创建学生对象，省去new关键字 apply
    var s1 = Student_04("Mart")
    println(s1.stuName)
  }
}
