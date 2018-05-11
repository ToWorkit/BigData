// 泛型类

// 操作Int的类型
class GenericClass_01 {
  // 定义一个变量
  private var content: Int = 100

  // 定义get和set方法
  def set(value: Int) = {content = value}
  def get(): Int = {content}
}

// 操作String的类型
class GenericClass_02 {
  // 定义一个变量
  private var content: String = "Hello World"

  // 定义get和set方法
  def set(value: String) = {content = value}
  def get(): String = {content}
}

// 当前问题： 能否有一个通用的类，既能操作Int，也能操作String
class GenericClass[T] {
  // 定义一个变量
  private var content: T = _

  // 定义get和set方法
  def set(value: T) = {content = value}
  def get(): T = {content}
}

object GenericClass {
  def main(args: Array[String]): Unit = {
    // 操作Int
    var intGeneric = new GenericClass[Int]
    intGeneric.set(1)
    println(intGeneric.get())

    // 操作字符串
    var StringGeneric = new GenericClass[String]
    StringGeneric.set("Hello World")
    println(StringGeneric.get())
  }
}
