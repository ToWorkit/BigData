package demo0604

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
  }
}
