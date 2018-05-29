/**
  * 循环
  */
object for_list {
  def main(args: Array[String]): Unit = {
    // for
    // 定义一个集合
    val list = List("Hello", "World", "Only")
    println(list)

    // 第一种写法
    for(item <- list) println(item)

    println("-" * 10)

    // 第二种写法
    for {
      item <- list
      if (item.length > 4)
    }println(item)

    println("-" * 10)

    // 第三种写法
    for(item <- list if item.length <= 4) println(item)

    println("-" * 10)

    // 第四种写法
    var upItem = for {
      item <- list
      upItem = item.toUpperCase
    }yield(upItem)
    println(upItem)

    println("-" * 10)

    // while
    var i = 0
    while(i < list.length) {
      println(list(i))
      i += 1
    }
  }
}
