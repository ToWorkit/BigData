/*
属性的get和set方法
1、当定义属性的时候，如果是private，Scala会自动生成对应的set和get方法
     private var stuName:String = "Tom"
     (1) get方法：stuName
     (2) set方法： stuName_

2、如何只有get方法，没有set方法？ ---> 将属性定义为: 常量 val
      private val money:Int = 1000

3、不希望生成get和set方法: private[this]
   该属性只属于该对象私有
 */
class Student {
  // 定义属性
  private var stuName: String = "Hello"
  // 只有get方法
  private val money: Int = 100
}

object Student {
  def main(args: Array[String]): Unit = {
    var s = new Student
    println(s.stuName)
    println(args(0))
    // 修改money error
//    s.money = 2000
  }
}
Student.main(Array_01("Tom"))