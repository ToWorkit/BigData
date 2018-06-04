/**
  * 利用object对象实现单例模式
  */
object CreditCard {
  // 专属私有属性(没有get和set) 变量，保存信用卡卡号
  private[this] var creditCardNumber: Long = 0

  // 方法定义在object中，所以是静态的，即 单例
  // 产生新的卡号
  def generateNewNumber() = {
    creditCardNumber += 1
    creditCardNumber
  }

  // 测试程序
  def main(args: Array[String]) = {
    // 产生新的卡号
    println(CreditCard.generateNewNumber())
    println(CreditCard.generateNewNumber())
    println(CreditCard.generateNewNumber())
  }
}
