// Map
// 不可变集合
val math = scala.collection.immutable.Map("Alice" -> 90, "Job" -> 90)

// 可变集合
val chinese = scala.collection.mutable.Map("Tom" -> 89, "Lida" -> 88)

// 获取集合中的值
chinese("Tom")

// 判断是否存在
if(chinese.contains("Tom")) {
  chinese("Lida")
} else {
  -1
}
// 判断简写
chinese.getOrElse("Job", -1)

// 修改更新可变集合中的值
chinese("Tom") = 100

// 添加
chinese += "Job" -> 98

// 删除
chinese -= "Lida"

// 迭代
for(s <- chinese) println(s)

// foreach 没有返回值
chinese.foreach(println)

