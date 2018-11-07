package com.ml.spark_base.transformation

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Sample {

  val conf = new SparkConf().setAppName("Sample").setMaster("local[*]")
  val spark = SparkSession.builder().config(conf).getOrCreate()
  val sc = spark.sparkContext
  val ssc =  new StreamingContext(sc, Seconds(2))

  val rdd = sc.parallelize(1 to 10)

  val rdd1 = sc.parallelize(1 to 5)
  val rdd2 = sc.parallelize(5 to 10)

  def main(args: Array[String]): Unit = {
    Sample()
    Union()
    Intersection()
    Distinct()
    partitionBy()
  }

  /**
    * 抽样
    * 第一个参数 withReplacement 如果为true,可能会有重复的元素，如果为false，不会有重复的元素
    * 第二个参数 fration 取值为[0,1]，最后的数据个数大约等于第二个参数乘总数
    * 第三个参数 seed 为随机因子
    *   例
    *     从RDD中随机且有放回的抽出50%的数据，随机种子值为3（即可能以1 2 3的其中一个起始值）
    */
  def Sample(): Unit = {
    val sampleRdd = rdd.sample(true, 0.4, 2)
    // List(1, 2, 2, 7, 7, 8, 9)
    println(sampleRdd.collect().toList)

    val sampleRdd_2 = rdd.sample(false, 0.4, 2)
    // List(1, 7, 8)
    println(sampleRdd_2.collect().toList)
  }

  /**
    * 求并集 返回一个新的RDD
    */
  def Union(): Unit = {
    val rdd3 = rdd1.union(rdd2)
    // List(1, 2, 3, 4, 5, 5, 6, 7, 8, 9, 10)
    println(rdd3.collect().toList)
  }

  /**
    * 求交集
    */
  def Intersection(): Unit = {
    val rdd1 = sc.parallelize(1 to 7)
    val rdd3 = rdd1.intersection(rdd2)
    // List(5, 6, 7)
    println(rdd3.collect().toList)
  }

  /**
    * 去重
    */
  def Distinct(): Unit = {
    val distinctRdd = sc.parallelize(List(1,2,1,5,2,9,6,1))
    val unionRDD = distinctRdd.distinct()
    // List(1, 9, 5, 6, 2)
    println(unionRDD.collect().toList)
  }

  /**
    * 重新分区
    *   如果原有的partionRDD和现有的partionRDD是一致的话就不进行分区，否则会生成ShuffleRDD. 
    */
  def partitionBy(): Unit = {
    val rdd = sc.parallelize(Array((1,"aaa"),(2,"bbb"),(3,"ccc"),(4,"ddd")),4)
    // 4
    println(rdd.partitions.size)

    var rdd2 = rdd.partitionBy(new org.apache.spark.HashPartitioner(2))
    // 2
    println(rdd2.partitions.size)
  }
}
