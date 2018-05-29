// Scala 的隐式参数
def testParam(implicit name: String) = { println("The value is" + name) }

// 定义一个隐式参数
implicit val name: String = "隐式参数"

// 调用，不想传递参数
testParam


def smaller[T](a: T, b: T)(implicit order: T => Ordered[T]) = if (a < b) a else b
smaller(100, 200)
smaller("c", "b")