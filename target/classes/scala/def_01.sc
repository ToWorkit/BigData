def myFun(name: String): String = "Hello " + name
println(myFun("World"))

def myFun_01(): String = "Hello World"

// 值函数，把函数变为变量的值
val v1 = myFun("Job")
val v2 = myFun_01()

// 再将 v1 作为值传递给 v1
println(myFun(v1))

// 匿名函数
// def fun(x: Int): Int = x * 3
(x: Int) => x * 2

Array(1, 2, 3).map((x: Int) => x * 3)