// 高阶函数
// 引入所有
import scala.math._

// 对数字10进行运算
// f 执行的运算
// 查看文档 math 下的很多运算返回的数据类型都是Double
def someAction(f: (Double) => Double) = f(10)

// 开平方
someAction(sqrt)
// sin
someAction(sin)

// demo 02
def test(x: Int, y: Int): Int = {x * y + 10}

// 定义一个高阶函数
def myFun(f: (Int, Int) => Int, x: Int, y: Int) = f(x, y)
// 传入
myFun(test, 2, 3)