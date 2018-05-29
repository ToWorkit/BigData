
import java.util.Properties

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.Row
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

object SpecifyingSchema {
  def main(args: Array[String]): Unit = {
    // 创建一个Spark Session对象
    val spark = SparkSession.builder().master("local").appName("SpecifyingSchema").getOrCreate()
    // 在SparkSession中包含一个SparkContext，读取数据
    val data = spark.sparkContext.textFile("D:\\test.txt").map(_.split(" "))

    // 创建Schema的结构
    val schema = StructType(
      List(
        StructField("id", IntegerType, true),
        StructField("name", StringType, true),
        StructField("age", IntegerType, true)
      )
    )

    // 将数据RDD映射成Row
    val rowRdd = data.map(p => Row(p(0).toInt, p(1).trim, p(2).toInt))

    // 关联schema
    val studentDF = spark.createDataFrame(rowRdd, schema)

    // 生成表
    studentDF.createOrReplaceTempView("student")

    // 执行SQL
    val result = spark.sql("select * from student")

    // 本地显示
//    result.show()

    // 保存到Oracle中
    val props = new Properties()
    // 用户名和密码
    props.setProperty("user", "scott")
    props.setProperty("password", "tiger")

    // 会自动创建表，不需要手动创建
    result.write.jdbc("jdbc:oracle:thin:@192.168.106.11:1521/oracle.example.com", "scott.student", props)

    // 如果表表已经存在了就采用append模式
    // result.write.mode("append").jdbc("jdbc:oracle:thin:@192.168.106.11:1521/oracle.example.com", "scott.student", props)

    // 完成后停止
    spark.stop()
  }
}
