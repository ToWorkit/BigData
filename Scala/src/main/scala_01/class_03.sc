import scala.collection.mutable.ArrayBuffer

/*
* 内部类
* */
class Student {
  // 定义一个内部类(嵌套类) 学生选修的课程
  // 通过主构造器
  // 课程 学分
  class Course(val courseName: String, val credit: Int) {
    // 方法
  }

  // 属性
  private var stuName: String = "Jon"
  private var stuAge: Int = 20

  // 定义一个可变数组来保存该学生选修的课程
  private var courseList = new ArrayBuffer[Course]()

  // 定义方法 往学生信息中添加新的课程
  def addNewCourse(cname: String, credit: Int): Unit = {
    // 创建一门课程
    var c = new Course(cname, credit)

    // 加入list
    courseList += c
  }
}

// 创建学生对象
object Student {
  def main(): Unit = {
    // 创建学生
    var s = new Student

    // 给学生添加课程
    s.addNewCourse("Chiness", 6)
    s.addNewCourse("Math", 4)
    s.addNewCourse("English", 9)

    // 输出
    println(s.stuName + "\t" + s.stuAge)
    for(item <- s.courseList) println(item.courseName + "\t" + item.credit)
  }
  main()
}

Student