import scala.collection.mutable
// 集(Set): 是不重复元素的集合，默认的实现: HashSet

// 创建一个Set
var s1 = Set(1, 1, 23, 0, 45, 2)
// 往里面添加一个重复的元素
s1 += 0
s1

// 里面添加一个不重复的元素
s1 += 100
s1

// LinkedHashSet
var weeksDay = mutable.LinkedHashSet("星期一", "星期二")
weeksDay + "星期五"
// 是否存在
weeksDay.contains("星期二")

// 可排序的Set
var s2 = mutable.SortedSet(1, 2, 3, 100, 10, 4)
