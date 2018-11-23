package com.ml.spark_core.demo

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.util.matching.Regex

object LogDemo extends App {
  // 定义conf
  val conf = new SparkConf().setAppName("LogDemo").setMaster("local[*]")
  val spark = SparkSession.builder().config(conf).getOrCreate()
  val sc = spark.sparkContext
  val ssc = new StreamingContext(sc, Seconds(2))

  // 读取文件
  val rdd = sc.textFile("E:\\tmp\\spark\\sparkCore\\log.txt").map {
    // 处理日志
    // 格式：192.168.88.1 - - [30/Jul/2017:12:53:43 +0800] "GET /MyDemoWeb/head.jsp HTTP/1.1" 200 713
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

      // 赋值为1 并返回
      (jspName, 1)
    }
  }

  // 根据key聚合
  val rdd2 = rdd.reduceByKey(_ + _)
  // 按照 访问量 降序排列
  val rdd3 = rdd2.sortBy(_._2, false)
  // rdd3.take(6) =>  Array[(String, Int)]
  // toBuffer 将定长数组转为变长数组
  // Array -> ArrayBuffer
  // ArrayBuffer((hadoop.jsp,9), (oracle.jsp,9), (web.jsp,3), (mysql.jsp,3))
  println(rdd3.take(4).toBuffer)

  // 关闭
  sc.stop()
}
