package demo0604
import scala.collection.mutable
/**
  * Set 集，不重复元素的集合，默认实现是 HashSet
  */
object set_ {
  def main(args: Array[String]): Unit = {
    // 创建一个Set
    var s1 = Set(1, 2, 32, 1, 4, 2, 0)
    // 添加重复元素
    s1 += 0
    println(s1) // Set(0, 1, 2, 32, 4)

    // 链表哈希集合
    var weeksDay = mutable.LinkedHashSet("周一", "周二")
    // 添加
    weeksDay += "周五"
    println(weeksDay) // Set(周一, 周二, 周五)
    // 判断是否存在
    println(weeksDay.contains("周三")) // false

    // 可排序的set
    var s2 = mutable.SortedSet(2, 3, 1, 0, 9, 29, 4)
    println(s2) // TreeSet(0, 1, 2, 3, 4, 9, 29)
  }
}
