class Student {
  // 定义属性
//  private[this] var stuName: String = "Tom" // 添加this之后为真正的私有成员，外部不可以直接访问
  private var stuName: String = "Tom"
  private var stuAge: Int = 20

  // 定义方法（函数）
  def getStuName(): String = stuName
  def setStuName(newName: String) = this.stuName = newName

  def getStuAge(): Int = stuAge
  def setStuAge(newAge: Int): Unit = this.stuAge = newAge
}

// 创建学生对象
// object对象可以跟类名不一样，如果一样object对象就是该类的伴生对象
object Student {
  // 都是静态的
  def main(): Unit = {
    // 创建学生对象
    var s1 = new Student

    // 第一次输出
    println(s1.getStuName() + "\t" + s1.getStuAge())

    // 调用set方法
    s1.setStuName("Job")
    s1.setStuAge(21)

    // 第二次输出
    println(s1.getStuName() + "\t" + s1.getStuAge())

    // 直接访问类的私有成员
    println(s1.stuName + "\t" + s1.stuAge)
  }
}
Student.main()