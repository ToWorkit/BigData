
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
    val studentDF = spark.createDataFrame(rowRdd, schema);

    // 生成表
    studentDF.createOrReplaceTempView("student");

    // 执行SQL
    val result = spark.sql("select * from student")

    // 显示
    result.show()
  }
}
