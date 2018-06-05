package demo0604

// 操作Int的类型
class Generic_Class_0 {
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

// 泛型 通用的类，既可以操作Int，亦可以操作String
class GenericClass[T] {
  // 定义一个变量 _ 代表所有类型的值
  private var content: T = _

  // 定义get和set方法
  def set(value: T) = {content = value}
  def get() = {content}
}

object GenericClass {
  def main(args: Array[String]): Unit = {
    // 操作Int
    var intOp = new GenericClass[Int]
    intOp.set(200)
    println(intOp.get())
    println(intOp.content)

    // 操作字符串
    var stringOp = new GenericClass[String]
    stringOp.set("Hello World")
    println(stringOp.get())
    println(stringOp.content)
  }
}