package com.ml.spark_core.transformation

import org.apache.spark.{HashPartitioner, SparkConf}
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Coalesce {

  val conf = new SparkConf().setAppName("Map").setMaster("local[*]")
  val spark = SparkSession.builder().config(conf).getOrCreate()
  val sc = spark.sparkContext
  val ssc = new StreamingContext(sc, Seconds(2))

  def main(args: Array[String]): Unit = {
    Coalesce()
    Coalesce_02()
    Repartition()
    RepartitionAndSortWithinPartitions()
    Glom()
    MapValues()
    Subtract()
  }

  /**
    * 缩减分区数，用于大数据集过滤后，提高小数据集的执行效率
    * 和repartition区别，默认不会进行 shuffle(false), 而repartition会进行shuffle(true)
    */
  def Coalesce(): Unit = {

    /**
      * 分区数 大于 coalesce指定的分区数时结果是不一样的
      */

    val rdd = sc.parallelize(1 to 16, 2)
    // 2
    println(rdd.partitions.size)

    val coalesceRDD = rdd.coalesce(3, true)
    // 3
    println(coalesceRDD.partitions.size)

    val coalesceRDDF = rdd.coalesce(3, false)
    // 2
    println(coalesceRDDF.partitions.size)
  }

  def Coalesce_02(): Unit = {

    /**
      * 分区数 大于 coalesce指定的分区数时结果是不一样的
      */

    val rdd = sc.parallelize(1 to 16, 4)
    // 4
    println(rdd.partitions.size)

    val coalesceRDD = rdd.coalesce(3, true)
    // 3
    println(coalesceRDD.partitions.size)

    val coalesceRDDF = rdd.coalesce(3, false)
    // 3
    println(coalesceRDDF.partitions.size)
  }

  /**
    * 重新分区 (重新shuffle)
    */
  def Repartition(): Unit = {
    val rdd = sc.parallelize(1 to 16, 4)
    // 4
    println(rdd.partitions.size)

    val reRdd = rdd.repartition(2)
    // 2
    println(reRdd.partitions.size)

    val reRdd_02 = rdd.repartition(4)
    // 4
    println(reRdd_02.partitions.size)
  }

  /**
    * 重新分区并且排序
    */
  def RepartitionAndSortWithinPartitions(): Unit = {
    val data = sc.parallelize(List((1, 3), (1, 2), (5, 4), (1, 4), (2, 3), (2, 4)), 3)
    val result = data.repartitionAndSortWithinPartitions(new HashPartitioner(3))

    // List((1,3), (1,2), (1,4), (2,3), (2,4), (5,4))
    println(result.collect().toList)
  }

  def Glom(): Unit = {
    val rdd = sc.parallelize(1 to 16, 4)
    // RDD[Array[Int]]
    // RDD[Array(9, 10, 11, 12), Array(1, 2, 3, 4),  Array(5, 6, 7, 8), Array(13, 14, 15, 16)]
    val result = rdd.glom()

    /**
      * List(9, 10, 11, 12)
      * List(1, 2, 3, 4)
      * List(5, 6, 7, 8)
      * List(13, 14, 15, 16)
      */
    // x  Array[Int]
    result.foreach(x => println(x.toList))
  }

  /**
    * 对于key, value 结构的 RDD，只处理value
    */
  def MapValues(): Unit = {
    val rdd = sc.parallelize(Array((1, "a"), (1, "d"), (2, "b"), (3, "c")))
    val result = rdd.mapValues(_ + "-")

    // List((1,a-), (1,d-), (2,b-), (3,c-))
    println(result.collect().toList)
  }

  /**
    * 去除和 参数重复 的元素
    */
  def Subtract(): Unit = {
    val rdd = sc.parallelize(3 to 8)
    val rdd1 = sc.parallelize(1 to 5)
    val result = rdd.subtract(rdd1)

    // List(8, 6, 7)
    println(result.collect().toList)
  }
}
