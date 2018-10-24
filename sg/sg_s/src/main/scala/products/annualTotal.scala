package products

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}
import java.lang.Double

// 定义一个case class表示订单表
// 指定DataFrame的Schema
case class OrderInfo(year: Int, amount: Double)

object annualTotal {
  def main(args: Array[String]): Unit = {
    // 创建SQLContext
    val conf = new SparkConf().setAppName("annualTotal").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val sQLContext = new SQLContext(sc) // SparkSession

    import sQLContext.implicits._

    // 从HDFS上读入数据
    val data = sc.textFile("hdfs://bigdata111:9000/datas/sales").map(
      // //13,1660,1998-01-10,3,999,1,1232.16
      line => {
        // 分词
        val words = line.split(",")

        // 取出年份和金额
        (Integer.parseInt(words(2).substring(0, 4)), Double.parseDouble(words(6)))
      }
      // 转为schema格式
    ).map(item => OrderInfo(item._1, item._2))
      // 转为DataFrame
     .toDF()

    /**
      * root
      * |-- year: integer (nullable = true)
      * |-- amount: double (nullable = true)
      */
    data.printSchema()

    // 创建视图
    data.createTempView("orders")

    // 执行查询
    /**
      * +----+-------------+--------------------+
      * |year|count(amount)|         sum(amount)|
      * +----+-------------+--------------------+
      * |1998|       178834| 2.408391494998051E7|
      * |2001|       259418|2.8136461979984347E7|
      * |2000|       232646|2.3765506619997747E7|
      * |1999|       247945|2.2219947660012607E7|
      * +----+-------------+--------------------+
      */
    sQLContext.sql("select year, count(amount), sum(amount) from orders group by year").show()

    // 关闭
    sc.stop()
  }
}
