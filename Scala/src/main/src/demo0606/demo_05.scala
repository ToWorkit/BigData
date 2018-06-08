package demo0606

/***
  * 隐式类，对类的功能进行加强
  * 调用隐式转换类 把 1 => Calc对象
  */
class demo_05 {}

object demo_05 {
  // 定义一个隐式类
  implicit class Calc(x: Int) {
    // 看demo_03的隐式转换
    def add(y: Int) = x + y
  }
  def main(args: Array[String]): Unit = {
    // 1.add(2) Int是没有add方法的
    // 当1.add(2)时，scala编译器不会立即报错，会在当前域中查找有没有implicit修饰且可以将Int作为参数的构造器，还具有add方法的类，通过查找则找到了Calc
    // 然后利用隐式类Calc来执行add方法
    println(1.add(2))
  }
}
