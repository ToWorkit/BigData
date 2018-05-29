// 映射 就是一个<key, value>的map集合

// 类型一 不可变映射Map
val scores = Map("A" -> 10, "B" -> 20, "c" -> 30)
// 类型二 可变映射Map
val chinese = scala.collection.mutable.Map("a" -> 1, "b" -> 2, "c" -> 3)

// 获取值
chinese("b")

// 判断某个值是否存在
if(chinese.contains("c")) {
  chinese("c")
} else {
  -1
}
// 判断值简写，有则返回，无则返回后面指定的值
chinese.getOrElse("d", -1)