class class_05(val stuName: String) {

}
object class_05 {
  // apply 在创建类的对象时候，可以不写new关键字
  def apply(stuName: String) = {
    println("调用到了apply方法")

    // 调用主构造器
    new class_05(stuName)
  }

  def main(): Unit = {
    // 创建学生对象
    var s = new class_05("Job")
    println(s.stuName)

    // 创建学生对象，省去new关键字 apply
    var s1 = class_05("Mary")
    // 上面不写主主构造器这里会报错，找不到
    println(s1.stuName)
  }
}
class_05.main()