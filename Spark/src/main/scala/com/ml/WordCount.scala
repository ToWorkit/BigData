package Scala_Server
import org.apache.spark.{ SparkConf, SparkContext }

object WordCount {
  def main(args: Array[String]): Unit = {
    // 创建一个Config
    val conf = new SparkConf().setAppName("MyScalaWordCount")

    // 创建最核心的SparkContext对象 sc
    val sc = new SparkContext(conf)

    // 使用 sc 对象执行相应的算子(函数)
    sc.textFile(args(0)) // 根据参数读取文件
      .flatMap(_.split(" ")) // 分词并压平
      .map((_, 1)) // 统计
      .reduceByKey(_ + _) // 根据 key 对统计的数据求和
      .saveAsTextFile(args(1)) // 根据参数保存处理好的文件

    // 停止
    sc.stop()
  }
}
