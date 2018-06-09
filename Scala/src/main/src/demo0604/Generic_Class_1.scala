package demo0604

import scala.reflect.ClassTag

class Generic_Class_1 {
  // 创建函数用于创建一个Int类型的数组
  def mkIntArray(elems: Int*) = Array[Int](elems: _*)
  // 创建函数用于创建一个String类型的数据
  def mkStringArray(elems: String*) = Array[String](elems: _*)
  // 泛型函数 可以创建Int类型的数组也可以创建String类型的数组
  def mkArray[T: ClassTag](elems: T*) = Array[T](elems: _*)
}
object Generic_Class_1 {
  def main(args: Array[String]): Unit = {
    val gc = new Generic_Class_1
    println("Int类型的数组")
    val mkia = gc.mkIntArray(2, 3, 1, 5, 0)
    mkia.foreach(println)
    println("String类型的数组")
    val mksa = gc.mkStringArray("Hello", "World", "Beijing")
    mksa.foreach(println)

    val mkai = gc.mkArray(3, 4, 1)
    mkai.foreach(println)
    val mkas = gc.mkArray("ADA", "OCN")
    mkas.foreach(println)
  }
}
