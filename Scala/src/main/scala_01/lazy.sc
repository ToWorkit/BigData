// 懒值 定义时不会使用，只用在使用时才会执行

val x: Int = 10
val z = x + x
println(z)

lazy val y: Int = x + 11 // 定义
y // 执行

// 读取文件(使用lazy读取当前还不存在的文件)
val words = scala.io.Source.fromFile("d:\\a.txt").mkString
println(words)

lazy val words_01 = scala.io.Source.fromFile("d:\\a.txt").mkString

// 读取当下还不存在的值, 控制台中不会报错, 在scala shell中效果比较明显
lazy val words_02 = scala.io.Source.fromFile("d:\\b.txt").mkString
println(words_02)