import org.apache.spark.{SparkConf, SparkContext}

object mr {
  def main(args: Array[String]): Unit = {
    // 配置 sc
    val conf = new SparkConf().setAppName("mr").setMaster("local[*]")
    val sc = new SparkContext(conf)

    // 读取数据
    val rdd1 = sc.textFile("hdfs://192.168.1.111:9000/datas/SogouQ.txt")
    // 数据清洗 以 \t 分割，且每条数据的长度为 6
    val rdd2 = rdd1.map(_.split("\t")).filter(_.length == 6)
    // 下标为3的值为 1 ，且下标为4的值为 2
    val rdd3 = rdd2.filter(_(3).toInt == 1).filter(_(4).toInt == 2)
    // 计数
    println(rdd3.count())
    // 取3条
//    println(rdd3.take(3))
    rdd3.foreach(x => x.foreach(println))

    // 关闭连接
    sc.stop()
  }
}
