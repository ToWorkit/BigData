/**
  * 隐式类的功能: 对类的功能进行加强
  * 第一步 先调用隐式转换类 把 1 ====> Calc对象
  */

object Demo_04 {
  // 定义一个隐式类
  implicit class Calc(x: Int) {
    def add(y: Int) = x + y
  }

  def main(): Unit = {
    // 1.add(2) Int是没有add方法的
    println(1.add(2))
  }
}

Demo_04.main()