// 元组
val a1 = (1, 2, 3, "Hello")

// 4 -> 个数
val a2 = new Tuple4(1, 2, 3, "World")

// 获取值(非索引)
a1._1
a2._4

// 遍历，首先需要构造迭代器
a2.productIterator.foreach(println)