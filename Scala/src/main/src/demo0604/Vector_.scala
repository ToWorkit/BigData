package demo0604

/**
  * Vector 是ArrayBuffer(可变数组)的不可变版本，是一个带下标的序列
  * 支持快速的查找和更新
  */
object Vector_ {
  def main(args: Array[String]): Unit = {
    // 常用的序列 Vector Range

    // Vector 带下标的序列，从 0 开始
    val v = Vector(3, 2, 1, 4, 5, 0)
    // 快速的查找 返回第一个满足条件的元素
    println(v.find(_ > 3)) // Some(4)
    // 更新下标为 2 的元素值
    println(v.updated(2, 12)) // Vector(3, 2, 12, 4, 5, 0)

    // Range 整数序列
    println("第一种写法：" + Range(0, 5))
    println("第二种写法：" + (0 until 5))
    println("第三种写法：" + (0 to 5))
    // 两个Range相加
    println(('0' to '9') ++ ('A' to 'z'))

    // 将Range转为List
    println(1 to 5 toList)
  }
}
