package demo0606

/**
  * 视图界定
  * 尽量使用视图界定来取代泛型的上界，因为适用的范围更加广泛
  */
class View_ {

}
object View_ {
  // 视图界定
  // 接收更广泛的数据类型，即字符串以及子类，可以转换成字符串的类型
  // 可以接受String和String的子类，也可以接受能转换成String类型的类型值
  def addTwoString[T <% String](x: T, y: T) = println(x + " ---- " + y)

  // 必须自定义转换规则
  implicit def int2String(n: Int) = n.toString

  def main(args: Array[String]): Unit = {
    addTwoString(100, 200)
  }
}