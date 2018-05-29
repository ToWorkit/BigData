import scala.collection.mutable

// 列表 List

// 不可变列表
val nameList = List("Mary", "Tom", "Mike")
// 整数列表
val intList = List(1, 2, 3, 4)
// 空列表
val nullList: List[Nothing] = List()
// 二维列表
val din: List[List[Int]] = List(List(1, 2, 3), List(4, 5, 6))

// 操作
println("第一个人的名字: " + nameList.head)
println("除去一个元素后，剩下的元素：" + nameList.tail)

// 可变列表(LinkedList): 可以修改里面的值
val myList = mutable.LinkedList(1, 2, 3, 4, 5, 6)

// 操作，每个元素乘以 2
// 类似 PLSQL程序 cursor 游标

// 定义一个指针
var cur = myList
// 当指针不等于null的时候，Nil： Scala的null值
while(cur != Nil) {
  // 对当前值乘以2
  cur.elem = cur.elem * 2

  // 把指针指向下一值
  cur = cur.next
}

// 查看结果
println(myList)