package demo0604

// 高阶函数
// 全部引入
import scala.math._

object fun_ {
  def main(args: Array[String]): Unit = {
    // 对数字 10 进行运算 math 文档中很多运算返回的都是double类型的值
    def someAction(f: (Double) => Double) = f(10)

    // 开平方
    println(someAction(sqrt))
    // sin
    println(someAction(sin))

    def test(x: Int, y: Int): Int = {x * y + 10}
    // 定义一个高阶函数
    def Fun_0(f: (Int, Int) => Int, x: Int, y: Int) = f(x, y)
    println(Fun_0(test, 3, 9))
  }
}
