/**
  * 内部类
  */
import scala.collection.mutable.ArrayBuffer
class Student_02 {
  // 定义一个内部类(嵌套类)学生选修的课程
  class Course(val courseName: String, val credit: Int) {}

  // 属性
  private var stuName: String = "Job"
  private var stuAge: Int = 20

  // 定义一个可变数组保存学生选修的课程
  private var courseList = new ArrayBuffer[Course]()

  // 定义方法，往学生信息中添加新的课程
  def addNewCourse(cname: String, credit: Int) = {
    // 创建一门课程
    var c = new Course(cname, credit)
    // 加入到选修的课程中
    courseList += c
  }
}

// 伴生对象(就是java的静态)用来执行main方法
object Student_02 {
  def main(args: Array[String]): Unit = {
    // 创建学生
    var s = new Student_02

    // 添加课程
    s.addNewCourse("Chinese", 20)
    s.addNewCourse("Math", 30)
    s.addNewCourse("English", 27)

    // 输出
    println(s.stuName + "\t" + s.stuAge)
    for(item <- s.courseList) println(item.courseName + "\t" + item.credit)
  }
}
