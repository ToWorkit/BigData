package demo0604
import scala.collection.mutable
object list_ {
  def main(args: Array[String]): Unit = {
    test_0()
    test_1()
  }
  // 不可变列表
  def test_0(): Unit = {
    // 列表 List
    val nameList = List("Mary", "Tom", "Mike")
    // 整数列表
    val intList = List(1, 2, 3, 4, 5)
    // 空列表
    val nullList: List[Nothing] = List()
    // 二维列表
    val din: List[List[Int]] = List(List(1, 2, 3), List(4, 5, 6))

    // 操作
    println("第一个: " + nameList.head)
    println("除第一个元素以外其他的所有元素：" + nameList.tail)
  }

  // 可变列表
  def test_1(): Unit = {
    // 可以修改值
    val mtList = mutable.LinkedList(1, 2, 3, 4, 5)

    /**
      * 每个元素乘以2
      * 类似 PLSQL程序的 cursor 游标
      */
    // 定义一个指针
    var cur = mtList
    // 当指针不等于null的时候，Nil： Scala的null值
    while(cur != Nil) {
      // 对当前值乘以 2
      cur.elem = cur.elem * 2
      // 将指针指向下一个值
      cur = cur.next
    }
    println(mtList)
  }
}
