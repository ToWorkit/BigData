package products

import org.apache.spark.sql.SparkSession
import java.lang.Double

// 商品表
case class Product(prod_id: Int, prod_name: String)

// 订单表
case class SaleOrder(prod_id: Int, year_id: Int, amount: Double)

object productsTotal {
  def main(args: Array[String]): Unit = {
    // 创建 SparkSession
    val spark = SparkSession.builder().master("local[*]").appName("productsTotal").getOrCreate()

    import spark.implicits._

    // 读取商品数据
    val productInfo = spark.sparkContext.textFile("hdfs://bigdata111:9000/datas/products").map(
      line => {
        // 分词
        val words = line.split(",")
        // 返回ID和名称
        (Integer.parseInt(words(0)), words(1))
      }
    )
      .map(item => Product(item._1, item._2))
      .toDF()

    // 读取订单数据
    val saleInfo = spark.sparkContext.textFile("hdfs://bigdata111:9000/datas/sales").map(
      line => {
        // 分词
        val words = line.split(",")

        // 返回 商品Id 年份 金额
        (Integer.parseInt(words(0)), Integer.parseInt(words(2).substring(0, 4)), Double.parseDouble(words(6)))
      }
    )
      .map(item => SaleOrder(item._1, item._2, item._3))
      .toDF()

    // 注册视图
    productInfo.createTempView("product")
    saleInfo.createTempView("sale")

    // 第一步 sql 获取结果
    var sql = "select prod_name, year_id, sum(amount) "
    sql += "from product, sale "
    sql += "where product.prod_id = sale.prod_id "
    sql += "group by prod_name, year_id"

    val result = spark.sql(sql).toDF("prod_name", "year_id", "total")
    // 注册视图
    result.createTempView("result")
    result.show()

    // 第二步 行转列 case 相当于 if
    spark.sql("select prod_name, sum(case year_id when 1998 then total else 0 end), " +
      "sum(case year_id when 1999 then total else 0 end), " +
      "sum(case year_id when 2000 then total else 0 end), " +
      "sum(case year_id when 2001 then total else 0 end) from result group by prod_name")
      .toDF("prod_name","1998","1999","2000","2001")
      .show()

    // 关闭
    spark.stop()
  }
}
