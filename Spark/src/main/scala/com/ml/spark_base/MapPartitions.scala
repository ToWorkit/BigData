package com.ml.spark_base

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Seconds, StreamingContext}

object MapPartitions {

  val conf = new SparkConf().setAppName("Map").setMaster("local[*]")
  val spark = SparkSession.builder().config(conf).getOrCreate()
  val sc = spark.sparkContext
  val ssc = new StreamingContext(sc, Seconds(2))

  val rdd = sc.makeRDD(1 to 10, 4)

  def main(args: Array[String]): Unit = {

  }

  /**
    * 类似于map，但是独立的在RDD的每一个分区上运行
    *   在类型为T的RDD上运行时，函数方法的类型必须是 Iterator[T] => Iterator[U]
    *   假设N个元素有M的分区，那么map函数会被调用N次，mapPartitions被调用M次
    *   rdd有10元素，3个分区，map会调用10次，mapPartitions只调用3次
    */
  def MapPartitions(): Unit = {

  }
}
