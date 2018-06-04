class Student {
  // 定义属性
  private var stuName: String = "Tom"
  private var stuAge: Int = 20
  // 私有化，get和set都没有
  private[this] var stuMoney: Long = 999999999

  // 定义方法(函数)
  def getStuName(): String = stuName
  def setStuName(newName: String) = this.stuName = newName

  def getStuAge(): Int = stuAge
  def setStuAge(newAge: Int) = this.stuAge = newAge
}

// 定义该类的伴生对象(静态的)执行main方法
object Student {
  def main(args: Array[String]): Unit = {
    // 创建学生对象
    var s1 = new Student

    println(s1.getStuName() + "\t" + s1.getStuAge())

    // 调用写的set方法
    s1.setStuAge(21)
    s1.setStuName("Jol")

    println(s1.getStuName() + "\t" + s1.getStuAge())

    // 直接去访问类的私有成员(会自动生成get和set方法)
    println(s1.stuAge + "\t" + s1.stuName)

    // 访问专属私有的成员会报错
    // println(s1.stuMoney)

    // 自动生成的get和set方法
    s1.stuName = "Link"
    println(s1.stuName)
  }
}