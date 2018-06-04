// 数组
import scala.collection.mutable.ArrayBuffer

// 定长数组 Array
val a = new Array[Int](10)
val b = new Array[String](3)
val c = Array("Hello", "World")
println(c(1))

// 变长数组 ArrayBuffer
val d = new ArrayBuffer[Int]()
// 添加元素
d += 1
d += 2
// 添加多个
d += (3, 4, 5, 6)
// 去除后3个
d.trimEnd(3)
d

// 将 变长数组 ArrayBuffer 转为 定长数组 Array
d.toArray

// 遍历数组
d.foreach(println) // 无返回值
d.map(println) // 有返回值

// 数组常见操作
val myArr = Array(1, 20, 1, 32, 45, 0)

// 最大，最小，求和
myArr.min
myArr.max
myArr.sum

// 排序
myArr.sortWith(_ > _)
// 完整的
myArr.sortWith((a, b) => {
  if (a > b)
    true
  else
    false
})

// 二维数组 shell 环境下可以看的比较清晰
val erArr = Array.ofDim[Int](3, 4)
erArr(1)(2) = 20
erArr.foreach(println)
