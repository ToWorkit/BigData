package com.ml.spark_core.demo

import org.apache.spark.{Partitioner, SparkConf}
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable

object LogPartitionDemo {
  def main(args: Array[String]): Unit = {
    // 设置环境变量
//    System.setProperty("hadoop.home.dir", "")
    // 初始化
    val conf = new SparkConf().setAppName("LogPartitionDemo").setMaster("local[*]")
    val spark = SparkSession.builder().config(conf).getOrCreate()
    val sc = spark.sparkContext
    val ssc = new StreamingContext(sc, Seconds(2))

    val rdd = sc.textFile("E:\\tmp\\spark\\sparkCore\\log.txt").map {
      line => {
        // 解析字符串找出 xx.jsp
        // 可以使用正则来处理
        // val pattern = new Regex("")

        // 第一个双引号的位置
        val index1 = line.indexOf("\"")
        // 第二个双引号的位置
        val index2 = line.lastIndexOf("\"")
        // 截取请求信息  GET /MyDemoWeb/hadoop.jsp HTTP/1.1
        val str1 = line.substring(index1 + 1, index2)

        // 解析
        val index3 = str1.indexOf(" ")
        val index4 = str1.lastIndexOf(" ")
        // 截取url路径  /MyDemoWeb/hadoop.jsp
        val str2 = str1.substring(index3 + 1, index4)

        // 找到*.jsp  hadoop.jsp
        val jspName = str2.substring(str2.lastIndexOf("/") + 1)

        // 文件名  文件所处日志
        // 倒排索引
        (jspName, line)
      }
    }

    // 根据key建立分区，得到个数
    // 获取key，去重，转为 Array格式  Array[String]
    val rdd2 = rdd.map(_._1).distinct().collect()
    // ArrayBuffer(, hadoop.jsp, web.jsp, oracle.jsp, mysql.jsp, head.jsp, java.jsp, body.jsp)
    // println(rdd2.toBuffer)

    // 根据key建立分区，创建自定义分区规则
    val myPartition = new MyPartition(rdd2)

    // 执行分区操作
    val result = rdd.partitionBy(myPartition)

    // 输出
    result.saveAsTextFile("E:\\tmp\\rddpartition")

    // 关闭
    sc.stop()
  }
}

// 自定义分区规则  需要继承自 Partitioner
class MyPartition(keys: Array[String]) extends Partitioner {

  // 定义一个可变集合保存分区的条件
  // String -> key, Int -> 分区号
  val partitionMap = new mutable.HashMap[String, Int]()

  // 建立分区规则
  // 分区号
  var partID = 0
  for (key <- keys) {
    // 有一个key就建立一个分区
    partitionMap.put(key, partID)

    // 累加
    partID += 1
  }

  // 重写方法
  // 返回分区的个数
  override def numPartitions: Int = partitionMap.size

  // 根据key获取分区号
  override def getPartition(key: Any): Int = {
    partitionMap.getOrElse(key.toString, -1)
  }
}
