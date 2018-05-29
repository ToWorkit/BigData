import scala.collection.mutable.ArrayBuffer

/**
  * 数组
  */
object Array_01 {
  def main(args: Array[String]): Unit = {
    // 定长数组
    val a = new Array[Int](10)
    val b = new Array[String](3)
    val c = Array("a", "c")
    println(a(2), b(1), c(0)) // (0,null,a)

    // 不定长数组
    val d = new ArrayBuffer[Int]()
    // 添加元素
    d += 1
    d += 2
    // 添加多个
    d += (4, 3, 2)
    println(d)
    // 删除下标为 2 的元素
    d.remove(2)
    println(d)


    /**
      * foreach 没有返回值
      * ()
      * ArrayBuffer(1, 2, 3, 2)
      * map 有返回值
      * ArrayBuffer(3, 4, 5, 4)
      * ArrayBuffer(1, 2, 3, 2)
      */
    // 遍历数组且不会修改原数组
    val l = d.foreach(_ + 1)
    println(l)
    println(d)

    val l_ = d.map(_ + 2)
    println(l_)
    println(d)

    // 常见的数组操作
    val arr = Array(2, 9, 7, 4, 5, 1)

    // 最大值，最小值，求和
    println(arr.max, arr.min, arr.sum)

    // 排序
    val arr_s = arr.sortWith(_ > _)
    arr_s.foreach(print)

    // 完整的写法
    arr.sortWith((a, b) => {
      if (a > b)
        true
      else
        false
    })
    println()
    // 二维数组
    val erArr = Array.ofDim[Int](3, 4)
    erArr(1)(2) = 10
    erArr.foreach(println)
  }
}
