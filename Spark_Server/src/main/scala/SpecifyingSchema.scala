
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.StructType

class SpecifyingSchema {
  def main(args: Array[String]): Unit = {
    // 创建一个Spark Session对象
    val spark = SparkSession.builder().master("local").appName("SpecifyingSchema").getOrCreate()
    // 在SparkSession中包含一个SparkContext，读取数据
    val data = spark.sparkContext.textFile("D:\\test.txt").map(_.split(" "))

    // 创建Schema的结构
    val schema = StructType
  }
}
