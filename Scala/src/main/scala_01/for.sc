// for 循环
// 定义一个集合
val list = List("Hello", "World", "You")
println(list)

println("for循环的第一种写法")
for(item <- list) println(item)

println("for循环的第二种写法")
for {
  item <- list
  if (item.length > 3)
}println(item)

println("for循环的第三种写法")
for(item <- list if item.length <= 3) println(item)

println("for循环的第四种写法")
var upItem = for {
  item <- list
  upItem = item.toUpperCase
}yield(upItem)
println(upItem(1))

println("while写法")
var i = 0
while(i < list.length) {
  println(list(i))
  i += 1
}

println("foreach写法")
list.foreach(println)